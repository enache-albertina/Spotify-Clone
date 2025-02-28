package app.page;
import app.user.User;

/**
 * The type Home page.
 */
public class HomePage implements Page {
    private User owner;

    /**
     * Instantiates a new Home page.
     *
     * @param owner the owner
     */
    public HomePage(final User owner) {
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
     * Instantiates a new Home page.
     */
    public HomePage() {

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
