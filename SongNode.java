/**The node class represents elements in a linked list. Each node object contains a Song and a reference to the previous and next node.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class SongNode{
    private SongNode prev;
    private SongNode next;
    private Song data;

    /**
     *
     * @param prev Previous SongNode.
     * @param next Next SongNode.
     * @param data Song of the SongNode.
     */
    public SongNode(SongNode prev, SongNode next, Song data){
        this.prev = prev;
        this.next = next;
        this.data = data;
    }

    /**Returns reference to the previous SongNode.
     *
     * @return Previous SongNode.
     */
    public SongNode getPrev() {
        return prev;
    }

    /**Sets the previous SongNode.
     *
     * @param prev Previous SongNode to set.
     */
    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    /**Returns reference to the next SongNode.
     *
     * @return Next SongNode.
     */
    public SongNode getNext() {
        return next;
    }

    /**Sets the next SongNode.
     *
     * @param next Next SongNode to set.
     */
    public void setNext(SongNode next) {
        this.next = next;
    }

    /**Returns the Song object of the SongNode.
     *
     * @return Song of the SongNode.
     */
    public Song getData() {
        return data;
    }

    /**Sets the Song of the SongNode.
     *
     * @param data Song to set.
     */
    public void setData(Song data) {
        this.data = data;
    }
}