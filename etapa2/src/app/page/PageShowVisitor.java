package app.page;

import app.user.User;

import java.util.Comparator;
import java.util.List;

import java.util.ArrayList;
import java.util.stream.Collectors;

import app.audio.Files.Song;
import app.audio.Collections.Playlist;
import app.user.Artist;
import app.user.Host;


/**
 * The type Page show visitor.
 */
public class PageShowVisitor implements PageVisitor {
    private static final int FIVE = 5;
    public static final int ZERO = 0;

    private User user;
    private String result = "";

    /**
     * Instantiates a new Page show visitor.
     *
     * @param user the user
     */
    public PageShowVisitor(final User user) {
        this.user = user;
    }

    /**
     * Instantiates a new Page show visitor.
     */
    public PageShowVisitor() {

    }
    /**
     * Gathers and formats the user's top liked songs and followed playlists.
     *
     * @param page The HomePage to process.
     */
    @Override
    public void visit(final HomePage page) {
        List<Song> likedSongsList = new ArrayList<>(user.getLikedSongs());
        likedSongsList.sort(Comparator.comparingInt(Song::getLikes).reversed());
        // primele 5 melodii
        likedSongsList = likedSongsList.subList(ZERO, Math.min(FIVE, likedSongsList.size()));

        String likedSongs = likedSongsList.stream()
                .map(Song::getName)
                .collect(Collectors.joining(", ", "[", "]"));


        List<Playlist> followedPlaylistList = user.getFollowedPlaylists();

        String followedPlaylists =  followedPlaylistList.stream()
                .map(Playlist::getName)
                .collect(Collectors.joining(", ", "[", "]"));

        result = "Liked songs:\n\t" + likedSongs + "\n\nFollowed playlists:\n\t"
                + followedPlaylists;
    }
    /**
     * Compiles information about the podcasts and announcements hosted by a Host.
     * <p>
     * Extracts and formats details of all podcasts and their episodes, as well as announcements,
     * managed by the host. The formatted information includes podcast names with episode details,
     * and announcement names with descriptions.
     *
     * @param page The HostPage to be visited.
     */
    @Override
    public void visit(final HostPage page) {
        Host host = page.getHost();
        String podcastInfo = host.getPodcastHost().stream()
                .map(podcast -> podcast.getName() + ":\n\t"
                        + podcast.getEpisodes().stream()
                        .map(episode -> episode.getName() + " - " + episode.getDescription())
                        .collect(Collectors.joining(", ", "[", "]")))
                .collect(Collectors.joining("\n, ", "[", "\n]"));

        String announcementsInfo = host.getAnnouncement().stream()
                .map(announcement -> announcement.getName()
                        + ":\n\t" + announcement.getDescription())
                .collect(Collectors.joining(", ", "[", "\n]"));

        result = "Podcasts:\n\t" + podcastInfo + "\n\nAnnouncements:\n\t" + announcementsInfo;
    }
    /**
     * Processes the LikedContentPage to compile a list of liked songs and followed playlists.
     * <p>
     * This method gathers the user's liked songs and followed playlists, formatting their names
     * and additional details into a string. It lists the names and artists of the liked songs,
     * and the names and owners of the followed playlists.
     *
     * @param page The LikedContentPage to be visited and processed.
     */
    @Override
    public void visit(final LikedContentPage page) {
        List<Song> likedSongsList =  new ArrayList<>(user.getLikedSongs());

        String likedSongs = likedSongsList.stream()
                .map(song -> song.getName() + " - " + song.getArtist())
                .collect(Collectors.joining(", ", "[", "]"));
        List<Playlist> followedPlaylistList = user.getFollowedPlaylists();

        String followedPlaylists = followedPlaylistList.stream()
                .map(playlist -> playlist.getName() + " - " + playlist.getOwner())
                .collect(Collectors.joining(", ", "[", "]"));
        result = "Liked songs:\n\t" + likedSongs + "\n\nFollowed playlists:\n\t"
                + followedPlaylists;
    }

    /**
     * Visit.
     *
     * @param page the page
     */
    @Override
    public void visit(final ArtistPage page) {
        Artist artist = page.getArtist();

        String albumInfo = artist.getAlbums().stream()
                .map(album -> album.getName())
                .collect(Collectors.joining(", ", "[", "]"));

        String merchInfo = artist.getMerch().stream()
                .map(merch -> merch.getName() + " - " + merch.getPrice()
                        + ":\n\t" + merch.getDescription())
                .collect(Collectors.joining(", ", "[", "]"));

        String eventInfo = artist.getEvents().stream()
                .map(event -> event.getName() + " - " + event.getDate() + ":\n\t"
                        + event.getDescription())
                .collect(Collectors.joining(", ", "[", "]"));

        result = "Albums:\n\t" + albumInfo + "\n\nMerch:\n\t" + merchInfo
                + "\n\nEvents:\n\t" + eventInfo;

    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public String getResult() {
        return result;
    }
}
