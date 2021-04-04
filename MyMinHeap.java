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
        // Brand new method required, not built in last year's assignment
        
    }

    private void upHeap() {
        int index = this.heapSize - 1;
        while(hasParent(index) && getParent(index) > heapArray[index]) {
            swap(getParent(index), index);
            index = getParent(index);
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
    private boolean hasRightChild(int index) {return (index * 2 + 1) <= this.heapSize;}

    //Given an index location, retrieve the value of its; parent, leftchild, rightchild
    private int getParent (int index) {return heapArray[(index / 2)];}
    private int getLeftChild(int index) {return heapArray[(2 * index)];}
    private int getRightChild(int index) {return heapArray[(2 * index + 1)];}

    // Return index of parent, left or right child index
    private int getParentIndex (int childIndex) {return (childIndex - 1) / 2;}
    private int getLeftChildIndex (int parentIndex) {return (parentIndex * 2) + 1; }
    private int getRightChildIndex (int parentIndex) {return (parentIndex * 2) + 2; }

}