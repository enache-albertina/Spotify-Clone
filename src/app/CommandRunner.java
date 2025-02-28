package app;
import app.audio.Collections.Album;

import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Collections.PlaylistOutput;
import app.audio.Collections.AlbumOutput;
import app.audio.Collections.PodcastOutput;



import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.user.User;
import app.user.UserSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;
import app.user.Host;
import fileio.input.EpisodeInput;


import java.util.ArrayList;

import java.util.List;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    private CommandRunner() {
    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        // daca utilizatorul nu e online
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (!user.getOnline()) {
           // ObjectNode offlineNode = objectMapper.createObjectNode();
            objectNode.put("message", user.getUsername() + " is offline.");
            objectNode.set("results", objectMapper.createArrayNode()); // Lista vidă
            return objectNode;
        }

        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();

        ArrayList<String> results = user.search(filters, type);
        String message = "Search returned " + results.size() + " results";


        objectNode.put("message", message);
        objectNode.set("results", objectMapper.valueToTree(results));

        return objectNode;
    }


    /**
     * Select object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode select(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.select(commandInput.getItemNumber());

       return createSuccessNode(commandInput, message);
    }

    /**
     * Load object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode load(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (user != null) {
            if (!(user.getOnline())) {
                return notOnline(commandInput);
            }
        }
        String message = user.load();

        return createSuccessNode(commandInput, message);
    }

    /**
     * Play pause object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        if (!user.getOnline()) {
            return notOnline(commandInput);
        }
        String message = user.playPause();

        return createSuccessNode(commandInput, message);
    }

    /**
     * Repeat object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.repeat();
        return createSuccessNode(commandInput, message);
    }

    /**
     * Shuffle object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        Integer seed = commandInput.getSeed();
        String message = user.shuffle(seed);

        return createSuccessNode(commandInput, message);
    }

    /**
     * Forward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.forward();
       return createSuccessNode(commandInput, message);
    }

    /**
     * Backward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.backward();

       return createSuccessNode(commandInput, message);
    }

    /**
     * Like object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode like(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }

        String message = user.like();
        return createSuccessNode(commandInput, message);
    }

    /**
     * Next object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode next(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.next();
       return createSuccessNode(commandInput, message);
    }

    /**
     * Prev object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.prev();
        return createSuccessNode(commandInput, message);
    }

    /**
     * Create playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.createPlaylist(commandInput.getPlaylistName(),
                                             commandInput.getTimestamp());
        return createSuccessNode(commandInput, message);
    }

    /**
     * Add remove in playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());
        return createSuccessNode(commandInput, message);
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        return createSuccessNode(commandInput, message);
    }

    /**
     * Show playlists object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Follow object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.follow();
        return createSuccessNode(commandInput, message);
    }

    /**
     * Status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode status(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());

        PlayerStats stats = user.getPlayerStats();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     * Show liked songs object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Switch connection status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        String message;
        if (!Admin.checkIfIsUser(commandInput.getUsername())) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else {
            User user = Admin.returnUser(commandInput.getUsername());

            message = user.switchConnectionStatus(user);
        }

        return createSuccessNode(commandInput, message);
    }

    /**
     * Add user object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addUser(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String username = commandInput.getUsername();
        String type = commandInput.getType();
        int age = commandInput.getAge();
        String city = commandInput.getCity();
        User newUser = new User(username, age, city);
        String message = Admin.addUser(newUser, type);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", objectMapper.valueToTree(message));

        return objectNode;
    }

    /**
     * Gets online users.
     *
     * @param commandInput the command input
     * @return the online users
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        List<User> users = Admin.getUsers();
        List<String> onlineUsers = new ArrayList<>();
        for (User user1 : users) {
            if (user1.getOnline()) {
                onlineUsers.add(user1.getUsername());
            }
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));
        return objectNode;

    }

    /**
     * Not online object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode  notOnline(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.getUsername() + " is offline.";

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", objectMapper.valueToTree(message));

        return objectNode;
    }

    /**
     * Add album object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        String message = Artist.addAlbum(commandInput);
        return createSuccessNode(commandInput, message);
    }

    /**
     * Show albums object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ArrayList<AlbumOutput> albums = Admin.showAlbums(commandInput);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * Prints the current page details for a specified user.
     * <p>
     * Retrieves a User object based on the username from the CommandInput. If the user is not found
     * or not online, an appropriate error is returned. Otherwise, returns the current page details
     * of the user.
     *
     * @param commandInput The CommandInput containing user's username and other command details.
     * @return An ObjectNode containing the page details or an error message.
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        if (!(user.getOnline())) {
            return notOnline(commandInput);
        }
        String message = user.printCurrentPage();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", objectMapper.valueToTree(message));

        return objectNode;
    }

    /**
     * Add event object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        String message = Artist.addEvent(commandInput);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", objectMapper.valueToTree(message));

        return objectNode;
    }

    /**
     * Add merch object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        String message = Artist.addMerch(commandInput);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", objectMapper.valueToTree(message));

        return objectNode;
    }

    /**
     * Gets all users.
     *
     * @param commandInput the command input
     * @return the all users
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        ArrayList<String> users = Admin.getAllUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(users));

        return objectNode;
    }

    /**
     * Delete user object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        String message = Admin.deleteUser(commandInput);
        return createSuccessNode(commandInput, message);
    }

    /**
     * Add podcast object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        String message;
        User user = UserSingleton.getInstance().findUser(commandInput.getUsername());
        if (user == null) {
            return createErrorNode("The username " + commandInput.getUsername()
                    + " doesn't exist.");
        }
        if (!(user instanceof Host)) {

            message = commandInput.getUsername() + " is not a host.";

        } else if (hasAnotherPodcastSameName((Host) user, commandInput.getName())) {

            message = commandInput.getUsername() + " has another podcast with the same name.";
        } else {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : commandInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(), episodeInput.getDuration(),
                        episodeInput.getDescription()));
            }


            Host host = (Host) user;
            message = host.getUsername() + " has added new podcast successfully.";
            host.addPodcast(
                    commandInput.getName(),
                    commandInput.getDescription(),
                    episodes
            );
        }
        return createSuccessNode(commandInput, message);
    }
    private static boolean hasAnotherPodcastSameName(final Host host, final String podcastName) {
        // parcurg lista de podcasturi a hostului
        for (Podcast podcast : host.getPodcastHost()) {
            if (podcast.getName().equals(podcastName)) {
                return true;
            }
        }
        return false;
    }

    private static ObjectNode createErrorNode(final String errorMessage) {
        ObjectNode errorNode = objectMapper.createObjectNode();
        errorNode.put("error", errorMessage);
        return errorNode;
    }

    private static ObjectNode createSuccessNode(final CommandInput commandInput,
                                                final String message) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Add announcement object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        String message = Host.addAnnouncement(commandInput);

        return createSuccessNode(commandInput, message);
    }

    /**
     * Remove announcement object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        String message = Host.removeAnnouncement(commandInput);
        return createSuccessNode(commandInput, message);
    }

    /**
     * Show podcasts object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        ArrayList<PodcastOutput> podcasts = Admin.showPodcasts(commandInput);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(podcasts));

        return objectNode;
    }

    /**
     * Remove album object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        String albumName = commandInput.getName();
        Album album = Admin.getAlbums().stream()
                .filter(a -> a.getName().equals(albumName))
                .findFirst()
                .orElse(null);
        // obtine artistul
        Artist artist = Admin.getArtists().stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst()
                .orElse(null);


        String message = artist.removeAlbum(album, username);

        return createSuccessNode(commandInput, message);
    }

    /**
     * Change page object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        User user = Admin.returnUser(commandInput.getUsername());
        String nextPage = commandInput.getNextPage();
        String message = user.changePage(user, nextPage);
        return createSuccessNode(commandInput, message);
    }

    /**
     * Remove podcast object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        String message = Host.removePodcast(commandInput);
        return createSuccessNode(commandInput, message);
    }

    /**
     * Remove event object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        String message = Artist.removeEvent(commandInput);
        return createSuccessNode(commandInput, message);
    }

    /**
     * Gets top 5 albums.
     *
     * @param commandInput the command input
     * @return the top 5 albums
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        List<String> albums = Admin.get5Albums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * Gets top 5 artists.
     *
     * @param commandInput the command input
     * @return the top 5 artists
     */
    public static ObjectNode getTop5Artists(final CommandInput commandInput) {
        List<String> artists = Admin.get5Artists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(artists));

        return objectNode;
    }
}
