public class Song{
    private String name;
    private String artist;
    private String album;
    private String length;

    public Song(String name, String artist, String album, String length){
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getLength() {
        return length;
    }

    public String getAlbum() {
        return album;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String toString(){
        String format = "%-25s %-25s %-25s %-5s";
        return String.format(format, getName(), getArtist(), getAlbum(), getLength());
    }
}