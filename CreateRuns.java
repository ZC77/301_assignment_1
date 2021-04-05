import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.*;

public class CreateRuns {

    private int runHeapSize;
    private String[] tmpArray;
    private int index = 0;

    public static void main(String[] args) {
        CreateRuns run = new CreateRuns();

        run.start(args);
    }

    public void start(String[] args) {
        try {
            runHeapSize = Integer.parseInt(args[0]);
            tmpArray = new String[runHeapSize];

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
                    tmpArray[index] = line;
                    index++;
                }
                //If the array is filled
                if (index == runHeapSize) {
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
        heap.load(tmpArray);

        
        //Read in a line
        //Check (using compareto) - checking if it is smaller that the top
        //Output the smallest element into standard output

        //If it is smaller, then append it to the heap + heapsize--
        //If element is larger, add it to the heap
        
        //All while the heap size is not zero (ie, )

        //when the next smallest value that you output
    }
}
