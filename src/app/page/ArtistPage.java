package app.page;

import app.user.Artist;

/**
 * The type Artist page.
 */
public class ArtistPage implements Page {

    private Artist artist;

    /**
     * Instantiates a new Artist page.
     *
     * @param artist the artist
     */
    public ArtistPage(final Artist artist) {
        this.artist = artist;
    }

    /**
     * Gets artist.
     *
     * @return the artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Instantiates a new Artist page.
     */
    public ArtistPage() {

    }
    /**
     * Accepts a visitor that performs operations on this page.
     * <p>
     * This method allows the implementation of the Visitor pattern,
     * enabling external operations
     * to be applied to this page object without modifying its structure.
     * The actual operation
     * is defined in the visitor implementation.
     *
     * @param visitor The visitor instance which performs the operation on this page.
     */
    @Override
    public void accept(final PageVisitor visitor) {
        visitor.visit(this);
    }
}

