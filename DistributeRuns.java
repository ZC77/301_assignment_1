import java.io.*;

public class DistributeRuns {

    int numOfFiles = 2;
    // Constructor
    public DistributeRuns (int numOfDistributedFiles) {
        if (numOfDistributedFiles >= 1 && numOfDistributedFiles < 100) {
            numOfFiles = numOfDistributedFiles;
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // Data which is being piped in
            BufferedWriter writer;
            String line;
            String filename = "RunFile_";
            int cycle = 1; 

            for (int i = 1; i <= numOfFiles; i++) {
                String next = filename + i;
                writer = new BufferedWriter(new FileWriter(next));
                writer.close();
            }

            // While not at the end, append lines onto each file until "end of line", then move onto next file, going round
            writer = new BufferedWriter(new FileWriter((filename + cycle), true));
            while ((line = reader.readLine()) != null) {
                
                if (line.equals("END_OF_RUN")) {
                    writer.close();
                    cycle = (cycle == numOfFiles) ? 1 : cycle + 1;
                    writer = new BufferedWriter(new FileWriter((filename + cycle), true));
                }
                else {
                    writer.write(line + "\n");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
