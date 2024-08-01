

public class DLLImp<T> implements DLL<T> {
/*NODE  Class */

    class Node<T> {
            public T data;
            public Node<T> previous;
            public Node<T> next;

            public Node () {

                data = null;
                next = null;
                previous = null;
            }

            public Node (T val) {
                data = val;
                next = null;
                previous = null;
            }

            // Setters/Getters...

            public T getData() {
                return data;
            }

            public void setData(T data) {
                this.data = data;
            }

            public Node<T> getNext() {
                return next;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }

            public Node<T> getPrevious() {
                return previous;
            }

            public void setPrevious(Node<T> previous) {
                this.previous = previous;
            }

        }
    
//*********************************************************//
    public Node<T> head;
    public Node<T> current;
    private int count;
    
    public DLLImp() {
            head = current = null;
            count = 0;
    }

    // Returns the number of elements in the list.
     public int size()
     {
         return count;
     }

    // Returns true if the list is empty, false otherwise.
    public boolean empty()
    {
        return head == null;
    }

    // Returns true if the current position is at the last element, false otherwise.
    public boolean last() 
    {
            return current.next == null;
    }
    
    // Returns true if the current position is at the first element, false
    // otherwise.
    public boolean first()
    {
            return current.previous == null;
    }

    // Sets the current position to the first element of the list (detailed
    // specification seen in class).
    public void findFirst()
    {
        current = head;
    }

    // Advances the current position to the next element in the list (detailed
    // specification seen in class).
    public void findNext()
    {
        if (current.next != null)
            current = current.next;
    }

    // Moves the current position to the previous element in the list (detailed
    // specification seen in class).
    public void findPrevious()
    {
        if (current.previous != null)
            current = current.previous;
    }

    // Retrieves the element at the current position.
    public T retrieve()
    {
        return current.data;
    }

    // Updates the element at the current position with the provided value.
    public void update(T val) {
        current.data = val;
    }
    
    // Inserts the provided element at the current position in the list (detailed
    // specification seen in class).
    public void insert(T val)
    {
         Node<T> tmp = new Node<T>(val);
            if(empty()) {
                    current = head = tmp;
            }
            else {
                    tmp.next = current.next;
                    tmp.previous = current;
                    if(current.next != null)
                            current.next.previous = tmp;
                    current.next = tmp;
                    current = tmp;
            }            
            count++;
    }

    // Removes the element at the current position from the list (detailed
    // specification seen in class).
    public void remove()
    {
        if(current == head) {
                    head = head.next;
                    if(head != null)
                       head.previous = null;
            }
            else {
                    current.previous.next = current.next;
                    if(current.next != null)
                       current.next.previous = current.previous;
            }

            if(current.next == null)
                    current = head;
            else
                    current = current.next;
            count --;
    }
    
}
