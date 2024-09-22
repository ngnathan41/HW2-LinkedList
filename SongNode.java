

public class SongNode{
    private SongNode prev;
    private SongNode next;
    private Song data;

    public SongNode(SongNode prev, SongNode next, Song data){
        this.prev = prev;
        this.next = next;
        this.data = data;
    }

    public SongNode getPrev() {
        return prev;
    }

    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    public SongNode getNext() {
        return next;
    }

    public void setNext(SongNode next) {
        this.next = next;
    }

    public Song getData() {
        return data;
    }

    public void setData(Song data) {
        this.data = data;
    }
}