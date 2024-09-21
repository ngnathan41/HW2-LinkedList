import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SongLinkedList {
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;

    public SongLinkedList() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    public SongNode getCursor(){
        return cursor;
    }

    public void play(String name) throws IllegalArgumentException{
        try{
            AudioInputStream AIS = AudioSystem.getAudioInputStream(
                    new File(name + ".wav")
            );
            Clip c = AudioSystem.getClip();
            c.open(AIS);
            c.start();
        }
        catch (Exception e){
            //throw new IllegalArgumentException();
            System.out.println("'" + name + "' not found");
        }
    }

    public boolean cursorForwards(){
        if(cursor != null && cursor.getNext() != null) {
            cursor = cursor.getNext();
            return true;
        }
        return false;
    }

    public boolean cursorBackwards(){
        if(cursor != null && cursor.getPrev() != null) {
            cursor = cursor.getPrev();
            return true;
        }
        return false;
    }

    public void insertAfterCursor(Song newSong) throws IllegalArgumentException{
        if(newSong == null)
            throw new IllegalArgumentException("newSong is null");

        if(head == null)
            head = tail = cursor = new SongNode(null, null, newSong);
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

    public Song removeCursor(){
        if(cursor == null){
            return null;
        }
        size--;
        Song song = cursor.getData();
        if(head == tail){
            head = tail = cursor = null;
            return song;
        }
        else if(cursor == head){
            cursor = cursor.getNext();
            cursor.setPrev(null);
            return song;
        }
        else if(cursor == tail){
            cursor = cursor.getPrev();
            cursor.setNext(null);
            return song;
        }
        else{
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getNext();
            return song;
        }
    }

    public void moveCursor(SongNode song){
        cursor = head;
        while(cursor != song && cursor.getNext() != null)
            cursor = cursor.getNext();
    }

    public int getSize(){
        return size;
    }

    public SongNode getRandom(){
        int index = (int) (Math.random() * size );
        SongNode song = head;
        for(int i = 0; i<index; i++){
            song = song.getNext();
        }
        return song;
    }

    public Song random(){
        Song song = getRandom().getData();
        play(song.getName());
        return song;
    }

    public void shuffle(){
        SongLinkedList shuffled = new SongLinkedList();
        while(getSize() >0){
            SongNode song = getRandom();
            removeCursor();
            shuffled.insertAfterCursor(song.getData());
        }
        head = shuffled.head;
        tail = shuffled.tail;
    }

    public void printPlaylist(){
        String format = "%-25s %-25s %-25s %-5s";
        SongNode song = head;
        System.out.println(String.format(format, "Song", "| Artist", "| Album","| Length (s)"));
        while(song != null) {
            System.out.print(song.getData().toString());
            if (song == cursor)
                System.out.print("<-");
            System.out.println();

            song = song.getNext();
        }


    }

    public void deleteAll(){
        head = tail = cursor = null;
        size = 0;
    }

    public String toString(){
        String format = "%-25s %-25s %-25 %-5s";
        SongNode song = head;
        System.out.println(String.format(format, "Song", "Artist", "Album","Length (s)"));
        String res = "";
        while(song != null) {
            res += song.getData().toString() + "\n";

            song = song.getNext();
        }
        return res;
    }

}