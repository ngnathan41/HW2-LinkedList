/**The Song class details the information of a song-name, artist, album, and length.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class Song{
    private String name;
    private String artist;
    private String album;
    private int length;

    /**Creates an instance of Song with its name, artist, album, and length.
     *
     * @param name Song name
     * @param artist Artist name
     * @param album Album name
     * @param length Song length
     */
    public Song(String name, String artist, String album, int length){
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    /** Returns the name of the song.
     *
     * @return Name of the song.
     */
    public String getName() {
        return name;
    }

    /**Returns the artist of the song.
     *
     * @return Artist of the song.
     */
    public String getArtist() {
        return artist;
    }

    /**Returns the length of the song.
     *
     * @return Length of the song.
     */
    public int getLength() {
        return length;
    }

    /**Returns the album of the song.
     *
     * @return Album of the song.
     */
    public String getAlbum() {
        return album;
    }

    /**Sets the name of the song.
     *
     * @param name Name of the song.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Sets artist of the song.
     *
     * @param artist Artist of the song.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**Sets album of the song.
     *
     * @param album Album of the song.
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**Sets length of the song.
     *
     * @param length Length of the song.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**Returns the data of the song in a set spaced format.
     *
     * @return String representation of Song.
     */
    public String toString(){
        String format = "%-25s %-25s %-25s %-5s";
        return String.format(format, getName(), getArtist(), getAlbum(), getLength());
    }
}