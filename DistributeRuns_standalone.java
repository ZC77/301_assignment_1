import java.io.*;

public class DistributeRuns_standalone {

    int numOfFiles = 2;
    // Constructor
    public DistributeRuns_standalone (int numOfDistributedFiles) {
        if (numOfDistributedFiles >= 1 && numOfDistributedFiles < 100) {
            numOfFiles = numOfDistributedFiles;
        }
    }

    public static void main(String [] args) {
        DistributeRuns_standalone drs = new DistributeRuns_standalone(2);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // Data which is being piped in
            BufferedWriter writer;
            String line;
            String filename = "RunFile_";
            int cycle = 1; 

            for (int i = 1; i <= drs.numOfFiles; i++) {
                String next = filename + i;
                writer = new BufferedWriter(new FileWriter(next));
                writer.close();
            }

            // While not at the end, append lines onto each file until "end of line", then move onto next file, going round
            writer = new BufferedWriter(new FileWriter((filename + cycle), true));
            while ((line = reader.readLine()) != null) {
                
                if (line.equals("END_OF_RUN")) { // This needs to match how we seperate runs in the output of create_runs
                    writer.write(line + "\n");
                    writer.close();
                    cycle = (cycle == drs.numOfFiles) ? 1 : cycle + 1;
                    writer = new BufferedWriter(new FileWriter((filename + cycle), true));
                }
                else {
                    writer.write(line + "\n");
                }
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
