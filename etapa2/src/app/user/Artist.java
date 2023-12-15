package app.user;

import app.audio.Files.Song;
import app.audio.Collections.Album;
import app.page.ArtistPage;
import app.player.Player;
import fileio.input.CommandInput;
import fileio.input.SongInput;

import app.utils.Enums.DateValidation;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import app.Admin;

/**
 * The type Artist.
 */
public class Artist extends User {

    private static final int THREE = 3;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
//private static List<Song> songs = new ArrayList<>();
    public Artist(final String username, final int age, final String city) {
        super(username, age, city);
        this.albums = new ArrayList<>();
        this.events = new ArrayList<>();
        this.merch = new ArrayList<>();
        this.setCurrentPage(new ArtistPage());

    }
    private List<Merch> merch = new ArrayList<>();
    private List<Event> events = new ArrayList<>();


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

    private String name;

    /**
     * Reset albums.
     */
// functie care reseteaza lista de albume
    public void resetAlbums() {
        albums.clear();
    }

    /**
     * Gets albums.
     *
     * @return the albums
     */
    public List<Album> getAlbums() {
        return albums;
    }

    /**
     * Sets albums.
     *
     * @param albums the albums
     */
    public void setAlbums(final List<Album> albums) {
        this.albums = albums;
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
     * Gets merch.
     *
     * @return the merch
     */
    public List<Merch> getMerch() {
        return merch;
    }

    /**
     * The type Merch.
     */
    public static class Merch {
        private String name;

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

        /**
         * Gets price.
         *
         * @return the price
         */
        public int getPrice() {
            return price;
        }

        /**
         * Sets price.
         *
         * @param price the price
         */
        public void setPrice(final int price) {
            this.price = price;
        }

        private String description;
        private int price;
    }

    /**
     * The type Event.
     */
    public static class Event {
        private String name;
        private String description;
        private String date;

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

        /**
         * Gets date.
         *
         * @return the date
         */
        public String getDate() {
            return date;
        }

        /**
         * Sets date.
         *
         * @param date the date
         */
        public void setDate(final String date) {
            this.date = date;
        }

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
         * Instantiates a new Event.
         */
        public Event() {

        }
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    public void addEvent(final Event event) {
        events.add(event); // Adaugă un eveniment în lista de evenimente
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    private List<Album> albums = null;
    private List<Song> songs;

    /**
     * Add album.
     *
     * @param album the album
     */
    public void addAlbum(final Album album) {
        List<Album> albumsLocal = this.getAlbums();
        albumsLocal.add(album);
        Admin.getAlbums().add(album);
    }
    /**
     * Calculates the total number of likes across all albums.
     * <p>
     * This method sums up the likes from each album in the artist's collection.
     *
     * @return The total number of likes for all albums.
     */
    public int getNumberOfAlbumsLikes() {
        Integer numberOfAlbumsLikes = 0;
        for (Album album : albums) {
            numberOfAlbumsLikes += album.getNumberofLikes();
        }
        return numberOfAlbumsLikes;
    }

    /**
     * Add album string.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String addAlbum(final CommandInput commandInput) {
        // Verifică dacă utilizatorul există și este artist
        String username = commandInput.getUsername();
        String albumName = commandInput.getName();
        int releaseYear = commandInput.getReleaseYear();
        String description = commandInput.getDescription();
        List<SongInput> songInputs = commandInput.getSongs();
        // cauta in UserSingleton
        User user = UserSingleton.getInstance().getUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (user == null) {
            return "The username " + username + " doesn't exist.";
        }
        if (!(user instanceof Artist)) {
            return username + " is not an artist.";
        }

        Artist artist = (Artist) user;

        // Verifică dacă există deja un album cu același nume pentru acest artist
        if (artist.getAlbums() != null) {
            if (artist.getAlbums().stream().anyMatch(album -> album.getName().equals(albumName))) {
                return username + " has another album with the same name.";
            }
        }
        Album newAlbum = new Album(albumName, artist.getUsername(),
                releaseYear, description, songInputs);
        // Verifică dacă există melodii duplicate în noul album
        Set<String> songNames = new HashSet<>();
        for (Song song : newAlbum.getSongs()) {
            if (!songNames.add(song.getName())) {
                return artist.getUsername() + " has the same song at least twice in this album.";
            }
        }

        // Adaugă albumul în lista artistului
        artist.addAlbum(newAlbum);
        Admin.getSongs().addAll(newAlbum.getSongs());

        return username + " has added new album successfully.";
    }

    /**
     * Valid date boolean.
     *
     * @param date the date
     * @return the boolean
     */
    public static boolean validDate(final String date) {
        String[] dateSplit = date.split("-");
        if (dateSplit.length != THREE) {
            return false;
        }
        int year = Integer.parseInt(dateSplit[2]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[0]);

        if (year < DateValidation.MIN_YEAR.getValue()
                || year > DateValidation.MAX_YEAR.getValue()) {
            return false;
        }
        if (month == 2 && day > DateValidation.MAX_DAY_FOR_FEBRUARY.getValue()) {
            return false;
        }
        if (month > DateValidation.MAX_MONTH.getValue()) {
            return false;
        }
        if (day > DateValidation.MAX_DAY.getValue()) {
            return false;
        }
        return true;
    }

    /**
     * Add event string.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String addEvent(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        String eventName = commandInput.getName();
        String date = commandInput.getDate();
        String description = commandInput.getDescription();
        User user = UserSingleton.getInstance().getUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user == null) {
            // iau userul din library.json
            user = Admin.getUsers().stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
        }
        if (user == null) {
            return "The username " + username + " doesn't exist.";
        }
        if (!(user instanceof Artist)) {
            return username + " is not an artist.";
        }
        if (!validDate(date)) {
            return "Event for " + username + " does not have a valid date.";
        }
        Artist artist = (Artist) user;
        // Verifică dacă există deja un eveniment cu același nume pentru acest artist
        if (artist.getEvents().stream().anyMatch(event -> event.getName().equals(eventName))) {
            return username + " has another event with the same name.";
        }

        Event newEvent = new Event();
        newEvent.setName(eventName);
        newEvent.setDescription(description);
        newEvent.setDate(date);
        artist.addEvent(newEvent);

        return username + " has added new event successfully.";
    }

    /**
     * Add merch string.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String addMerch(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        String merchName = commandInput.getName();
        String description = commandInput.getDescription();
        int price = commandInput.getPrice();
        User user = UserSingleton.getInstance().getUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user == null) {
            // iau userul din library.json
            user = Admin.getUsers().stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
        }
        if (user == null) {
            return "The username " + username + " doesn't exist.";
        }
        if (!(user instanceof Artist)) {
            return username + " is not an artist.";
        }
        Artist artist = (Artist) user;
        // Verifică dacă există deja un merch cu același nume pentru acest artist
        if (artist.getMerch().stream().anyMatch(merch -> merch.getName().equals(merchName))) {
            return username + " has merchandise with the same name.";
        }
        // verific pretul
        if (price < 0) {
            return "Price for merchandise can not be negative.";
        }
        Merch newMerch = new Merch();
        newMerch.setName(merchName);
        newMerch.setDescription(description);
        newMerch.setPrice(price);
        artist.getMerch().add(newMerch);

        return username + " has added new merchandise successfully.";
    }

    /**
     * Albumis loaded boolean.
     *
     * @param album the album
     * @param users the users
     * @return the boolean
     */
    public boolean albumisLoaded(final Album album, final List<User> users) {
        String albumName = album.getName();
        for (User user : users) {
            Player currentPlayer = user.getPlayer();
            // parcurg playerul utilizatorului curent
            if (currentPlayer != null && currentPlayer.getCurrentAudioFile() != null) {
                if (currentPlayer.getCurrentAudioFile().getName().equals(albumName)) {

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Songis loaded boolean.
     *
     * @param song  the song
     * @param users the users
     * @return the boolean
     */
    public boolean songisLoaded(final Song song, final List<User> users) {
        for (User user : users) {
            Player currentPlayer = user.getPlayer();
            if (currentPlayer != null && currentPlayer.getCurrentAudioFile() != null) {
                if (currentPlayer.getCurrentAudioFile().getName().equals(song.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check and set loaded album boolean.
     *
     * @param album the album
     * @return the boolean
     */
    public boolean checkAndSetLoadedAlbum(final Album album) {
        return (albumisLoaded(album, Admin.getUsers())
                || albumisLoaded(album, UserSingleton.getInstance().getUsers()));

    }
    /**
     * Check and set loaded song boolean.
     *
     * @param song the song
     * @return the boolean
     */
    public boolean checkAndSetLoadedSong(final Song song) {
        return (songisLoaded(song, Admin.getUsers())
                || songisLoaded(song, UserSingleton.getInstance().getUsers()));
    }

    /**
     * Remove album.
     *
     * @param album1 the album 1
     */
    public void removeAlbum(final Album album1) {
        for (Song song : album1.getSongs()) {
            if (checkAndSetLoadedSong(song)) {
                album1.setLoaded(true);
                return;
            }
        }
        if (checkAndSetLoadedAlbum(album1)) {
            album1.setLoaded(true);
            return;
        }
        // daca nu sterg albumul din Admin, UserSingleton si din lista de albume a artistului
        if (!(album1.isLoaded())) {
            Admin.getAlbums().remove(album1);
            Album finalAlbum = album1;
            UserSingleton.getInstance().getUsers().stream()
                    .filter(u -> u instanceof Artist)
                    .map(u -> (Artist) u)
                    .forEach(artist -> artist.getAlbums().remove(finalAlbum));
            Admin.getSongs().removeAll(album1.getSongs());
        }
    }

    /**
     * Remove album string.
     *
     * @param albumLocal    the album
     * @param userName the username
     * @return the string
     */
    public static String removeAlbum(final Album albumLocal, final String userName) {
        User user = Admin.returnUser(userName);
        if (user == null) {
            return "The username " + userName + " doesn't exist.";
        }
        if (!(user instanceof Artist)) {
            return userName + " is not an artist.";
        }
        if (albumLocal == null) {
            return userName + " doesn't have an album with the given name.";
        }
        Artist artist = (Artist) user;
        int ok = 0;
        for (Album album1 : artist.getAlbums()) {
            if (album1.getName().equals(albumLocal.getName())) {
                ok = 1;
                break;
            }
        }
        if (ok == 0) {
            return userName + " doesn't have an album with the given name.";
        }
        artist.removeAlbum(albumLocal);
        if (albumLocal.isLoaded()) {
            return userName + " can't delete this album.";
        }
        return userName + " has deleted the album successfully.";
    }

    /**
     * Remove event.
     *
     * @param event the event
     */
    public void removeEvent(final Event event) {
        events.remove(event);
    }

    /**
     * Remove event string.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String removeEvent(final CommandInput commandInput) {
        User user = UserSingleton.getInstance().getUsers().stream()
                .filter(u -> u.getUsername().equals(commandInput.getUsername()))
                .findFirst()
                .orElse(null);
        String eventName = commandInput.getName();
        // verific daca userul se afla in LISTA de utilizatori
        boolean isUserInList = false;
        for (User user1 : UserSingleton.getInstance().getUsers()) {
            if (user1.getUsername().equals(user.getUsername())) {
                isUserInList = true;
            }
        }
        if (!isUserInList) {
            return "The username " + user.getUsername() + " doesn't exist.";
        }
        if (!(user instanceof Artist)) {
            return user.getUsername() + " is not an artist.";
        }
        // caut evenimentul in lista de evenimente a utilizatorului
        Artist artist = (Artist) user;
        for (Event event : artist.getEvents()) {
            if (event.getName().equals(eventName)) {
                artist.removeEvent(event);
                return user.getUsername() + " deleted the event successfully.";
            }
        }
        return user.getUsername() + " doesn't have an event with the given name.";
    }

}
