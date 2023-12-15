package app.audio;

import lombok.Getter;

import java.util.ArrayList;
import app.user.Artist;
import app.user.Host;
import app.audio.Collections.Album;
import app.audio.Collections.Podcast;


/**
 * The type Library entry.
 */
@Getter
public class LibraryEntry {
    private Podcast podcast = null;

    public LibraryEntry(final Podcast podcast) {
        this.name = podcast.getName();
        this.podcast = podcast;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    private final String name;

    /**
     * Instantiates a new Library entry.
     *
     * @param name the name
     */
    public LibraryEntry(final String name) {
        this.name = name;
    }
    public LibraryEntry(final Artist artist) {
        this.name = artist.getUsername();
    }
    public LibraryEntry(final Host host) {
        this.name = host.getUsername();
    }
    public LibraryEntry(final Album album) {
        this.name = album.getName();
    }

    /**
     * Matches name boolean.
     *
     * @param nameFilter the name
     * @return the boolean
     */
    public boolean matchesName(final String nameFilter) {
        return getName().toLowerCase().startsWith(nameFilter.toLowerCase());
    }

    /**
     * Matches album boolean.
     *
     * @param album the album
     * @return the boolean
     */
    public boolean matchesAlbum(final String album) {
        return false;
    }

    /**
     * Matches tags boolean.
     *
     * @param tags the tags
     * @return the boolean
     */
    public boolean matchesTags(final ArrayList<String> tags) {
        return false;
    }

    /**
     * Matches lyrics boolean.
     *
     * @param lyrics the lyrics
     * @return the boolean
     */
    public boolean matchesLyrics(final String lyrics) {
        return false;
    }

    /**
     * Matches genre boolean.
     *
     * @param genre the genre
     * @return the boolean
     */
    public boolean matchesGenre(final String genre) {
        return false;
    }

    /**
     * Matches artist boolean.
     *
     * @param artist the artist
     * @return the boolean
     */
    public boolean matchesArtist(final String artist) {
        return false;
    }

    /**
     * Matches release year boolean.
     *
     * @param releaseYear the release year
     * @return the boolean
     */
    public boolean matchesReleaseYear(final String releaseYear) {
        return false;
    }

    /**
     * Matches owner boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean matchesOwner(final String user) {
        return false;
    }

    /**
     * Is visible to user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean isVisibleToUser(final String user) {
        return false;
    }

    /**
     * Matches followers boolean.
     *
     * @param followers the followers
     * @return the boolean
     */
    public boolean matchesFollowers(final String followers) {
        return false;
    }
}
