import java.io.*;

public class CreateRuns {

    private int runHeapSize;
    private String[] unsortedLinesArray;
    private int index = 0;

    public static void main(String[] args) {
        CreateRuns run = new CreateRuns();

        run.start(args);
    }

    public void start(String[] args) {
        try {
            runHeapSize = Integer.parseInt(args[0]);
            unsortedLinesArray = new String[runHeapSize];

        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        //Reads data from the piped input
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            //While there is still data unread, insert it into a temporary array
            while ((line = reader.readLine()) != null) {
                //If the array is not full yet, add a line of data
                if (index < runHeapSize) {
                    unsortedLinesArray[index] = line;
                    index++;
                }
                //If the array is filled
                if (index.equals(runHeapSize)) {
                    run(reader);
                    return;
                }
            }
        } catch (Exception e){
            System.out.println("Invalid piped in file");
        }
    }

    //Create a heap and loading the tmp array
    public void run(BufferedReader reader) {
        MyMinHeap heap = new MyMinHeap();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)); 
        heap.load(unsortedLinesArray);

        try{
            // 1 iteration - get the smallest item in the heap, and put it into our output run
            String smallestInHeap = heap.peek();

            writer.write(smallestInHeap);

            heap.insert(reader.readLine());

            writer.write(smallestInHeap);

            //

        } catch (Exception e) {
            
        }
        

        
        //1. Pop the heap
        //2. Make comparison with smallest (peek compared to next item in InputR)
        //3. If bigger, then add to end of heap.
        //4. If smaller, qurantine value, remove, then decrement heap size
        //5. 

        //Read in a line
        //Check (using compareto) - checking if it is smaller that the top
        //Output the smallest element into standard output

        //If it is smaller, then append it to the heap + heapsize--
        //If element is larger, add it to the heap
        
        //All while the heap size is not zero (ie, )

        //when the next smallest value that you output
    }
}
