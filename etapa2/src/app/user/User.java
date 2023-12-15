package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.AudioCollection;
import app.audio.Collections.Playlist;
import app.audio.Collections.PlaylistOutput;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import app.audio.Files.Song;

import app.audio.LibraryEntry;

import app.page.ArtistPage;
import app.page.HostPage;
import app.page.HomePage;
import app.page.LikedContentPage;
import app.page.Page;
import app.page.PageShowVisitor;

import app.player.Player;
import app.player.PlayerStats;

import app.searchBar.Filters;
import app.searchBar.SearchBar;
import app.utils.Enums;
import app.audio.Collections.Podcast;


import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User.
 */
public class User {
    /**
     * Gets username.
     *
     * @return the username
     */
// o lista cu toti utilizatorii normali
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Sets playlists.
     *
     * @param playlists the playlists
     */
    public void setPlaylists(final ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     * Gets liked songs.
     *
     * @return the liked songs
     */
    public ArrayList<Song> getLikedSongs() {
        return likedSongs;
    }

    /**
     * Gets followed playlists.
     *
     * @return the followed playlists
     */
    public ArrayList<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }
    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
    private Page currentPage;

    /**
     * Is current used boolean.
     *
     * @return the boolean
     */
    public boolean isCurrentUsed() {
        return isCurrentUsed;
    }

    /**
     * Sets current used.
     *
     * @param currentUsed the current used
     */
    public void setCurrentUsed(final boolean currentUsed) {
        isCurrentUsed = currentUsed;
    }

    /**
     * Gets current page.
     *
     * @return the current page
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets current page.
     *
     * @param currentPage the current page
     */
    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    public boolean isOnline() {
        return isOnline;
    }

    private boolean isCurrentUsed;

    @Getter
    private String username;
    @Getter
    private int age;
    @Getter
    private String city;
    @Getter
    private ArrayList<Playlist> playlists;
    @Getter
    private ArrayList<Song> likedSongs;
    @Getter
    private ArrayList<Playlist> followedPlaylists;
    private final Player player;
    private final SearchBar searchBar;
    private boolean isOnline = true;

    /**
     * Gets online.
     *
     * @return the online
     */
    public boolean getOnline() {
        return isOnline;
    }

    /**
     * Sets online.
     *
     * @param online the online
     */
    public void setOnline(final boolean online) {
        isOnline = online;
    }

    private boolean lastSearched;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public User(final String username, final int age, final String city) {
        this.username = username;
        this.age = age;
        this.city = city;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        lastSearched = false;
        isOnline = true;
        this.currentPage = new HomePage(this);
        this.isCurrentUsed = false;
    }

    /**
     * Change page.
     *
     * @param newPage the new page
     */
    public void changePage(final Page newPage) {
        this.currentPage = newPage;
    }


    /**
     * Search array list.
     *
     * @param filters the filters
     * @param type    the type
     * @return the array list
     */
    public ArrayList<String> search(final Filters filters, final String type) {
        searchBar.clearSelection();
        player.stop();

        lastSearched = true;
        ArrayList<String> results = new ArrayList<>();
        List<LibraryEntry> libraryEntries = searchBar.search(filters, type);

        for (LibraryEntry libraryEntry : libraryEntries) {
            results.add(libraryEntry.getName());
        }
        return results;
    }

    /**
     * Select string.
     *
     * @param itemNumber the item number
     * @return the string
     */
    public String select(final int itemNumber) {
        if (!lastSearched) {
            return "Please conduct a search before making a selection.";
        }

        lastSearched = false;

        LibraryEntry selected = searchBar.select(itemNumber);

        if (selected == null) {
            return "The selected ID is too high.";
        }
        // extrag numele utilizatorului din selected
        String userName = selected.getName();
        if (isArtist(userName)) {
            modifyOnArtistPage(selected.getName());
            return "Successfully selected %s's page.".formatted(selected.getName());
        }
        if (isHost(userName)) {
            // printare proprietar pagina
            modifyOnHostPage(selected.getName());
            return "Successfully selected %s's page.".formatted(selected.getName());
        }

        return "Successfully selected %s.".formatted(selected.getName());
    }

