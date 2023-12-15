package app.page;

interface PageVisitor {
    void visit(HomePage homePage);
    void visit(LikedContentPage likedContentPage);
    void visit(ArtistPage artistPage);
    void visit(HostPage hostPage);
}
