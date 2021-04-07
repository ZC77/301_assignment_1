import java.util.Arrays;

public class MyMinHeap implements IMinHeap{

    public MyMinHeap(int HC) {
        heapCapacity = HC;
        this.heapArray = new String[heapCapacity];
    }

    public int heapCapacity = 32;
    public int heapSize = 0;
    public String[] heapArray; 

    /*
    public static void main(String[] args) {
        MyMinHeap heap = new MyMinHeap(32); // instantiate the class

        try {
            heap.heapCapacity = Integer.parseInt(args[0]);

            if (heap.heapCapacity > 10000) {
                heap.heapCapacity = 32;
                throw new Exception();
            }

        } catch (Exception e) {
            System.out.println("Usage: MyMinHeap [heap array size] \n Array size over 10000 not accepted.");
        }
        heap.heapArray = new String[heap.heapCapacity];
    }

    */

    public void reset()
    {   
        int nextContiguousSpot = 0;
        for (int i = 0; i < this.heapArray.length - 1; i++) {
            if (heapArray[i] != null) {
                heapArray[nextContiguousSpot] = heapArray[i];
                nextContiguousSpot++;
            }
        }
        this.heapSize = nextContiguousSpot + 1;
        reHeap();

        /*
            Start with contiguous spot = 0
            For each element in the array:
                IF element != null
                    Move to the next contiguous spot
                    Increment next contiguous spot
                IF element == null
                    Ignore
            heapSize = #contiguous spots filled
            reheap
        */
    }

    @Override
    public void insert(String x) {
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

    public void clear() {
        if (this.heapSize > 0) {
            swap(0,this.heapSize - 1);
            this.heapArray[this.heapSize - 1] = null;
            heapSize--;
            downHeap();
        }
    }

    @Override
    public void replace(String replacementItem) {
        if (this.heapSize > 0) {
            this.heapArray[0] = replacementItem;
            if (replacementItem != null) {
                downHeap();
            }
        } else {
            insert(replacementItem);
        }
    }

    @Override
    public String peek() {
        return this.heapSize > 0 ? this.heapArray[0] : null;
    }

    @Override
    public void load(String[] paramArray) {
        try {
            for(int i = 0; i < this.heapArray.length; i++) {
                this.heapArray[i] = paramArray[i];
            }
            heapSize = paramArray.length;
            reHeap();

        } catch (Exception e) {

            System.out.println("Error occured with loading");
        }

    }

    @Override
    public void reHeap() {
        int lastParentIndex = (this.heapSize / 2) - 1; 

        for (int x = lastParentIndex ; x >= 0; x--) {

            if (hasRightChild(lastParentIndex)){
                if(getRightChild(lastParentIndex).compareTo(heapArray[lastParentIndex]) < 0) {
                    swap(getRightChildIndex(lastParentIndex), lastParentIndex);
                }
            }
            if (hasLeftChild(lastParentIndex)) {
                if (getLeftChild(lastParentIndex).compareTo(heapArray[lastParentIndex]) < 0) {
                    swap(getLeftChildIndex(lastParentIndex), lastParentIndex);
                }
            }
    
            lastParentIndex--;
        }
    }

    private void upHeap() {
        int index = this.heapSize - 1;
        while(hasParent(index + 1) && getParent(index).compareTo(heapArray[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void downHeap() {
        int index = 0;

        while (hasLeftChild(index) && heapSize > 1) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index).compareTo(getLeftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (this.heapArray[index].compareTo(this.heapArray[smallerChildIndex]) < 0 ) {
                break;
            } else {
                swap(index,smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    public void swap(int a, int b) {
        String temp = this.heapArray[a];

        this.heapArray[a] = this.heapArray[b];
        this.heapArray[b] = temp;
    }

    // Boolean check given an index, whether that item in the heap has; parent, leftchild, rightchild
    private boolean hasParent(int index) {return (index / 2) > 0;}
    private boolean hasLeftChild(int index) {return (index * 2) < this.heapSize - 1;}
    private boolean hasRightChild(int index) {return ((index * 2) + 1) < this.heapSize - 1;}

    //Given an index location, retrieve the value of its; parent, leftchild, rightchild
    private String getParent (int index) {return heapArray[(index - 1) / 2];}
    private String getLeftChild(int index) {return heapArray[(2 * index) + 1];}
    private String getRightChild(int index) {return heapArray[(2 * index) + 2];}

    // Return index of parent, left or right child index
    private int getParentIndex (int childIndex) {return (childIndex - 1) / 2;}
    private int getLeftChildIndex (int parentIndex) {return (parentIndex * 2) + 1; }
    private int getRightChildIndex (int parentIndex) {return (parentIndex * 2) + 2; }

}