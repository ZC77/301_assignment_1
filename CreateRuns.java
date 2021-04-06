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
        } catch (Exception e){
            System.out.println("Invalid piped in file");
        }
    }

    //Run replacement selection on the inputted lines to create runs using the heap
    public void run(BufferedReader reader) {
        try{
            MyMinHeap heap = new MyMinHeap(4);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            
            heap.load(unsortedLinesArray); // Bulk load
            System.out.println("Bulk loaded to heap");

            String smallestInHeap = heap.peek(); // Peek smallest value at top
            String nextLine = reader.readLine(); // Get the next line value
            System.out.println("Just 1st LINE read: " + nextLine);
            
            while (nextLine != null) { // While there are still lines to read
                while(heap.heapSize > 0) { // While there is still space in the heap

                    writer.write(smallestInHeap);
                    System.out.println("Curent SIH: " + smallestInHeap);

                    if (smallestInHeap.compareTo(nextLine) <= 0) {
                        heap.remove();
                        heap.insert(nextLine);
                    } else {
                        heap.replace(nextLine);
                        heap.swap(0, heap.heapSize - 1);
                        heap.heapSize--;
                        heap.reHeap();
                    }
                    
                    smallestInHeap = heap.peek();
                    nextLine = reader.readLine();
                    //System.out.println("Just read: " + nextLine);
                }

                writer.write("RUN FINISHED");
                System.out.println("Run FINISHED");

                heap.heapSize = heap.heapArray.length;
                heap.reHeap();
            }

        } catch (Exception e) {
            System.out.println("Error occured: " + e);
        }
    }
}
