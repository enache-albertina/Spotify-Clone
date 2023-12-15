package fileio.input;

import java.util.ArrayList;


/**
 * The type Command input.
 */
public final class CommandInput {
    private String command;
    private String username;
    private ArrayList<EpisodeInput> episodes;
    private String album;
    private Integer releaseYear;
    private String description;
    private ArrayList<SongInput> songs;

    private String name;

    /**
     * Gets album.
     *
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Sets album.
     *
     * @param album the album
     */
    public void setAlbum(final String album) {
        this.album = album;
    }


    /**
     * Gets episodes.
     *
     * @return the episodes
     */
// getters and setters
    public ArrayList<EpisodeInput> getEpisodes() {
        return episodes;
    }

    /**
     * Sets episodes.
     *
     * @param episodes the episodes
     */
    public void setEpisodes(final ArrayList<EpisodeInput> episodes) {
        this.episodes = episodes;
    }
    private String nextPage;

    /**
     * Gets next page.
     *
     * @return the next page
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * Sets next page.
     *
     * @param nextPage the next page
     */
    public void setNextPage(final String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    private Integer price;

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    private String date;

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public ArrayList<SongInput> getSongs() {
        return songs;
    }

    /**
     * Sets songs.
     *
     * @param songs the songs
     */
    public void setSongs(final ArrayList<SongInput> songs) {
        this.songs = songs;
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


    /**
     * Gets release year.
     *
     * @return the release year
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets release year.
     *
     * @param releaseYear the release year
     */
    public void setReleaseYear(final Integer releaseYear) {
        this.releaseYear = releaseYear;
    }


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

    private Integer timestamp;
    private String type; // song / playlist / podcast
    private FiltersInput filters; // pentru search
    private Integer itemNumber; // pentru select
    private Integer repeatMode; // pentru repeat
    private Integer playlistId; // pentru add/remove song
    private String playlistName; // pentru create playlist
    private Integer seed; // pentru shuffle

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

    private String city;

    /**
     * Gets age.
     *
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(final Integer age) {
        this.age = age;
    }

    private Integer age;


    /**
     * Instantiates a new Command input.
     */
    public CommandInput() {
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets command.
     *
     * @param command the command
     */
    public void setCommand(final String command) {
        this.command = command;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
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
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(final Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets filters.
     *
     * @return the filters
     */
    public FiltersInput getFilters() {
        return filters;
    }

    /**
     * Sets filters.
     *
     * @param filters the filters
     */
    public void setFilters(final FiltersInput filters) {
        this.filters = filters;
    }

    /**
     * Gets item number.
     *
     * @return the item number
     */
    public Integer getItemNumber() {
        return itemNumber;
    }

    /**
     * Sets item number.
     *
     * @param itemNumber the item number
     */
    public void setItemNumber(final Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Gets repeat mode.
     *
     * @return the repeat mode
     */
    public Integer getRepeatMode() {
        return repeatMode;
    }

    /**
     * Sets repeat mode.
     *
     * @param repeatMode the repeat mode
     */
    public void setRepeatMode(final Integer repeatMode) {
        this.repeatMode = repeatMode;
    }

    /**
     * Gets playlist id.
     *
     * @return the playlist id
     */
    public Integer getPlaylistId() {
        return playlistId;
    }

    /**
     * Sets playlist id.
     *
     * @param playlistId the playlist id
     */
    public void setPlaylistId(final Integer playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Gets playlist name.
     *
     * @return the playlist name
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Sets playlist name.
     *
     * @param playlistName the playlist name
     */
    public void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Gets seed.
     *
     * @return the seed
     */
    public Integer getSeed() {
        return seed;
    }

    /**
     * Sets seed.
     *
     * @param seed the seed
     */
    public void setSeed(final Integer seed) {
        this.seed = seed;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "CommandInput{"
                + "command='" + command + '\''
                + ", username='" + username + '\''
                + ", timestamp=" + timestamp
                + ", type='" + type + '\''
                + ", filters=" + filters
                + ", itemNumber=" + itemNumber
                + ", repeatMode=" + repeatMode
                + ", playlistId=" + playlistId
                + ", playlistName='" + playlistName + '\''
                + ", seed=" + seed
                + '}';
    }
}
