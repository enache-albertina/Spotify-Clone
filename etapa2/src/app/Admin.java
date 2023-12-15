package app;
import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Collections.AlbumOutput;
import app.audio.Collections.PodcastOutput;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.User;
import app.user.UserSingleton;
import fileio.input.CommandInput;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import java.util.Iterator;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import app.user.Artist;
import java.util.stream.Collectors;

import app.user.Host;


/**
 * The type Admin.
 */
public final class Admin {
    private static final int MAX_ATTEMPTS = 5;
    private static List<User> users = new ArrayList<>();
    private static List<Artist> artists = new ArrayList<>();

    // Alte câmpuri și metode...

    /**
     * Gets artists.
     *
     * @return the artists
     */
    public static List<Artist> getArtists() {
        return artists;
    }


    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();
    private static List<Album> albums = new ArrayList<>();
    private static int timestamp = 0;
    private static final int LIMIT = 5;

    private Admin() {
    }

    /**
     * Gets albums.
     *
     * @return the albums
     */
    public static List<Album> getAlbums() {
        return albums;
    }

    /**
     * Sets albums.
     *
     * @param albums the albums
     */
    public static void setAlbums(final List<Album> albums) {
        Admin.albums = albums;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public static List<User> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }

    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }


    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                                         episodeInput.getDuration(),
                                         episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public static List<Song> getSongs() {
        return songs;
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * Adds a given Podcast instance to the existing collection of podcasts.
     * <p>
     * This method takes a Podcast object as a parameter and adds it to a static collection
     * named 'podcasts'. It's assumed that 'podcasts' is a static field in the class
     * where this method is defined and is capable of storing Podcast objects.
     *
     * @param podcast the Podcast object to be added to the collection. Should not be null.
     */
    public static void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        for (User user : UserSingleton.getInstance().getUsers()) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }
    /**
     * Check if is user boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public static boolean checkIfIsUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        for (User user : UserSingleton.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return user user.
     *
     * @param userName the username
     * @return the user
     */
    public static User returnUser(final String userName) {
        User user1 = null;
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        for (User user : UserSingleton.getInstance().getUsers()) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return user1;
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public static User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
        for (User user : UserSingleton.getInstance().getUsers()) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * Retrieves the names of the top 5 albums.
     * <p>
     * This method first obtains a list of albums using the Admin.getAlbums() method.
     * It then sorts these albums primarily by the number of likes (in descending order),
     * and secondarily by their names in alphabetical order, in case of a tie in the number
     * of likes.
     * <p>
     * After sorting, the method extracts and returns the names of the top 5 albums. If there are
     * fewer than 5 albums, it returns the names of all available albums.
     * <p>
     * Note: This method assumes that the Admin.getAlbums() method provides a non-null list
     * of albums.
     * The method will perform unexpectedly if the list contains null Album objects or if the
     * Album objects have null names.
     *
     * @return A list of strings containing the names of the top 5 albums.
     * The list size will be less than 5 if there are not enough albums.
     */
    public static List<String> get5Albums() {
        List<Album> localAlbums = Admin.getAlbums();
        List<Album> sortedAlbums = localAlbums.stream()
                .sorted((a1, a2) -> {
                    if (a1.getNumberofLikes() == a2.getNumberofLikes()) {
                        return a1.getName().compareTo(a2.getName());
                    }
                    return a2.getNumberofLikes() - a1.getNumberofLikes();
                })
                .collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        int size = Math.min(MAX_ATTEMPTS, sortedAlbums.size());
        for (int i = 0; i < size; i++) {
            result.add(sortedAlbums.get(i).getName());
        }
        return result;
    }

    /**
     * Gets 5 artists.
     *
     * @return the 5 artists
     */
    public static List<String> get5Artists() {
        List<Artist> localArtists = UserSingleton.getInstance().findArtists();
        localArtists.sort(Comparator.comparingInt(Artist::getNumberOfAlbumsLikes).reversed());
        List<String> result = new ArrayList<>();
        int size = Math.min(MAX_ATTEMPTS, localArtists.size());
        for (int i = 0; i < size; i++) {
            result.add(localArtists.get(i).getUsername());
        }
        return result;
    }


    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }
    // lista de albume


    /**
     * Reset.
     */
    public static void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
        timestamp = 0;
    }

    /**
     * Adds a new user with specified details to the system.
     * <p>
     * Checks if the username of the given user is already in use. If the username is unique,
     * the user is added and a success message is returned. If the username is already taken,
     * an appropriate message is returned.
     *
     * @param user The User object to be added. Should contain valid username, city, and age.
     * @param type The type of the user as a String.
     * @return A String indicating whether the user was successfully added or if
     * the username was already taken.
     */
    public static String addUser(final User user, final String type) {
        String username = user.getUsername();
        String city = user.getCity();
        int age = user.getAge();
        if (UserSingleton.getInstance().getUsers().stream().anyMatch(
                u -> u.getUsername().equals(username))) {
            return "The username " + username + " is already taken.";
        }
        List<User> allUsers = Admin.getUsers();
        for (User user1 : allUsers) {
            if (user1.getUsername().equals(username)) {
                return "The username " + username + " is already taken.";
            }
        }
        return UserSingleton.getInstance().addUsers(type, age, username, city);
    }

    /**
     * Retrieves and returns a list of albums for a specified artist.
     * <p>
     * Extracts the username from the provided CommandInput and finds the corresponding
     * Artist object.
     * If the artist is found, their albums are converted into AlbumOutput objects
     * and compiled into a list.
     * If the artist is not found, returns an empty list.
     *
     * @param commandInput The CommandInput containing the username of the artist.
     * @return An ArrayList of AlbumOutput objects representing the albums of the specified artist.
     */
    public static ArrayList<AlbumOutput> showAlbums(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        Artist user = UserSingleton.getInstance().getUsers().stream()
                .filter(artist -> artist.getUsername().equals(username))
                .map(artist -> (Artist) artist)
                .findFirst()
                .orElse(null);
        ArrayList<AlbumOutput> localAlbums = new ArrayList<>();

        Artist artist = (Artist) user;
        for (Album album : artist.getAlbums()) {
            localAlbums.add(new AlbumOutput(album));
        }
        return localAlbums;

    }

    /**
     * Add list.
     *
     * @param list1 the list 1
     * @param list2 the list 2
     */
