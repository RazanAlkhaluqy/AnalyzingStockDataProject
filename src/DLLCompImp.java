
import java.util.Date;
public class DLLCompImp <T extends Comparable<T>>   implements DLLComp <T>
{
    public DLL<T> doubleLinkedList;
    boolean increasing = true;
    
    public DLLCompImp()
    {
    	doubleLinkedList = new DLLImp<T> ();
    }
    
    
    public void quickSort(T[] A, int l, int r) {
        // Loop
        while (l < r) {
            // Partition the array
            int s = partition(A, l, r);
            
            // Sort the left partition
            quickSort(A, l, s - 1);

            // Move to the right partition
            l = s + 1;
        }
    }

   public int partition(T[] A, int l, int r) {
        T p = A[l];
        int i = l + 1, j = r;
        while (i <= j) {
               while (i <= j && (A[i].compareTo(p) <= 0 )) // Move i until A[i] > p
                    i++;
        	   while (i <= j && (A[j].compareTo(p) >= 0) ) // Move j until A[j] < p
                    j--;
            
            if (i < j) {
                swap(A, i, j);
                i++;
                j--;
            }
        }
        swap(A, l, j); // Place pivot in its correct position
        return j;
    } 
    
    
 // [ Requires Chapter on Sorting ] Sorts the list . The parameter " increasing "
    // indicates whether the sort is done in increasing or decreasing order .
 
    public void sort(boolean increasing) {
        this.increasing = increasing;
        if (doubleLinkedList.empty())
            return;

        T[] array;
        array = (T[]) new Comparable[doubleLinkedList.size()];
        this.findFirst();
        int i = 0;
        while (!doubleLinkedList.empty()) {
            array[i++] = this.retrieve();
            this.remove();
        }
        quickSort(array, 0, array.length - 1);

        // Reinsert sorted elements back into the linked list
        if (increasing) {
            for (int index = 0; index < array.length; index++)
                this.insert(array[index]);
        } else {
            for (int index = array.length - 1; index >= 0; index--)
                this.insert(array[index]);
        }
    }
    // Swap two elements in an array
    private void swap(T[] A, int i, int j) {
        T tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    } 
 // Returns the maximum element . The list must not be empty .
   
 public T getMax () 
 {
	 if ( empty())
         return null;
     
     this.findFirst();
    if (increasing )
     {
         while (!last())
        	this.findNext();
     }
     return this.retrieve();
 }
 
 // Returns the minimum element . The list must not be empty .
 public T getMin () 
 {
	 if ( empty())
         return null;
     
     this.findFirst();
    if (!increasing)
         
     {
         while ( !last() )
        	 this.findNext();
     }
     return this.retrieve();
 } 
 @Override
 public int size() {
     return doubleLinkedList.size();
 }

 @Override
 public boolean empty() {
     return doubleLinkedList.empty();
             
 }

 @Override
 public boolean last() {
     return doubleLinkedList.last();
 }

 @Override
 public boolean first() {
      return doubleLinkedList.first();
 }

 @Override
 public void findFirst() {
	 doubleLinkedList.findFirst();
 }

 @Override
 public void findNext() {
	 doubleLinkedList.findNext();
 }

 @Override
 public void findPrevious() {
	 doubleLinkedList.findPrevious();
 }

 @Override
 public T retrieve() {
     return doubleLinkedList.retrieve();
 }

 @Override
 public void update(T val) {
	 doubleLinkedList.update(val);
 }

 @Override
 public void insert(T val) {
	 doubleLinkedList.insert(val);
 }

 @Override
 public void remove() {
	 doubleLinkedList.remove();
 }   
} //end class.