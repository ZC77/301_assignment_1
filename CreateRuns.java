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
            
            heap.load(unsortedLinesArray); // Bulk load
            String smallestInHeap = heap.peek(); // Peek smallest value at top
            String previousLineInRun = smallestInHeap; //Set smallest in heap as tmp last in run
            String nextLineInput = "p"; // Starting placeholding text to start while loop
            int counter = 1;

            //While this is not the last run to be processed
            while (heap.heapSize != 0) {

                // While elements still need to be processed (not at the end of the current run)
                while (heap.heapSize != 0) {

                    //If smallest in heap can go as the next element in the run
                    if (smallestInHeap.compareTo(previousLineInRun) >= 0) {
                        writer.write(smallestInHeap + "\n");
                        System.out.println(counter + ": [" + smallestInHeap + "] has been added to the RUN");
                        counter++;
                        previousLineInRun = smallestInHeap;
                        nextLineInput = reader.readLine();

                        //If we are not at the end of the file
                        if (nextLineInput != null) {
                            heap.replace(nextLineInput);
                        }
                        else {
                            heap.clear();
                        }

                    //Else, item needs to be put aside
                    } else {
                        heap.remove();
                    }
                    smallestInHeap = heap.peek();

                    //Debugging view on terminal
                    System.out.println("--: [" + nextLineInput + "] has been added to the HEAP");
                    PrintHeap(heap);
                }
                
                //If heapsize is 0 (no more valid elements pushed to run), end the current run.
                writer.write("END_OF_RUN" + "\n");
                System.out.println("END_OF_RUN");
                heap.reset();
                PrintHeap(heap);

                //Reset the smallest in heap 
                smallestInHeap = heap.peek();
                previousLineInRun = smallestInHeap;
            }
            
            //Once there are no more lines to input
            writer.flush();
            writer.close();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occured: " + e);
        }
    }

    //Prints current heap elements to system terminal
    public void PrintHeap(MyMinHeap heap) {
        System.out.print("Curent items in heap: total(" + heap.heapSize + ") ");
        for (int f = 0; f < heap.heapCapacity; f++){
            System.out.print("[" + f + "]:" + heap.heapArray[f] + ", ");
        }
        System.out.println("");
        System.out.println("");
    }
}
