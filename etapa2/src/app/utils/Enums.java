package app.utils;

public class Enums { // diferite enumuri, le-am gurpat pe toate intr-un loc
    public enum Genre {
        POP,
        ROCK,
        RAP
    } // etc

    public enum Visibility {
        PUBLIC,
        PRIVATE
    }

    public enum SearchType {
        SONG,
        PLAYLIST,
        PODCAST
    }

    public enum RepeatMode {
        REPEAT_ALL, REPEAT_ONCE, REPEAT_INFINITE, REPEAT_CURRENT_SONG, NO_REPEAT,
    }

    public enum PlayerSourceType {
        LIBRARY, PLAYLIST, PODCAST, ALBUM;
    }
    public enum DateValidation {
        MIN_YEAR(1900),
        MAX_YEAR(2023),
        MAX_MONTH(12),
        MAX_DAY(31),
        MAX_DAY_FOR_FEBRUARY(28);

        private final int value;

        DateValidation(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
