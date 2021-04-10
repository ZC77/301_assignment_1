import java.io.*; 

public class MergeRuns {
    
    int k_ways = 3; 
    DistributeRuns dr;

    public static void main (String [] args) {

        MergeRuns mRuns = new MergeRuns();
        
        try{
            mRuns.merge();

        } catch (Exception e) {
            System.out.println("Usage: MergeRuns [k-ways] \n Miniumum is 2 way.");
        }
    }

    public void merge() {
        
        String filename = "RunFile_";
        String temp_filename = "temp_merge";
        BufferedWriter writer;
        int cycle = 3;

        try {
            for (int i = 1; i <= k_ways; i++) {
                String next = filename + (i+2);
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

            writer = new BufferedWriter(new FileWriter((filename + cycle), true));

            for (int i = 1; i <= 2; i++) {

                while (!(line_reader1 == null && line_reader2 == null)) {
                   // System.out.println("LR1: " + line_reader1 + " LR2: " + line_reader2);
                    if (line_reader1 == null || line_reader2 == null) {
                        if (line_reader1 == null) { // reader 1 ran till the end
                            while (!(line_reader2.equals("END_OF_RUN"))) { // Then just write everything from reader 2
                                writer.write(line_reader2 +"\n");
                                System.out.println("Pulled " + line_reader2 + " from L2 (OR loop)");
                                line_reader2 = reader2.readLine();
                            }
                        }
                        if (line_reader2 == null) { // reader 2 ran till the end 
                            while (!(line_reader1.equals("END_OF_RUN"))) { // Then just write everything from reader 1
                                writer.write(line_reader1 +"\n");
                                System.out.println("Pulled " + line_reader1 + " from L1 (OR loop)");
                                line_reader1 = reader1.readLine(); 
                            }
                        }
                        writer.write("END_OF_RUN\n");
                        line_reader1 = reader1.readLine(); 
                        line_reader2 = reader2.readLine();
                        writer.flush();
                        cycle++;
                        writer = new BufferedWriter(new FileWriter((filename + cycle), true));
                    } else {
                        if (!(line_reader1.equals("END_OF_RUN") && line_reader2.equals("END_OF_RUN"))) {
                            if (line_reader2 == null || line_reader2.equals("END_OF_RUN") || line_reader1.compareTo(line_reader2) >= 0) {
                                // L1 >= L2 OR L2 is end of run, OR L2 has nothing left
                                writer.write(line_reader1 + "\n");
                                System.out.println("Pulled " + line_reader1 + " from L1 (AND loop)");
                                line_reader1 = reader1.readLine();
                                
                            } else if (line_reader1 == null || line_reader1.equals("END_OF_RUN") || line_reader2.compareTo(line_reader1) >= 0) {
                                // L2 >= L1 OR L1 is end of run, OR L1 has nothing left
                                writer.write(line_reader2 + "\n");
                                System.out.println("Pulled " + line_reader2 + " from L2 (AND loop)");
                                line_reader2 = reader2.readLine();
                                
                            }
                        } else if (line_reader1.equals("END_OF_RUN") && line_reader2.equals("END_OF_RUN")){
        
                            cycle++;
                            line_reader1 = reader1.readLine();
                            line_reader2 = reader2.readLine();
                            writer.write("END_OF_RUN\n");
                            writer.flush();
                            writer = new BufferedWriter(new FileWriter((filename + cycle), true));
                            System.out.println(cycle);
                            System.out.println("Both line readers reached EOR.");
                        }
    
                    }
                }
                writer.flush();
                reader1 = new BufferedReader(new FileReader(filename + 3));
                reader2 = new BufferedReader(new FileReader(filename + 4));
                line_reader1 = reader1.readLine();
                line_reader2 = reader2.readLine();
                System.out.println("iteration" + line_reader1 + " , " + line_reader2);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}