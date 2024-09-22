import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**Implements a doubly linked list using SongNode nodes and represents a playlist.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class SongLinkedList {
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;
    private Clip c;

    /**Initializes an instance of SongLinkedList with no SongNodes.
     *
     */
    public SongLinkedList() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**Plays the song with the specified name if it is the source folder, otherwise throws an IllegalArgumentException.
     *
     * @param name Name of song to play.
     * @throws IllegalArgumentException Indicates that the song cannot be found.
     * @precondition THe name must match a song in the playlist and a .wav file.
     */
    public void play(String name) throws IllegalArgumentException{
        if(c!= null && c.isRunning()){
            c.stop();
            c.close();
        }
        try{
            findSong(name);
            AudioInputStream AIS = AudioSystem.getAudioInputStream(
                    new File(name + ".wav")
            );
            c = AudioSystem.getClip();
            c.open(AIS);
            c.start();
        }
        catch (Exception e){
            throw new IllegalArgumentException();
            //System.out.println("'" + name + "' not found");
        }
    }

    /**Checks to see if a song with a specific name exist in the playlist.
     *
     * @param name Name to check for.
     * @return Song instance with specified song name.
     * @throws IllegalArgumentException Indicates that the song does not exist in the playlist.
     * @precondition A song with the name exists in playlist.
     */
    public Song findSong(String name) throws IllegalArgumentException{
        SongNode current = head;
        while(current != null){
            if(current.getData().getName().equals(name))
                return current.getData();
            current = current.getNext();
        }
        throw new IllegalArgumentException();
    }
    /**Moves the cursor forward if it can.
     *
     * @return True if cursor was moved forward, false otherwise.
     */
    public boolean cursorForwards(){
        if(cursor != null && cursor.getNext() != null) {
            cursor = cursor.getNext();
            return true;
        }
        return false;
    }

    /**Moves the cursor backwards if it can.
     *
     * @return True if cursor was moved backwards, false otherwise.
     */
    public boolean cursorBackwards(){
        if(cursor != null && cursor.getPrev() != null) {
            cursor = cursor.getPrev();
            return true;
        }
        return false;
    }

    /**Inserts a SongNode after the cursor if it can, if not the SongNode becomes the cursor.
     *
     * @param newSong New Song to add.
     * @throws IllegalArgumentException Indicates that the newSong is invalid.
     * @precondition newSong is not null.
     */
    public void insertAfterCursor(Song newSong) throws IllegalArgumentException{
        if(newSong == null)
            throw new IllegalArgumentException("newSong is null");

        //Sets head, tail, and cursor to the new song if playlist is empty.
        if(head == null)
            head = tail = cursor = new SongNode(null, null, newSong);
        //The new song becomes the tail if there is only one song.
        else if(head == tail){
            SongNode song = new SongNode(head, null , newSong);
            head.setNext(song);
            tail = cursor = song;
        }
        else if(cursor == tail){
            SongNode song = new SongNode(tail, null, newSong);
            tail.setNext(song);
            tail = cursor = song;
        }
        else{
            SongNode song = new SongNode(cursor, cursor.getNext(), newSong);
            song.getNext().setPrev(song);
            cursor.setNext(song);
            cursor = song;
        }
        size++;
    }

    /**Removes the SongNode at cursor if there are SongNodes in the LinkedList.
     *
     * @return The Song removed.
     */
    public Song removeCursor(){
        if(cursor == null){
            return null;
        }
        size--;
        Song song = cursor.getData();
        //Clears playlist if only one song.
        if(head == tail){
            head = tail = cursor = null;
        }
        else if(cursor == head){
            cursor = cursor.getNext();
            cursor.setPrev(null);
            head = cursor;
        }
        else if(cursor == tail){
            cursor = cursor.getPrev();
            cursor.setNext(null);
            tail = cursor;
        }
        else{
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getNext();
        }
        return song;
    }

    /**Helper method for shuffle method, moves the cursor to the specified SongNode.
     *
     * @param song SongNode to move cursor to.
     */
    public void moveCursor(SongNode song){
        cursor = head;
        while(cursor != song && cursor.getNext() != null)
            cursor = cursor.getNext();
    }

    /**Returns number of Songs in SongLinkedList.
     *
     * @return Number of Songs.
     */
    public int getSize(){
        return size;
    }

    /**Picks a random SongNode in the playlist.
     *
     * @return A random SongNode.
     */
    public SongNode getRandom(){
        int index = (int) (Math.random() * getSize());
        SongNode song = head;
        for(int i = 0; i<index; i++){
            song = song.getNext();
        }
        return song;
    }

    /**Returns a Random Song from the playlist.
     *
     * @return A random Song.
     * @throws IllegalStateException Indicates that there are no songs in the playlist.
     * @precondition There is at least one song in the playlist.
     */
    public Song random() throws IllegalStateException{
        if(getSize() == 0)
            throw new IllegalStateException();
        Song song = getRandom().getData();
        try {
        play(song.getName());
        }
        catch(IllegalArgumentException e){
            throw new IllegalStateException();
        }
        return song;
    }

    /**Shuffles the playlist by creating a new playlist, removing a random song from the original and putting it in new.
     */
    public void shuffle(){
        if(getSize() == 0){
            return;
        }
        SongLinkedList shuffled = new SongLinkedList();
        
        while(getSize() >0){
            SongNode song = getRandom();
            moveCursor(song);
            removeCursor();
            shuffled.insertAfterCursor(song.getData());
        }
        head = shuffled.head;
        tail = shuffled.tail;
        cursor = head;
        size = shuffled.size;
    }

    /**Prints the playlist in tabular form and adds an arrow to the SongNode the cursor references.
     *
     */
    public void printPlaylist(){
        String format = "%-25s %-25s %-25s %-5s";
        SongNode song = head;
        System.out.println(String.format(format, "Song", "| Artist",
          "| Album","| Length (s)"));
        while(song != null) {
            System.out.print(song.getData().toString());
            if (song == cursor)
                System.out.print("<-");
            System.out.println();

            song = song.getNext();
        }


    }

    /**Deletes all songs in the playlist.
     *
     */
    public void deleteAll(){
        head = tail = cursor = null;
        size = 0;
    }

    /**Returns the string representation of the playlist ih tabular format.
     *
     * @return Playlist as a string.
     */
    public String toString(){
        String format = "%-25s %-25s %-25 %-5s";
        SongNode song = head;
        System.out.println(String.format(format, "Song", "Artist", "Album",
          "Length (s)"));
        String res = "";
        while(song != null) {
            res += song.getData().toString() + "\n";

            song = song.getNext();
        }
        return res;
    }

}