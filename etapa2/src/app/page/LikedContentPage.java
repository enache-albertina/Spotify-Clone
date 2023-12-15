package app.page;
import app.user.User;

/**
 * The type Liked content page.
 */
public class LikedContentPage implements Page {
    private User owner;

    /**
     * Instantiates a new Liked content page.
     *
     * @param owner the owner
     */
    public LikedContentPage(final User owner) {
        this.owner = owner;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Instantiates a new Liked content page.
     */
    public LikedContentPage() {

    }
    /**
     * Accepts a visitor that operates on this object.
     *
     * @param visitor The PageVisitor to be accepted.
     */
    @Override
    public void accept(final PageVisitor visitor) {
        visitor.visit(this);
    }
}
