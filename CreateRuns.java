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
                if (index == runHeapSize) {
                    run(reader);
                    return;
                }
            }
            runHeapSize = index;
            run(reader);
            return;
        } catch (Exception e){
            System.out.println("Invalid piped in file");
        }
    }

    //Run replacement selection on the inputted lines to create runs using the heap
    public void run(BufferedReader reader) {
        try{
            MyMinHeap heap = new MyMinHeap(runHeapSize);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            
            //1st instance
            heap.load(unsortedLinesArray); // Bulk load
            heap.printHeap();
            String smallestInHeap = heap.peek(); // Peek smallest value at top
            String previousLineInRun = smallestInHeap; //Set smallest in heap as tmp last in run
            String nextLineInput = "placeholder";

            while (nextLineInput != null) {

                while (heap.heapSize != 0) { // While elements still need to be process (not at the end of the current run)
                    //If smallest in heap can go as the next element in the run
                    if (smallestInHeap.compareTo(previousLineInRun) >= 0) {
                        writer.write(smallestInHeap);
                        System.out.println("Added to RUN: " + smallestInHeap + " | Current heap size: " + heap.heapSize);
                        previousLineInRun = smallestInHeap;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// I'm pretty sure these three steps are the culprit for the null pointer exception. When the file reaches the end, nextlineinput is set to null.
// when ever we make a reference to a null object, it will cause an exception. 
                        nextLineInput = reader.readLine();
                        heap.replace(nextLineInput);
                        System.out.println("Added to HEAP: " + nextLineInput);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    } else {
                        heap.printHeap();
                        heap.remove();
                        System.out.println("Removed from HEAP: " + smallestInHeap);
                    }
                    smallestInHeap = heap.peek();
                }

                writer.write("(RUN COMPLETED)" + "\n");
                System.out.println("(RUN COMPLETED)");

                heap.reset();
                smallestInHeap = heap.peek();
                previousLineInRun = smallestInHeap;

                // BREAK CASE: If the next inputted line is null, we are at the end of the file
                if (nextLineInput == null) {
                    for (int i = 0; i < heap.heapSize; i++) {
                        writer.write(smallestInHeap);
                        System.out.println(smallestInHeap);
                        heap.remove();
                        smallestInHeap = heap.peek();
                        i++;
                    }
                    writer.write("(RUN COMPLETED)" + "\n");
                    break;
                }
            }
            
            writer.flush();
            writer.close();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occured: " + e);
        }
            /*1st Iteration (Heap is empty): 
            - Fill the heap using load()
            - get the input stream ready for next item
            */

            /*IF the current smallest in heap is more than or equal to the last item added to the run
                - Write it to the current run
                - Save the top of the heap we just wrote to the run
                - Get the next line from the Stream
                - Replace the heap top with the next line and downheap
                    - Replace needs to check if the next item is a null
                        - If so, it must be swapped to the back of the heap then heapSize--
            */
            /*ELSE the smallest line in heap is smaller than the line of the previous run
                - Remove (Put aside/Quarantine) the line to the back of the heap (decrement heap size)
            */
            //Reset the variables holding previous in run and top of heap

            /*IF the size of the heap is 0 then a run is completed - pause the program
                - Add a run completed marker
                - Reset the heap (get rid of nulls, reset the size)
                - Perform heap to get the items back into order
                - Continue next iteration of the while loop

            */
    }
}
