import java.util.Arrays;

public class MyMinHeap implements IMinHeap{

    public MyMinHeap(int HC) {
        heapCapacity = HC;
        this.heapArray = new String[heapCapacity];
    }

    public int heapCapacity = 32;
    public int heapSize = 0;
    public String[] heapArray;

    //Reset method resets the heap using a reording algorithm to bring elements to the start of the heap
    //Heapsize is updated and is changed after disregarded null values segregated
    public void reset()
    {   
        int nextContiguousSpot = 0;

        //For each element in the heap, if element is not null, move to beginning of heap in order
        for (int i = 0; i < this.heapArray.length; i++) {
            if (heapArray[i] != null) {
                heapArray[nextContiguousSpot] = heapArray[i];
                if (i != nextContiguousSpot) {
                    heapArray[i] = null;
                }
                nextContiguousSpot++;
            }
        }
        //Update heap size and ensure heap order is restored
        this.heapSize = nextContiguousSpot;
        reHeap();
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

    //Load method fills heap using passed string array
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