import java.util.Arrays;

public class MyMinHeap implements IMinHeap{

    private int heapCapacity = 32;
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
        // TODO Auto-generated method stub
        
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
}