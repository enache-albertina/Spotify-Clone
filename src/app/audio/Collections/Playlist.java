package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.utils.Enums;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Playlist.
 */
@Getter
public class Playlist extends AudioCollection {
    /**
     * Gets songs.
     *
     * @return the songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Gets visibility.
     *
     * @return the visibility
     */
    public Enums.Visibility getVisibility() {
        return visibility;
    }

    /**
     * Sets visibility.
     *
     * @param visibility the visibility
     */
    public void setVisibility(final Enums.Visibility visibility) {
        this.visibility = visibility;
    }

    /**
     * Gets followers.
     *
     * @return the followers
     */
    public Integer getFollowers() {
        return followers;
    }

    /**
     * Sets followers.
     *
     * @param followers the followers
     */
    public void setFollowers(final Integer followers) {
        this.followers = followers;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    private final ArrayList<Song> songs;
    private Enums.Visibility visibility;
    private Integer followers;
    private int timestamp;

    /**
     * Instantiates a new Playlist.
     *
     * @param name  the name
     * @param owner the owner
     */
    public Playlist(final String name, final String owner) {
        this(name, owner, 0);
    }

    /**
     * Instantiates a new Playlist.
     *
     * @param name      the name
     * @param owner     the owner
     * @param timestamp the timestamp
     */
    public Playlist(final String name, final String owner, final int timestamp) {
        super(name, owner);
        this.songs = new ArrayList<>();
        this.visibility = Enums.Visibility.PUBLIC;
        this.followers = 0;
        this.timestamp = timestamp;
    }

    /**
     * Contains song boolean.
     *
     * @param song the song
     * @return the boolean
     */
    public boolean containsSong(final Song song) {
        return songs.contains(song);
    }

    /**
     * Add song.
     *
     * @param song the song
     */
    public void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Remove song.
     *
     * @param song the song
     */
    public void removeSong(final Song song) {
        songs.remove(song);
    }

    /**
     * Remove song.
     *
     * @param index the index
     */
    public void removeSong(final int index) {
        songs.remove(index);
    }

    /**
     * Switch visibility.
     */
    public void switchVisibility() {
        if (visibility == Enums.Visibility.PUBLIC) {
            visibility = Enums.Visibility.PRIVATE;
        } else {
            visibility = Enums.Visibility.PUBLIC;
        }
    }

    /**
     * Increase followers.
     */
    public void increaseFollowers() {
        followers++;
    }

    /**
     * Decrease followers.
     */
    public void decreaseFollowers() {
        followers--;
    }
    /**
     * Gets the number of tracks in the collection.
     *
     * @return The number of tracks.
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }
    /**
     * Gets a track by index.
     *
     * @param index The index of the track.
     * @return The track at the given index.
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }
    /**
     * Determines if the collection is visible to a specific user.
     *
     * @param user The user to check visibility for.
     * @return true if visible to the user, false otherwise.
     */
    @Override
    public boolean isVisibleToUser(final String user) {
        return this.getVisibility() == Enums.Visibility.PUBLIC
               || (this.getVisibility() == Enums.Visibility.PRIVATE
                   && this.getOwner().equals(user));
    }
    /**
     * Checks if the number of followers matches the given criteria.
     *
     * @param followerNum The criteria for follower count
     * @return true if the criteria is met, false otherwise.
     */
    @Override
    public boolean matchesFollowers(final String followerNum) {
        return filterByFollowersCount(this.getFollowers(), followerNum);
    }
    /**
     * Filters based on follower count and a query string.
     *
     * @param count The number of followers.
     * @param query The query string for filtering
     * @return true if the count matches the query, false otherwise.
     */
    private static boolean filterByFollowersCount(final int count, final String query) {
        if (query.startsWith("<")) {
            return count < Integer.parseInt(query.substring(1));
        } else if (query.startsWith(">")) {
            return count > Integer.parseInt(query.substring(1));
        } else {
            return count == Integer.parseInt(query);
        }
    }
}