    /**
     * Modify on host page.
     *
     * @param userName the username
     */
    public void modifyOnHostPage(final String userName) {
        Host selectedHost = (Host) UserSingleton.getInstance().getUsers().stream()
                .filter(u -> u.getUsername().equals(userName) && u instanceof Host)
                .findFirst()
                .orElse(null);
        this.changePage(new HostPage(selectedHost));
    }

    /**
     * Modify on artist page.
     *
     * @param userName the username
     */
    public void modifyOnArtistPage(final String userName) {
        Artist selectedArtist = (Artist) UserSingleton.getInstance().getUsers().stream()
                .filter(u -> u.getUsername().equals(userName) && u instanceof Artist)
                .findFirst()
                .orElse(null);
        this.changePage(new ArtistPage(selectedArtist));
    }

    /**
     * Is host boolean.
     *
     * @param userName the username
     * @return the boolean
     */
    public final boolean isHost(final String userName) {
        List<Host> allHosts = UserSingleton.getInstance().getUsers().stream()
                .filter(user -> user instanceof Host)
                .map(user -> (Host) user)
                .collect(Collectors.toList());
        for (Host host : allHosts) {
            if (host.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is artist boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public final boolean isArtist(final String userName) {
        List<Artist> allArtists = UserSingleton.getInstance().getUsers().stream()
                .filter(user -> user instanceof Artist)
                .map(user -> (Artist) user)
                .collect(Collectors.toList());
        for (Artist artist : allArtists) {
            if (artist.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Load string.
     *
     * @return the string
     */
    public String load() {
        if (searchBar.getLastSelected() == null) {
            return "Please select a source before attempting to load.";
        }
        if (searchBar.getLastSelected() instanceof AudioCollection) {
            if (!searchBar.getLastSearchType().equals("song")
                    && ((AudioCollection) searchBar.getLastSelected()).getNumberOfTracks() == 0) {
                return "You can't load an empty audio collection!";
            }
        }
        player.setSource(searchBar.getLastSelected(), searchBar.getLastSearchType());
        searchBar.clearSelection();

        player.pause();

        return "Playback loaded successfully.";
    }

    /**
     * Play pause string.
     *
     * @return the string
     */
    public String playPause() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to pause or resume playback.";
        }

        player.pause();

        if (player.getPaused()) {
            return "Playback paused successfully.";
        } else {
            return "Playback resumed successfully.";
        }
    }

    /**
     * Repeat string.
     *
     * @return the string
     */
    public String repeat() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before setting the repeat status.";
        }

        Enums.RepeatMode repeatMode = player.repeat();
        String repeatStatus = "";

        switch (repeatMode) {
            case NO_REPEAT -> {
                repeatStatus = "no repeat";
            }
            case REPEAT_ONCE -> {
                repeatStatus = "repeat once";
            }
            case REPEAT_ALL -> {
                repeatStatus = "repeat all";
            }
            case REPEAT_INFINITE -> {
                repeatStatus = "repeat infinite";
            }
            case REPEAT_CURRENT_SONG -> {
                repeatStatus = "repeat current song";
            }
            default -> {
                repeatStatus = "";
            }
        }

        return "Repeat mode changed to %s.".formatted(repeatStatus);
    }

    /**
     * Shuffle string.
     *
     * @param seed the seed
     * @return the string
     */
    public String shuffle(final Integer seed) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before using the shuffle function.";
        }

        if (!player.getType().equals("playlist") && !player.getType().equals("album")) {
            return "The loaded source is not a playlist or an album.";
        }

        player.shuffle(seed);

        if (player.getShuffle()) {
            return "Shuffle function activated successfully.";
        }
        return "Shuffle function deactivated successfully.";
    }

