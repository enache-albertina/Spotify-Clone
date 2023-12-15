package app.audio.Collections;

import java.util.ArrayList;

/**
 * The type Podcast output.
 */
public class PodcastOutput {
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
     * Gets episodes.
     *
     * @return the episodes
     */
    public ArrayList<String> getEpisodes() {
        return episodes;
    }

    private String name;
    private final ArrayList<String> episodes;

    /**
     * Instantiates a new Podcast output.
     *
     * @param podcast the podcast
     */
    public PodcastOutput(final Podcast podcast) {
        this.name = podcast.getName();
        this.episodes = new ArrayList<>();
        for (int i = 0; i < podcast.getEpisodes().size(); i++) {
            episodes.add(podcast.getEpisodes().get(i).getName());
        }
    }
}
