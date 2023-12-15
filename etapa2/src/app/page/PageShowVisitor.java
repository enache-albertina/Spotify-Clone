package app.page;

public class PageShowVisitor implements PageVisitor{
    @Override
    public void visit(HomePage page) {
        System.out.println("Showing home page");
    }

    @Override
    public void visit(HostPage page) {
        System.out.println("Showing host page");
    }

    @Override
    public void visit(LikedContentPage page) {
        System.out.println("Showing liked content page");
    }

    @Override
    public void visit(ArtistPage page) {
        System.out.println("Showing artist page");
    }
}
