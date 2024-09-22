import java.util.Scanner;

/**Implements a CLI for a playlist using the SongLinkedList class.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class Player{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SongLinkedList playlist = new SongLinkedList();

        String commands = "(A) Add Song to Playlist\n" +
                "(F) Go to Next Song\n" +
                "(B) Go to Previous Song\n" +
                "(R) Remove Song from Playlist\n" +
                "(L) Play a Song\n" +
                "(C) Clear the Playlist\n" +
                "(S) Shuffle Playlist\n" +
                "(Z) Random Song\n" +
                "(P) Print Playlist\n" +
                "(T) Get the total amount of songs in the playlist\n" +
                "(Q) Exit the playlist";
        System.out.println(commands);
        //Asks for and executes commands until the quit command is received.
        program:
        while(true){
            System.out.println("Enter an option:");
            //Commands are not case sensitive.
            String command = sc.nextLine().toUpperCase();

            switch(command){
                case "A": addSong(playlist); break;
                case "F": nextSong(playlist); break;
                case "B": prevSong(playlist); break;
                case "R": removeSong(playlist); break;
                case "L": playSong(playlist); break;
                case "C": clearPlayList(playlist); break;
                case "S": playlist.shuffle();
                  System.out.println("Playlist shuffled"); break;

                case "Z": playRandom( playlist); break;
                case "P": playlist.printPlaylist(); break;
                case "T": getSize(playlist); break;
                case "Q": break program;
            }

        }
        sc.close();
    }

    /**Helper method to a new Song to the playlist. Prompts user for the attributes of a Song.
     *
     * @param playlist Playlist to add song to.
     */
    public static void addSong(SongLinkedList playlist){
        System.out.println("Enter song title:");
        String title = sc.nextLine();
        System.out.println("Enter artist(s) of the song:");
        String artist = sc.nextLine();
        System.out.println("Enter album:");
        String album = sc.nextLine();
        System.out.println("Enter length (in seconds):");
        String length = sc.nextLine();

        Song song = new Song(title, artist, album, length);
        playlist.insertAfterCursor(song);
        System.out.println("'" + title + "' by " + artist + " is added to your playlist.");
    }

    /**Helper method to move the cursor to the next song. Indicates if cursor is already at the end.
     *
     * @param playlist Playlist to move the cursor of.
     */
    public static void nextSong(SongLinkedList playlist){
        if(playlist.cursorForwards())
            System.out.println("Cursor moved to the next song");
        else
            System.out.println("Already at the end of the playlist");
    }

    /**Helper method to move the cursor to the previous song. Indicates if cursor is already at the beginning.
     *
     * @param playlist Playlist to move the cursor of.
     */
    public static void prevSong(SongLinkedList playlist){
        if(playlist.cursorBackwards())
            System.out.println("Cursor moved to the previous song");
        else
            System.out.println("Already at the beginning of the playlist");
    }

    /**Helper method to remove a song. Prompts user for song details and indicates if the song does not exist in playlist.
     *
     * @param playlist Playlist to remove song from.
     */
    public static void removeSong(SongLinkedList playlist){
        Song removed = playlist.removeCursor();
        if(removed == null){
            System.out.println("Your playlist is empty.");
        }
        else
            System.out.println("'" + removed.getName() + "' by " + removed.getArtist() + " was removed from the playlist.");

    }

    /**Helper method to play a song. Prompts user for song details and indicates if song does not exist.
     *
     * @param playlist Playlist to play song from.
     */
    public static void playSong(SongLinkedList playlist){
        System.out.println("Enter name of song to play:");
        String name = sc.nextLine();
        try{
            Song song = playlist.findSong(name);
            playlist.play(name);
            System.out.println("'" + name + "' by " + song.getArtist() + " is now playing.");
        }
        catch(IllegalArgumentException e){
            System.out.println("'" + name + "' not found");
        }
    }

    /**Helper method to clear all songs from playlist.
     *
     * @param playlist Playlist to clear.
     */
    public static void clearPlayList(SongLinkedList playlist){
        playlist.deleteAll();
        System.out.println("Playlist cleared.");
    }

    /**Helper method to play a random song from the playlist. Indicates if there are no songs in the playlist.
     *
     * @param playlist Playlist to play a random song from.
     */
    public static void playRandom(SongLinkedList playlist){
        try {
            Song random = playlist.random();
            System.out.println("Playing a random song. . .");
            System.out.println("'" + random.getName() + "' by " + random.getArtist() + " is now playing.");
        }
        catch(IllegalStateException e){
            System.out.println("Your playlist is empty.");
        }
    }

    /**Helper method to get the size of the playlist.
     *
     * @param playlist Playlist to get size from.
     */
    public static void getSize(SongLinkedList playlist){
        int size = playlist.getSize();
        if (size >0)
            System.out.println("Your playlist contains " + size + " songs.");
        else
            System.out.println("Your playlist is empty.");
    }
}