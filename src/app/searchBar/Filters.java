package app.searchBar;

import fileio.input.FiltersInput;
import lombok.Data;

import java.util.ArrayList;

/**
 * The type Filters.
 */
@Data
public class Filters {
    /**
     * Gets name.
     *
     * @return the name
     */
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
     * Gets album.
     *
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Sets album.
     *
     * @param album the album
     */
    public void setAlbum(final String album) {
        this.album = album;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(final ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     * Gets lyrics.
     *
     * @return the lyrics
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * Sets lyrics.
     *
     * @param lyrics the lyrics
     */
    public void setLyrics(final String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(final String genre) {
        this.genre = genre;
    }

    /**
     * Gets release year.
     *
     * @return the release year
     */
    public String getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets release year.
     *
     * @param releaseYear the release year
     */
    public void setReleaseYear(final String releaseYear) {
        this.releaseYear = releaseYear;
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
     * Gets owner.
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     * Gets followers.
     *
     * @return the followers
     */
    public String getFollowers() {
        return followers;
    }

    /**
     * Sets followers.
     *
     * @param followers the followers
     */
    public void setFollowers(final String followers) {
        this.followers = followers;
    }

    private String name;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private String releaseYear;
    private String artist;
    private String owner;
    private String followers;

    /**
     * Instantiates a new Filters.
     *
     * @param filters the filters
     */
    public Filters(final FiltersInput filters) {
        this.name = filters.getName();
        this.album = filters.getAlbum();
        this.tags = filters.getTags();
        this.lyrics = filters.getLyrics();
        this.genre = filters.getGenre();
        this.releaseYear = filters.getReleaseYear();
        this.artist = filters.getArtist();
        this.owner = filters.getOwner();
        this.followers = filters.getFollowers();
    }
}
