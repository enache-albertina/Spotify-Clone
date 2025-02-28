package app.page;
import app.user.Host;

/**
 * The type Host page.
 */
public class HostPage implements Page {
    private Host host;

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

    private String owner;

    /**
     * Instantiates a new Host page.
     *
     * @param host the host
     */
    public HostPage(final Host host) {
        this.host = host;
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public Host getHost() {
        return host;
    }

    /**
     * Instantiates a new Host page.
     */
    public HostPage() {

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
