//Name and ID: Bhavit Wadhwa 1516846

import java.util.Arrays;

public class MyMinHeap implements MinHeapFace {

    private int heapCapacity = 20;
    private int heapSize = 0;
    private int[] itemsMinHeap = new int[heapCapacity];

/****************** PUBLIC INTERFACE METHODS ********************/
//Public interface methods as enforced by programming by contract

    // ADD int to MinHeap
    public void add(int newItem) {
        checkCapacity();                    //Check heap capacity is sufficient, OTHERWISE resize/update heap size
        itemsMinHeap[heapSize + 1] = newItem;   //Add new item to next empty location in heap
        heapSize++;                         //Update size of heap
        itemsMinHeap[0] = heapSize;         //First value in heap contains the size of the heap
        upHeap();                           //Make sure new item is in correct position in heap
    }

    // RETURN (but do not remove) top of heap
    public int get() {
        if (isEmpty()) {                    //If the heap is empty, display warning and return -1
            System.err.println("Get method attempted on an empty heap, error");           
            return -1;
        }
        return itemsMinHeap[1];
    }

    // REMOVE top of heap
    public void remove() {
        if (isEmpty() == false) {           //If the heap is NOT empty
            swapValues(1, heapSize);        //Swap with last element in heap
            heapSize--;                     //Reduce the size of the heap
            itemsMinHeap[0] = heapSize;     //First value in heap contains the size of the heap
            downHeap();                     //Down heap to rearrange heap in correct order
        }
        //Otherwise, do nothing as there is nothing to remove
    }

    // Return true if nothing in heap, false otherwise
    public boolean isEmpty() {
        //If the heap is empty, return true, else return false
        if (heapSize == 0) {
            return true;
        }
        return false;
    }

    // REPLACE top of heap with int
    public void replace(int replacementItem) {
        if (isEmpty() == false) {               //If the heap is NOT empty
            itemsMinHeap[1] = replacementItem;  //Replace top of heap (root node) with recieved int
            downHeap();                         //Make sure replacement item is in correct position
        }
        else {
            //Otherwise, if we are replacing on an empty heap, just add "replacementItem" instead
            add(replacementItem);
        }
    }

    // REMOVE everything from heap
    public void clear() {
        //Reset size to 0 and reset array as NEW array of stock capacity (20)
        //This means the array is cleared and array does not have capacity in excess of 20 from previous operation
        heapSize = 0;
        itemsMinHeap = new int[heapCapacity];
    }

    // return COUNT of how many items in heap
    public int size() {
        return heapSize;
    }
/****************** PRIVATE METHODS (INTERNAL OPERATIONS) ********************/
//Private maintenance operations used to support heap ordering

    //Given the parent's index, find the value of LEFT child
    private int leftChild(int index) {
        return itemsMinHeap[(2 * index)];
    }
    
    //Given the parent's index, find the value of RIGHT child
    private int rightChild(int index) { 
        return itemsMinHeap[(2 * index + 1)];
    }

    //Given the child's index, find the value of PARENT
    private int parent(int index) { 
        return itemsMinHeap[(index / 2)];
    }

    //Check if the current node (at location index) has left/right child, if child's location 
    private boolean hasLeftChild(int index) {
        return (index * 2) <= heapSize;
    }
    private boolean hasRightChild(int index) {
        return (index * 2 + 1) <= heapSize;
    }

    //Every node apart from the root node has a parent, return false if parent node index is 0
    private boolean hasParent(int index) {
        return (index / 2) > 0;
    }
    

    private void upHeap() {
        //Gets the location of the last added element
        int currentIndex = heapSize;
        //While we have a parent and that parent is LESS than us
        while (hasParent(currentIndex) && parent(currentIndex) > itemsMinHeap[currentIndex]) {
            //Swap with parent (until reaching root node OR parent is smaller than us)
            swapValues((int)(currentIndex / 2), currentIndex);
            //Traverse upwards by updating index
            currentIndex = (int)(currentIndex / 2);
        }
    }

    private void downHeap() {
        //Gets the location of the ROOT element
        int currentIndex = 1;
        //While we have a child (check if there is a left child - heaps are leftify), keep traversing down heap
        while (hasLeftChild(currentIndex)) {
            //Find the smaller child (set to left child as default, then check if right child)
            int smallerChildIndex = (currentIndex * 2);

            //If there is a right child and it is smaller than the left child, we have found the smaller child 
            if (hasRightChild(currentIndex) && rightChild(currentIndex) < leftChild(currentIndex)) {
                smallerChildIndex = (currentIndex * 2 + 1);
            }

            //If the current value is smaller than its smaller child, our heap is in order now
            if (itemsMinHeap[currentIndex] < itemsMinHeap[smallerChildIndex]) {
                return;
            }
            //Otherwise, heap is still out of order, swap the current value with smaller child
            else{
                swapValues(currentIndex, smallerChildIndex);
            }
            //Update current index to shift with current value (moving down)
            currentIndex = smallerChildIndex;
        }
    }

    private void checkCapacity() {
        //If the heap int array is full
        if (heapSize == (heapCapacity - 1)) {
            //Copy over current elements from heap into new array and increase heap capacity by 20%
            int increasedCapacity = (int)(heapCapacity * 1.2);
            itemsMinHeap = Arrays.copyOf(itemsMinHeap, increasedCapacity);
            //Update heap capacity
            heapCapacity = increasedCapacity;
        }
    }
    
    private void swapValues(int i, int j) {
        //Swaps values of elements using the 2 indexed locations recieved (i and j)
        int tmp = itemsMinHeap[i];
        itemsMinHeap[i] = itemsMinHeap[j];
        itemsMinHeap[j] = tmp;
    }
}