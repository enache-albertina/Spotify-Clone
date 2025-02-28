package app.page;

/**
 * The interface Page.
 */
public interface Page {
    /**
     * Accept.
     *
     * @param visitor the visitor
     */
    void accept(PageVisitor visitor);
}
