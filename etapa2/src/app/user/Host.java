package app.user;
import app.Admin;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.page.HostPage;

import fileio.input.CommandInput;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Host.
 */
public class Host extends User {
    private List<Podcast> podcastHost = new ArrayList<>();
    private List<Announcement> announcement = new ArrayList<>();

    /**
     * Gets announcement.
     *
     * @return the announcement
     */
    public List<Announcement> getAnnouncement() {
        return announcement;
    }

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public Host(final String username, final int age, final String city) {
        super(username, age, city);
        this.setCurrentPage(new HostPage(this));
    }

    /**
     * The type Announcement.
     */
    public static class Announcement {
        private String name;
        private String description;

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
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return "host";
    }

    /**
     * Gets podcast host.
     *
     * @return the podcast host
     */
    public List<Podcast> getPodcastHost() {
        return this.podcastHost;
    }

    /**
     * Add podcast.
     *
     * @param name        the name
     * @param description the description
     * @param episodes    the episodes
     */
    public void addPodcast(final String name, final String description,
                           final List<Episode> episodes) {
            Podcast podcast = new Podcast(name, description, episodes);
            List<Podcast> podcasts = this.getPodcastHost();
            Admin.addPodcast(podcast);
            podcasts.add(podcast);

    }
    private void removeAnnouncement(final String name) {
        List<Announcement> announcementsLocal = this.getAnnouncement();
        for (Announcement announcementLocal : announcementsLocal) {
            if (announcementLocal.getName().equals(name)) {
                announcementsLocal.remove(announcementLocal);
                break;
            }
        }
    }

    /**
     * Remove announcement string.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String removeAnnouncement(final CommandInput commandInput) {
        String userName = commandInput.getUsername();
        String announcementNameLocal = commandInput.getName();
        User user = UserSingleton.getInstance().getUsers().stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst()
                .orElse(null);
        if (user == null) {
            return "The username " + userName + " doesn't exist.";
        } else if (!(user instanceof Host)) {
            return userName + " is not a host.";
        } else {
            Host host = (Host) user;
            List<Announcement> announcements = host.getAnnouncement();
            for (Announcement announcement : announcements) {
                if (announcement.getName().equals(announcementNameLocal)) {
                    host.removeAnnouncement(announcementNameLocal);
                    return userName + " has successfully deleted the announcement.";
                }
            }
            return userName + " has no announcement with the given name.";
        }
    }

    /**
     * Add announcement string.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String addAnnouncement(final CommandInput commandInput) {
        String userName = commandInput.getUsername();
        String announcementName = commandInput.getName();
        String description = commandInput.getDescription();
        User user = Admin.returnUser(userName);
        if (user == null) {
                return "The username " + userName + " doesn't exist.";
        } else if (!(user instanceof Host)) {
            return userName + " is not a host.";
        } else {
            Host host = (Host) user;
            List<Announcement> announcements = host.getAnnouncement();
            for (Announcement announcement : announcements) {
                if (announcement.getName().equals(announcementName)) {
                    return userName + " already has an announcement with the same name";
                }
            }
            Announcement announcement = new Announcement();
            announcement.setName(announcementName);
            announcement.setDescription(description);
            announcements.add(announcement);
            return userName +  " has successfully added new announcement.";
        }
    }
    private void removePodcast(final String name) {
        List<Podcast> podcasts = this.getPodcastHost();
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                podcasts.remove(podcast);
                break;
            }
        }
    }
    /**
     * Remove podcast string.
     *
     * @param commandInput the command input
     * @return the string
     */
    public static String removePodcast(final CommandInput commandInput) {
        String username = commandInput.getUsername();
        User user = UserSingleton.getInstance().getUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user == null) {
            return "The username " + username + " doesn't exist.";
        }
        if (!(user instanceof Host)) {
            return username + " is not an host.";
        }
        Host host = (Host) user;

        List<Podcast> podcasts = host.getPodcastHost();
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(commandInput.getName())) {
                // verific daca podcastul este incarcat
                host.checkPodcastUserRemove(podcast);
                if (podcast.isLoadedPodcast()) {
                    return username + " can't delete this podcast.";
                }
                // sterg podcastul
                host.removePodcast(commandInput.getName());
                return username + " deleted the podcast successfully.";
            }
        }
        return username + " doesn't have a podcast with the given name.";
    }
}
