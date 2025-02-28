package app.audio.Collections;

import app.utils.Enums;
import lombok.Getter;

import java.util.ArrayList;

/**
 * The type Playlist output.
 */
@Getter
public class PlaylistOutput {
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public ArrayList<String> getSongs() {
        return songs;
    }

    /**
     * Gets visibility.
     *
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Gets followers.
     *
     * @return the followers
     */
    public int getFollowers() {
        return followers;
    }

    private final String name;
    private final ArrayList<String> songs;
    private final String visibility;
    private final int followers;


    /**
     * Instantiates a new Playlist output.
     *
     * @param playlist the playlist
     */
    public PlaylistOutput(final Playlist playlist) {
        this.name = playlist.getName();
        this.songs = new ArrayList<>();
        for (int i = 0; i < playlist.getSongs().size(); i++) {
            songs.add(playlist.getSongs().get(i).getName());
        }
        this.visibility = playlist.getVisibility() == Enums.Visibility.PRIVATE
                                                      ? "private" : "public";
        this.followers = playlist.getFollowers();
    }

}
