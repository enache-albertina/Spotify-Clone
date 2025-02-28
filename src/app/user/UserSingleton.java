package app.user;

import java.util.ArrayList;

import java.util.List;

/**
 * The type User singleton.
 */
public final class UserSingleton {
    private static UserSingleton instance = null;
    private static ArrayList<User> users;
    private UserSingleton() {
        users = new ArrayList<>();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public static void setUsers(final List<User> userInputList) {
        users = new ArrayList<>();
        for (User userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }
    /**
     * Gets users.
     *
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Add users string.
     *
     * @param type     the type
     * @param age      the age
     * @param username the username
     * @param city     the city
     * @return the string
     */
    public String  addUsers(final String type, final int age,
                            final String username, final String city) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return "The username " + username + " is already taken.";
            }
        }
        switch (type) {
            case "user":
                users.add(new User(username, age, city));
                break;
            case "artist":
                users.add(new Artist(username, age, city));
                break;
            case "host":
                users.add(new Host(username, age, city));
                break;
            default:
                return "Invalid user type.";
        }
        return "The username " + username + " has been added successfully.";
    }

    /**
     * Reset.
     */
    public void reset() {
        users.clear();
    }

    /**
     * Find user user.
     *
     * @param username the username
     * @return the user
     */
    public User findUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Find artists list.
     *
     * @return the list
     */
    public List<Artist> findArtists() {
        List<Artist> artists = new ArrayList<>();
        for (User user : users) {
            if (user.getClass().getSimpleName().equals("Artist")) {
                artists.add((Artist) user);
            }
        }
        return artists;
    }
}

