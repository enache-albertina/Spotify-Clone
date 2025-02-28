package app.player;

import lombok.Getter;

/**
 * The type Podcast bookmark.
 */
@Getter
public final class PodcastBookmark {
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    private final String name;
    private final int id;
    private final int timestamp;

    /**
     * Instantiates a new Podcast bookmark.
     *
     * @param name      the name
     * @param id        the id
     * @param timestamp the timestamp
     */
    public PodcastBookmark(final String name,
                           final int id,
                           final int timestamp) {
        this.name = name;
        this.id = id;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PodcastBookmark{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", timestamp=" + timestamp
                + '}';
    }
}
