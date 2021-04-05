import java.util.Arrays;

public class MyMinHeap implements IMinHeap{

    private int heapCapacity = 32;
    private int heapSize = 0;
    private int[] heapArray; 

    public static void main(String[] args) {
        MyMinHeap heap = new MyMinHeap(); // instantiate the class

        try {
            heap.heapCapacity = Integer.parseInt(args[0]);

            if (heap.heapCapacity > 10000) {
                heap.heapCapacity = 32;
                throw new Exception();
            }

        } catch (Exception e) {
            System.out.println("Usage: MyMinHeap [heap array size] \n Array size over 10000 not accepted.");
        }
        heap.heapArray = new int[heap.heapCapacity];

        heap.insert(5);
        heap.insert(345);
        heap.insert(234);
        heap.insert(1);
        heap.insert(63);
        heap.insert(7233);
        heap.insert(435);
        heap.insert(214);
        heap.insert(7134);
        heap.insert(525);

        for (int i = 0; i < heap.heapArray.length; i++) {
            System.out.print(heap.heapArray[i] + " ");
            
        }
        System.out.println("");
    }

    @Override
    public void insert(int x) {
        this.heapArray[heapSize] = x; 
        this.heapSize++; 
        upHeap();
    }

    @Override
    public void remove() {
        if (this.heapSize > 0) {
            swap(0,this.heapSize - 1);
            heapSize--;
            downHeap();
        }
    }

    @Override
    public void replace(int replacementItem) {
        if (this.heapSize > 0) {
            this.heapArray[0] = replacementItem;
            downHeap();
        } else {
            insert(replacementItem);
        }
    }

    @Override
    public int peek() {
        return this.heapSize > 0 ? this.heapArray[0] : 0;
    }

    @Override
    public void load() {
        // Not exactly sure how loading is supposed to happen
        
    }

    @Override
    public void reHeap() {
        // Brand new method required, not built in last year's assignment.. (In progress)

        int lastParentIndex = (this.heapSize / 2) - 1; 

        for (int x = lastParentIndex ; x >= 0; x--) {

            if (hasRightChild(lastParentIndex) && getRightChild(lastParentIndex) < this.heapArray[lastParentIndex]) {
                swap(getRightChildIndex(lastParentIndex), lastParentIndex);
            }
            if (hasLeftChild(lastParentIndex) && getLeftChild(lastParentIndex) < this.heapArray[lastParentIndex]) {
                swap(getLeftChildIndex(lastParentIndex), lastParentIndex);
            }
    
            lastParentIndex--;

        }
        
    }

    private void upHeap() {
        int index = this.heapSize - 1;
        while(hasParent(index + 1) && getParent(index) > heapArray[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void downHeap() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (this.heapArray[index] < this.heapArray[smallerChildIndex]) {
                break;
            } else {
                swap(index,smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    private void swap(int a, int b) {
        int temp = this.heapArray[a];

        this.heapArray[a] = this.heapArray[b];
        this.heapArray[b] = temp;
    }

    // Boolean check given an index, whether that item in the heap has; parent, leftchild, rightchild
    private boolean hasParent(int index) {return (index / 2) > 0;}
    private boolean hasLeftChild(int index) {return (index * 2) <= this.heapSize;}
    private boolean hasRightChild(int index) {return ((index + 1) * 2) < this.heapSize;}

    //Given an index location, retrieve the value of its; parent, leftchild, rightchild
    private int getParent (int index) {return heapArray[(index - 1) / 2];}
    private int getLeftChild(int index) {return heapArray[(2 * index) + 1];}
    private int getRightChild(int index) {return heapArray[(2 * index) + 2];}

    // Return index of parent, left or right child index
    private int getParentIndex (int childIndex) {return (childIndex - 1) / 2;}
    private int getLeftChildIndex (int parentIndex) {return (parentIndex * 2) + 1; }
    private int getRightChildIndex (int parentIndex) {return (parentIndex * 2) + 2; }

}