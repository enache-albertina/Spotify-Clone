package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import fileio.input.SongInput;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Album.
 */
public class Album extends AudioCollection {
    private String name;
    private String artist;
    private int releaseYear;
    private List<Song> songs;
    private String description;

    /**
     * Is loaded boolean.
     *
     * @return the boolean
     */
    public boolean isLoaded() {
        return isLoaded;
    }

    /**
     * Gets numberof likes.
     *
     * @return the numberof likes
     */
    public int getNumberofLikes() {
        int likes = 0;
        for (Song song : songs) {
            likes += song.getLikes();
        }
        return likes;
    }

    /**
     * Sets loaded.
     *
     * @param loaded the loaded
     */
    public void setLoaded(final boolean loaded) {
        isLoaded = loaded;
    }

    private boolean isLoaded;

    /**
     * Instantiates a new Album.
     *
     * @param name        the name
     * @param artist      the artist
     * @param releaseYear the release year
     * @param description the description
     * @param songsInput  the songs input
     */
    public Album(final String name, final String artist, final int releaseYear,
                 final String description, final List<SongInput> songsInput) {
        super(name, artist);

        this.name = name;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.description = description;
        this.songs = new ArrayList<>();
        this.isLoaded = false;

        for (SongInput songInput : songsInput) {
            Song song = new Song(
                    songInput.getName(),
                    songInput.getDuration(),
                    this.name, // Numele albumului pentru melodie
                    songInput.getTags(),
                    songInput.getLyrics(),
                    songInput.getGenre(),
                    songInput.getReleaseYear(),
                    songInput.getArtist()
            );
            songs.add(song);
        }
    }
    /**
     * Retrieves the total number of tracks in the collection.
     *
     * @return the count of songs in the collection.
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }
    /**
     * Returns the track at the given index or null if index is out of range.
     *
     * @param index Index of the track.
     * @return Track at the given index, or null.
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        if (index >= 0 && index < songs.size()) {
            return songs.get(index);
        }
        return null;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
// Getteri și setteri pentru fiecare câmp
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets artist.
     *
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets artist.
     *
     * @param artist the artist
     */
    public void setArtist(final String artist) {
        this.artist = artist;
    }

    /**
     * Gets release year.
     *
     * @return the release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets release year.
     *
     * @param releaseYear the release year
     */
    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Sets songs.
     *
     * @param songs the songs
     */
    public void setSongs(final List<Song> songs) {
        this.songs = songs;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}
