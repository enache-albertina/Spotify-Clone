package app.searchBar;


import app.Admin;
import app.audio.LibraryEntry;
import app.user.UserSingleton;
import lombok.Getter;
import app.user.Artist;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import app.user.Host;


import static app.searchBar.FilterUtils.filterByAlbum;
import static app.searchBar.FilterUtils.filterByArtist;
import static app.searchBar.FilterUtils.filterByFollowers;
import static app.searchBar.FilterUtils.filterByGenre;
import static app.searchBar.FilterUtils.filterByLyrics;
import static app.searchBar.FilterUtils.filterByName;
import static app.searchBar.FilterUtils.filterByOwner;
import static app.searchBar.FilterUtils.filterByPlaylistVisibility;
import static app.searchBar.FilterUtils.filterByReleaseYear;
import static app.searchBar.FilterUtils.filterByTags;

/**
 * The type Search bar.
 */
public final class SearchBar {
    /**
     * Gets results.
     *
     * @return the results
     */
    public List<LibraryEntry> getResults() {
        return results;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(final List<LibraryEntry> results) {
        this.results = results;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets last search type.
     *
     * @return the last search type
     */
    public String getLastSearchType() {
        return lastSearchType;
    }

    /**
     * Gets last selected.
     *
     * @return the last selected
     */
    public LibraryEntry getLastSelected() {
        return lastSelected;
    }

    private List<LibraryEntry> results;
    private final String user;
    private static final Integer MAX_RESULTS = 5;
    @Getter
    private String lastSearchType;

    @Getter
    private LibraryEntry lastSelected;

    /**
     * Instantiates a new Search bar.
     *
     * @param user the user
     */
    public SearchBar(final String user) {
        this.results = new ArrayList<>();
        this.user = user;
    }

    /**
     * Clear selection.
     */
    public void clearSelection() {
        lastSelected = null;
        lastSearchType = null;
    }
    private List<LibraryEntry> convertArtistsToLibraryEntries(final List<Artist> artists) {
        List<LibraryEntry> entries = new ArrayList<>();
        for (Artist artistLocal : artists) {
            entries.add(new LibraryEntry(artistLocal));
        }
        return entries;
    }
    private List<LibraryEntry> convertHosttoLibraryEntries(final List<Host> hosts) {
        List<LibraryEntry> entries = new ArrayList<>();
        for (Host hostLocal : hosts) {
            entries.add(new LibraryEntry(hostLocal));
        }
        return entries;
    }


    /**
     * Search list.
     *
     * @param filters the filters
     * @param type    the type
     * @return the list
     */
    public List<LibraryEntry> search(final Filters filters, final String type) {
        List<LibraryEntry> entries;

        switch (type) {
            case "song":
                entries = new ArrayList<>(Admin.getSongs());
                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }

                if (filters.getAlbum() != null) {
                    entries = filterByAlbum(entries, filters.getAlbum());
                }

                if (filters.getTags() != null) {
                    entries = filterByTags(entries, filters.getTags());
                }

                if (filters.getLyrics() != null) {
                    entries = filterByLyrics(entries, filters.getLyrics());
                }

                if (filters.getGenre() != null) {
                    entries = filterByGenre(entries, filters.getGenre());
                }

                if (filters.getReleaseYear() != null) {
                    entries = filterByReleaseYear(entries, filters.getReleaseYear());
                }

                if (filters.getArtist() != null) {
                    entries = filterByArtist(entries, filters.getArtist());
                }
                break;
            case "playlist":
                entries = new ArrayList<>(Admin.getPlaylists());

                entries = filterByPlaylistVisibility(entries, user);

                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }

                if (filters.getOwner() != null) {
                    entries = filterByOwner(entries, filters.getOwner());
                }

                if (filters.getFollowers() != null) {
                    entries = filterByFollowers(entries, filters.getFollowers());
                }

                break;
            case "podcast":
                entries = new ArrayList<>(Admin.getPodcasts());

                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }

                if (filters.getOwner() != null) {
                    entries = filterByOwner(entries, filters.getOwner());
                }

                break;
            case "artist":
                List<Artist> allArtists = UserSingleton.getInstance().getUsers().stream()
                        .filter(userLocal -> userLocal instanceof Artist)
                        .map(userLocal -> (Artist) userLocal)
                        .collect(Collectors.toList());
                entries = convertArtistsToLibraryEntries(allArtists);
                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }
                break;

            case "album":
                entries = new ArrayList<>(Admin.getAlbums());
                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }

                if (filters.getOwner() != null) {
                    // printare continut entries
                    entries = filterByOwner(entries, filters.getOwner());
                }

                break;
                case "host":
                List<Host> allHosts = UserSingleton.getInstance().getUsers().stream()
                        .filter(userLocal -> userLocal instanceof Host)
                        .map(userLocal -> (Host) userLocal)
                        .collect(Collectors.toList());
                entries = convertHosttoLibraryEntries(allHosts);
                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }
                break;
            default:
                entries = new ArrayList<>();
        }

        while (entries.size() > MAX_RESULTS) {
            entries.remove(entries.size() - 1);
        }

        this.results = entries;
        this.lastSearchType = type;
        return this.results;
    }

    /**
     * Select library entry.
     *
     * @param itemNumber the item number
     * @return the library entry
     */
    public LibraryEntry select(final Integer itemNumber) {
        if (this.results.size() < itemNumber) {
            results.clear();

            return null;
        } else {
            lastSelected =  this.results.get(itemNumber - 1);
            results.clear();

            return lastSelected;
        }
    }
}
