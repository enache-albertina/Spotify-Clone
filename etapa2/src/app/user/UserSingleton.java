package app.user;

import java.util.ArrayList;

public class UserCoordinator {
    private ArrayList<User> users;

    public UserCoordinator() {
        users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String addUsers(String type, int age, String username, String city) {
        System.out.println("Utilizatorii adăugați sunt:\n");
        for (User user : users) {
            System.out.println(user.getUsername());
            if (user.getUsername().equals(username)) {
                return "The username " + username + " is already taken.";
            }
        }
        System.out.println("\n");
        switch (type) {
            case "User":
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

    public void reset() {
        users.clear();
    }
}
