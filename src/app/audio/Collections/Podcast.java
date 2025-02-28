package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import java.util.List;

/**
 * The type Podcast.
 */
public final class Podcast extends AudioCollection {
    private final List<Episode> episodes;

    /**
     * Is loaded podcast boolean.
     *
     * @return the boolean
     */
    public boolean isLoadedPodcast() {
        return isLoadedPodcast;
    }

    /**
     * Sets loaded podcast.
     *
     * @param loadedPodcast the loaded podcast
     */
    public void setLoadedPodcast(final boolean loadedPodcast) {
        isLoadedPodcast = loadedPodcast;
    }

    private boolean isLoadedPodcast;

    /**
     * Instantiates a new Podcast.
     *
     * @param name     the name
     * @param owner    the owner
     * @param episodes the episodes
     */
    public Podcast(final String name, final String owner, final List<Episode> episodes) {
        super(name, owner);
        this.episodes = episodes;
        this.isLoadedPodcast = false;
    }

    /**
     * Gets episodes.
     *
     * @return the episodes
     */
    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public int getNumberOfTracks() {
        return episodes.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return episodes.get(index);
    }
}
