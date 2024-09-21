import java.util.Scanner;
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

    public static void nextSong(SongLinkedList playlist){
        if(playlist.cursorForwards())
            System.out.println("Cursor moved to the next song");
        else
            System.out.println("Already at the end of the playlist");
    }

    public static void prevSong(SongLinkedList playlist){
        if(playlist.cursorBackwards())
            System.out.println("Cursor moved to the previous song");
        else
            System.out.println("Already at the beginning of the playlist");
    }

    public static void removeSong(SongLinkedList playlist){
        Song removed = playlist.removeCursor();
        if(removed == null){
            System.out.println("No song removed.");
        }
        else
            System.out.println("'" + removed.getName() + "' by " + removed.getArtist() + " was removed from the playlist.");

    }

    public static void playSong(SongLinkedList playlist){
        System.out.println("Enter name of song to play:");
        String name = sc.nextLine();
        try{
            playlist.play(name);

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void clearPlayList(SongLinkedList playlist){
        playlist.deleteAll();
        System.out.println("Playlist cleared.");
    }

    public static void playRandom(SongLinkedList playlist){
        System.out.println("Playing a random song. . .");
        Song random = playlist.random();
        System.out.println("'" + random.getName() + "' by " + random.getArtist() + " is now playing.");
    }
    public static void getSize(SongLinkedList playlist){
        System.out.println("Your playlist contains " + playlist.getSize() + " songs.");
    }
}