// functie care adauga dintr-o lista in alta
    public static void addList(final List<String> list1, final List<String> list2) {
        for (String string : list2) {
            list1.add(string);
        }
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public static ArrayList<String> getAllUsers() {
        ArrayList<String> allUsers = new ArrayList<>();
        ArrayList<String> allArtists = new ArrayList<>();
        ArrayList<String> allNormalUsers = new ArrayList<>();
        ArrayList<String> allHosts = new ArrayList<>();
        for (User user : Admin.getUsers()) {
            allUsers.add(user.getUsername());
        }
        for (User user : UserSingleton.getInstance().getUsers()) {
            // daca userul nu se afla deja in lista
            if (allUsers.contains(user.getUsername())) {
                continue;
            }
            if (user instanceof Artist) {
                allArtists.add(user.getUsername());
            } else if (user instanceof Host) {
                allHosts.add(user.getUsername());
            } else {
                allNormalUsers.add(user.getUsername());
            }
        }
        allUsers.addAll(allNormalUsers);
        allUsers.addAll(allArtists);
        allUsers.addAll(allHosts);
        return allUsers;
    }

    /**
     * Delete user string.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String deleteUser(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        User user = getUserOrFind(username);

        if (user == null) {
            return "The username " + username + " doesn't exist!";
        }
        user.checkPodcastUser();
        if (user.isCurrentUsed()) {
            return username + " can't be deleted.";
        }
        user.checkAndSetArtistCurrentUsed();

        if (user.isCurrentUsed()) {
            return username + " can't be deleted.";
        }
        user.checkUserPageUsed();
        if (user.isCurrentUsed() && (user instanceof Host || user instanceof Artist)) {
            return username + " can't be deleted.";
        }

        if (user instanceof Artist) {
            Artist artist = (Artist) user;
            removeArtistSongsFromLikedLists(artist);
            removeArtistAlbums(artist);
        }
        removeUserSongs(username);
        UserSingleton.getInstance().getUsers().remove(user);
        ArrayList<Playlist> playlists = user.getPlaylists();
        for (User user1 : Admin.getUsers()) {
            if (user1.getFollowedPlaylists().containsAll(playlists)) {
                checkIfUserFollowsPlaylist(user1);
            }
        }
        removePlaylistFromFollowedLists(playlists);

        return username + " was successfully deleted.";
    }


    private static User getUserOrFind(final String username) {
        User user = Admin.getUser(username);
        if (user == null) {
            user = UserSingleton.getInstance().findUser(username);
        }
        return user;
    }

    private static void removeArtistSongsFromLikedLists(final Artist artist) {
        List<Song> artistSongs = getAllSongsFromArtistAlbums(artist);
        removeSongsFromUserLikedLists(artistSongs, Admin.getUsers());
        removeSongsFromUserLikedLists(artistSongs, UserSingleton.getInstance().getUsers());
    }

    private static List<Song> getAllSongsFromArtistAlbums(final Artist artist) {
        List<Song> artistSongs = new ArrayList<>();
        for (Album album : artist.getAlbums()) {
            artistSongs.addAll(album.getSongs());
        }
        return artistSongs;
    }

    private static void removeSongsFromUserLikedLists(final List<Song> songsToRemove,
                                                      final List<User> localUsers) {
        for (User user : localUsers) {
            List<Song> likedSongs = user.getLikedSongs();
            likedSongs.removeAll(songsToRemove);
        }
    }
    private static void removeArtistAlbums(final Artist artist) {
        for (Album album : artist.getAlbums()) {
            Admin.getAlbums().remove(album);
        }
    }

    private static void removeUserSongs(final String username) {
        Iterator<Song> songIterator = Admin.getSongs().iterator();
        while (songIterator.hasNext()) {
            Song song = songIterator.next();
            if (song.getArtist().equals(username)) {
                songIterator.remove();
            }
        }
    }

    /**
     * Show podcasts array list.
     *
     * @param commandInput the command input
     * @return the array list
     */
    public static  ArrayList<PodcastOutput> showPodcasts(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        Host user = UserSingleton.getInstance().getUsers().stream()
                .filter(host -> host.getUsername().equals(username))
                .map(host -> (Host) host)
                .findFirst()
                .orElse(null);
        ArrayList<PodcastOutput> localPodcasts = new ArrayList<>();

        Host host = (Host) user;
        for (Podcast podcast : host.getPodcastHost()) {
            localPodcasts.add(new PodcastOutput(podcast));
        }
        return localPodcasts;
    }

    /**
     * Check if user follows playlist.
     *
     * @param user the user
     */
// verific daca utilizatorul a dat follow la un playlist
    public static void checkIfUserFollowsPlaylist(final User user) {
        List<Playlist> playlists = Admin.getPlaylists();
        // verific daca userul a dat follow la un playlist
        for (Playlist playlist : playlists) {
            if (user.getFollowedPlaylists().contains(playlist)) {
                // caut userul caruia ii apartine playlistul
                User user1 = getUserwhoOwnsPlaylist(playlist);
                // sterg followerul din lista de followers a playlistului
                user1.getFollowedPlaylists().remove(playlist);
                // scad numarul de followers al playlistului
                playlist.setFollowers(playlist.getFollowers() - 1);
            }
        }
    }

    /**
     * Gets userwho owns playlist.
     *
     * @param playlist the playlist
     * @return the userwho owns playlist
     */
    public static User getUserwhoOwnsPlaylist(final Playlist playlist) {
        List<User> usersLocal = Admin.getUsers();
        for (User user : usersLocal) {
            if (user.getPlaylists().contains(playlist)) {
                return user;
            }
        }
        for (User user : UserSingleton.getInstance().getUsers()) {
            if (user.getPlaylists().contains(playlist)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Remove playlist from followed lists.
     *
     * @param playlists the playlists
     */
    public static void removePlaylistFromFollowedLists(final ArrayList<Playlist> playlists) {
        for (Playlist playlist : playlists) {
            removePlaylistFromUsers(Admin.getUsers(), playlist);
            removePlaylistFromUsers(UserSingleton.getInstance().getUsers(), playlist);
        }
    }

    private static void removePlaylistFromUsers(final List<User> usersLocal,
                                                final Playlist playlist) {
        for (User user : usersLocal) {
            Iterator<Playlist> iterator = user.getFollowedPlaylists().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(playlist)) {
                    iterator.remove();
                }
            }
        }
    }

}
