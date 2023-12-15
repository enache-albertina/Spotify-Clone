package app.audio.Collections;


import java.util.ArrayList;


/**
 * The type Album output.
 */
public class AlbumOutput {
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

    private final String name;
    private final ArrayList<String> songs;

    /**
     * Instantiates a new Album output.
     *
     * @param album the album
     */
    public AlbumOutput(final Album album) {
        this.name = album.getName();
        this.songs = new ArrayList<>();
        for (int i = 0; i < album.getSongs().size(); i++) {
            songs.add(album.getSongs().get(i).getName());
        }
    }
}
