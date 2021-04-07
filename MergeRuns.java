import java.io.*; 

public class MergeRuns {
    
    int k_ways = 2; 
    DistributeRuns dr;

    public static void main (String [] args) {

        MergeRuns mRuns = new MergeRuns();
        
        try{
            int k = Integer.parseInt(args[0]);
            mRuns.k_ways = k > 1 ? k : 2; 
            mRuns.merge();

        } catch (Exception e) {
            System.out.println("Usage: MergeRuns [k-ways] \n Miniumum is 2 way.");
        }
    }

    public void merge() {
        
        String filename = "RunFile_";
        String temp_filename = "temp_merge";
        BufferedWriter writer;
        int cycle = 1;

        try {
            for (int i = 1; i <= k_ways; i++) {
                String next = temp_filename + i;
                writer = new BufferedWriter(new FileWriter(next));
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("File creation error: " + e);
        }

        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(filename + 1));
            BufferedReader reader2 = new BufferedReader(new FileReader(filename + 2));
            String line_reader1 = reader1.readLine();
            String line_reader2 = reader2.readLine();

            //dr = new DistributeRuns(2);
            //dr.distribute(); // Create the distributed run files

            writer = new BufferedWriter(new FileWriter((temp_filename + cycle), true));
            while (!(line_reader1 == null && line_reader2 == null)) {

                if (!(line_reader1.equals("END_OF_RUN") && line_reader2.equals("END_OF_RUN"))) {
                    if (line_reader2 == null || line_reader2.equals("END_OF_RUN") || line_reader1.compareTo(line_reader2) >= 0) {
                        // L1 >= L2 OR L2 is end of run, OR L2 has nothing left
                        writer.write(line_reader1 + "\n");
                        System.out.println("Pulled " + line_reader1 + " from L1");
                        line_reader1 = reader1.readLine();
                        
                    } else if (line_reader1 == null || line_reader1.equals("END_OF_RUN") || line_reader2.compareTo(line_reader1) >= 0) {
                        // L2 >= L1 OR L1 is end of run, OR L1 has nothing left
                        writer.write(line_reader2 + "\n");
                        System.out.println("Pulled " + line_reader2 + " from L2");
                        line_reader2 = reader2.readLine();
                        
                    }
                } else if (line_reader1.equals("END_OF_RUN") && line_reader2.equals("END_OF_RUN")){

                    cycle = (cycle == k_ways) ? 1 : cycle + 1;
                    line_reader1 = reader1.readLine();
                    line_reader2 = reader2.readLine();
                    writer.write("END_OF_RUN \n");
                    writer.close();
                    writer = new BufferedWriter(new FileWriter((temp_filename + cycle), true));
                    System.out.println("Both line readers reached EOR.");
                }
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
