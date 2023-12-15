package app.audio.Files;

import app.audio.LibraryEntry;
import lombok.Getter;
import app.user.User;
@Getter
public abstract class AudioFile extends LibraryEntry {
    /**
     * Gets creator.
     *
     * @return the creator
     */
    public Integer getDuration() {
        return duration;
    }

    private User creator;

    private final Integer duration;

    public AudioFile(final String name, final Integer duration) {
        super(name);
        this.duration = duration;
    }
}
