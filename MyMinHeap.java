import java.util.*;

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
        
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void replace() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void peek() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reHeap() {
        // TODO Auto-generated method stub
        
    }

    private void upHeap() {

        int index = this.heapSize - 1;
        while(hasParent(index) && getParent(index) > heapArray[index]) {
            swap(getParent(index), index);
            index = getParent(index);
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

}