    /**
     * Forward string.
     *
     * @return the string
     */
    public String forward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to forward.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipNext();

        return "Skipped forward successfully.";
    }

    /**
     * Backward string.
     *
     * @return the string
     */
    public String backward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please select a source before rewinding.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipPrev();

        return "Rewound successfully.";
    }

    /**
     * Like string.
     *
     * @return the string
     */
    public String like() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before liking or unliking.";
        }

        if (!player.getType().equals("song") && !player.getType().equals("playlist")
                && !player.getType().equals("album")) {
            return "Loaded source is not a song.";
        }

        Song song = (Song) player.getCurrentAudioFile();

        if (likedSongs.contains(song)) {
            likedSongs.remove(song);
            song.dislike();

            return "Unlike registered successfully.";
        }

        likedSongs.add(song);
        song.like();
        return "Like registered successfully.";
    }

    /**
     * Next string.
     *
     * @return the string
     */
    public String next() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        player.next();

        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        return "Skipped to next track successfully. The current track is %s."
                .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     * Check and set artist current used.
     */
    public void checkAndSetArtistCurrentUsed() {
        if (checkArtistUsage(Admin.getUsers())
                || checkArtistUsage(UserSingleton.getInstance().getUsers())) {
            this.setCurrentUsed(true);
        } else {
            this.setCurrentUsed(false);
        }
    }

    /**
     * Check podcast user.
     */
    public void checkPodcastUser() {
        if (checkPodcastUsage(Admin.getUsers())
                || checkPodcastUsage(UserSingleton.getInstance().getUsers())) {
            this.setCurrentUsed(true);
        } else {
            this.setCurrentUsed(false);
        }
    }

    /**
     * Check podcast user remove.
     *
     * @param podcast the podcast
     */
    public void checkPodcastUserRemove(final Podcast podcast) {
        if (checkPodcastcanRemove(Admin.getUsers(), podcast)
                || checkPodcastcanRemove(UserSingleton.getInstance().getUsers(), podcast)) {
            podcast.setLoadedPodcast(true);
        } else {
            podcast.setLoadedPodcast(false);
        }
    }

    /**
     * Check podcastcan remove boolean.
     *
     * @param users   the users
     * @param podcast the podcast
     * @return the boolean
     */
    public boolean checkPodcastcanRemove(final List<User> users, final Podcast podcast) {
        for (User user : users) {
            Player currentPlayer = user.getPlayer();
            if (currentPlayer != null && currentPlayer.getCurrentAudioFile() != null) {
                String podcastName = currentPlayer.getCurrentAudioFile().getName();
                if (podcast.getName().equals(podcastName)) {
                    return true;
                }

                List<Episode> episodes = podcast.getEpisodes();
                for (Episode episode : episodes) {
                    if (episode.getName().equals(podcastName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check podcast usage boolean.
     *
     * @param users the users
     * @return the boolean
     */
    public boolean checkPodcastUsage(final List<User> users) {
        for (User user : users) {
            Player currentPlayer = user.getPlayer();
            if (currentPlayer != null && currentPlayer.getCurrentAudioFile() != null) {
                String podcastName = currentPlayer.getCurrentAudioFile().getName();
                if (this instanceof Host) {
                    for (Podcast podcast : ((Host) this).getPodcastHost()) {
                        if (podcast.getName().equals(podcastName)) {
                            return true;
                        }
                        // verifica lista de episoade din lista curenta de podcasturi
                        List<Episode> episodes = podcast.getEpisodes();
                        for (Episode episode : episodes) {
                            if (episode.getName().equals(podcastName)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean checkUserPageUsed(final User user, final List<User> users) {
        // verific daca pagina userului este folosita
        for (User currentUser : users) {
            if (user instanceof Host && user != currentUser) {
                Page currentPageLocal = currentUser.getCurrentPage();

                if (currentPageLocal instanceof HostPage) {
                    HostPage hostPageLocal = (HostPage) currentPageLocal;
                    // obtin proprietarul paginii curent
                    User host = hostPageLocal.getHost();
                    if (host.getUsername().equals(user.getUsername())) {
                        return true;
                    }
                }
            }
            if (user instanceof Artist && user != currentUser) {
                Page currentPageLocal = currentUser.getCurrentPage();
                if (currentPageLocal instanceof ArtistPage) {
                    ArtistPage artistPage = (ArtistPage) currentPageLocal;
                    Artist artist = artistPage.getArtist();
                    if (artist != null && artist.getUsername().equals(user.getUsername())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Check user page used.
     */
    public void checkUserPageUsed() {
        if (checkUserPageUsed(this, Admin.getUsers())
                || checkUserPageUsed(this, UserSingleton.getInstance().getUsers())) {
            this.setCurrentUsed(true);
        } else {
            this.setCurrentUsed(false);
        }
    }

    private boolean checkArtistUsage(final List<User> users) {
        for (User user : users) {
            Player currentPlayer = user.getPlayer();
            if (currentPlayer != null && currentPlayer.getCurrentAudioFile() != null) {
                String songName = currentPlayer.getCurrentAudioFile().getName();
                if (this instanceof Artist) {
                    for (Album album : ((Artist) this).getAlbums()) {
                        for (Song song : album.getSongs()) {
                            if (song.getName().equals(songName)) {
                                return true;
                            }
                        }
                    }
                }
                if (this instanceof User) {
                    for (Playlist playlist : ((User) this).getPlaylists()) {
                        for (Song song : playlist.getSongs()) {
                            if (song.getName().equals(songName)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * Prev string.
     *
     * @return the string
     */
    public String prev() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before returning to the previous track.";
        }

        player.prev();
        return "Returned to previous track successfully. The current track is %s."
                .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     * Create playlist string.
     *
     * @param name      the name
     * @param timestamp the timestamp
     * @return the string
     */
    public String createPlaylist(final String name, final int timestamp) {
        if (playlists.stream().anyMatch(playlist -> playlist.getName().equals(name))) {
            return "A playlist with the same name already exists.";
        }

        playlists.add(new Playlist(name, username, timestamp));
        Admin.getPlaylists().add(new Playlist(name, username, timestamp));
        return "Playlist created successfully.";
    }

    /**
     * Add remove in playlist string.
     *
     * @param id the id
     * @return the string
     */
    public String addRemoveInPlaylist(final int id) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before adding to or removing from the playlist.";
        }

        if (player.getType().equals("podcast")) {
            return "The loaded source is not a song.";
        }

        if (id > playlists.size()) {
            return "The specified playlist does not exist.";
        }

        Playlist playlist = playlists.get(id - 1);

        if (playlist.containsSong((Song) player.getCurrentAudioFile())) {
            playlist.removeSong((Song) player.getCurrentAudioFile());
            return "Successfully removed from playlist.";
        }

        playlist.addSong((Song) player.getCurrentAudioFile());
        return "Successfully added to playlist.";
    }

    /**
     * Switch playlist visibility string.
     *
     * @param playlistId the playlist id
     * @return the string
     */
    public String switchPlaylistVisibility(final Integer playlistId) {
        if (playlistId > playlists.size()) {
            return "The specified playlist ID is too high.";
        }

        Playlist playlist = playlists.get(playlistId - 1);
        playlist.switchVisibility();

        if (playlist.getVisibility() == Enums.Visibility.PUBLIC) {
            return "Visibility status updated successfully to public.";
        }

        return "Visibility status updated successfully to private.";
    }

    /**
     * Show playlists array list.
     *
     * @return the array list
     */
    public ArrayList<PlaylistOutput> showPlaylists() {
        ArrayList<PlaylistOutput> playlistOutputs = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistOutputs.add(new PlaylistOutput(playlist));
        }

        return playlistOutputs;
    }

    /**
     * Follow string.
     *
     * @return the string
     */
    public String follow() {
        LibraryEntry selection = searchBar.getLastSelected();
        String type = searchBar.getLastSearchType();

        if (selection == null) {
            return "Please select a source before following or unfollowing.";
        }

        if (!type.equals("playlist")) {
            return "The selected source is not a playlist.";
        }

        Playlist playlist = (Playlist) selection;

        if (playlist.getOwner().equals(username)) {
            return "You cannot follow or unfollow your own playlist.";
        }

        if (followedPlaylists.contains(playlist)) {
            followedPlaylists.remove(playlist);
            playlist.decreaseFollowers();

            return "Playlist unfollowed successfully.";
        }

        followedPlaylists.add(playlist);
        playlist.increaseFollowers();


        return "Playlist followed successfully.";
    }

    /**
     * Gets player stats.
     *
     * @return the player stats
     */
    public PlayerStats getPlayerStats() {
        return player.getStats();
    }

    /**
     * Show preferred songs array list.
     *
     * @return the array list
     */
    public ArrayList<String> showPreferredSongs() {
        ArrayList<String> results = new ArrayList<>();
        for (AudioFile audioFile : likedSongs) {
            results.add(audioFile.getName());
        }

        return results;
    }

    /**
     * Switch connection status string.
     *
     * @param user  the user
     * @return the string
     */
    public String switchConnectionStatus(final User user) {

        if (!(user instanceof Artist) && !(user instanceof Host)) {
            if (user.getOnline()) {
                user.setOnline(false);
            } else {
                user.setOnline(true);
            }
            return user.getUsername() + " has changed status successfully.";
        } else {
            return user.getUsername() + " is not a normal user.";
        }
    }

    /**
     * Gets preferred genre.
     *
     * @return the preferred genre
     */
    public String getPreferredGenre() {
        String[] genres = {"pop", "rock", "rap"};
        int[] counts = new int[genres.length];
        int mostLikedIndex = -1;
        int mostLikedCount = 0;

        for (Song song : likedSongs) {
            for (int i = 0; i < genres.length; i++) {
                if (song.getGenre().equals(genres[i])) {
                    counts[i]++;
                    if (counts[i] > mostLikedCount) {
                        mostLikedCount = counts[i];
                        mostLikedIndex = i;
                    }
                    break;
                }
            }
        }

        String preferredGenre = mostLikedIndex != -1 ? genres[mostLikedIndex] : "unknown";
        return "This user's preferred genre is %s.".formatted(preferredGenre);
    }

    /**
     * Simulate time.
     *
     * @param time the time
     */
    public void simulateTime(final int time) {
        if (this.isOnline) {
            player.simulatePlayer(time);
        }
    }

    /**
     * Display current page content string.
     *
     * @return the string
     */
// Metodă pentru afișarea conținutului paginii curente
    public String displayCurrentPageContent() {
        PageShowVisitor visitor = new PageShowVisitor(this);
        currentPage.accept(visitor);
        return visitor.getResult();
    }

    /**
     * Print current page string.
     *
     * @return the string
     */
    public String printCurrentPage() {
        return this.displayCurrentPageContent();
    }

    /**
     * Change page string.
     *
     * @param user     the user
     * @param nextPage the next page
     * @return the string
     */
    public String changePage(final User user, final String nextPage) {

        // Verifică dacă pagina există
        if (!(nextPage.equals("Home")) && !(nextPage.equals("LikedContent"))) {
            return username + " is trying to access a non-existent page.";
        }
        if (nextPage.equals("Home")) {
            user.changePage(new HomePage(this));
        } else if (nextPage.equals("LikedContent")) {
            user.changePage(new LikedContentPage(this));
        }

        // Returnează mesajul de succes
        return username + " accessed " + nextPage + " successfully.";
    }
}
