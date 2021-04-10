//Name: Zachary Cui - ID: 1364880
//Name: Bhavit Wadhwa - ID: 1516846

import java.io.*; 

public class MergeRuns {
    
    DistributeRuns dr;

    public static void main (String [] args) {

        MergeRuns mRuns = new MergeRuns();
        mRuns.merge();
    }

    public void merge() {
        
        // Variables needed for merging process
        String filename = "RunFile_";
        BufferedWriter writer;
        int cycle = 3;
        int iterations = 2;
        int reading = 1;

        try {
            // Create the temp. files necessary for our merge. 
            // No. of files to be created = no. of merge iterations 
            for (int i = 1; i <= (iterations + 1); i++) {
                String next = filename + (i+2);
                writer = new BufferedWriter(new FileWriter(next));
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("File creation error: " + e);
        }

        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(filename + (reading)));
            BufferedReader reader2 = new BufferedReader(new FileReader(filename + (reading + 1)));
            String line_reader1 = reader1.readLine();
            String line_reader2 = reader2.readLine();
            
            //Add writer to new run file
            writer = new BufferedWriter(new FileWriter((filename + cycle), true));
            
            //For each iteration
            for (int i = 0; i < iterations; i++) {

                //Until both files have no more lines to read
                while (!(line_reader1 == null && line_reader2 == null)) {
    
                    // Have we reached the end of one file, if we have then just write the last odd run
                    if (line_reader1 == null || line_reader2 == null) {
                        if (line_reader1 == null) { // reader 1 ran till the end
                            while (!(line_reader2.equals("END_OF_RUN"))) { // Then just write everything from reader 2
                                writer.write(line_reader2 +"\n");
                                System.out.println("Pulled " + line_reader2 + " from L2");
                                line_reader2 = reader2.readLine();
                            }
                        }
                        if (line_reader2 == null) { // reader 2 ran till the end 
                            while (!(line_reader1.equals("END_OF_RUN"))) { // Then just write everything from reader 1
                                writer.write(line_reader1 +"\n");
                                System.out.println("Pulled " + line_reader1 + " from L1");
                                line_reader1 = reader1.readLine(); 
                            }
                        }
                        writer.write("END_OF_RUN \n");
                        line_reader1 = reader1.readLine(); 
                        line_reader2 = reader2.readLine();
                        writer.close();
                    }
                    
                    // Else we haven't reached the end of either file
                    else {
                        
                        //If we are not at the end of either  files, then write to file
                        if (!(line_reader1.equals("END_OF_RUN") && line_reader2.equals("END_OF_RUN"))) {
                            if (line_reader2 == null || line_reader2.equals("END_OF_RUN") || line_reader1.compareTo(line_reader2) >= 0) {
                                // L1 >= L2 OR L2 is end of run, OR L2 has nothing left
                                System.out.println("x");
                                writer.write(line_reader1 + "\n");
                                System.out.println("Pulled " + line_reader1 + " from L1");
                                line_reader1 = reader1.readLine();
                                
                            } else if (line_reader1 == null || line_reader1.equals("END_OF_RUN") || line_reader2.compareTo(line_reader1) >= 0) {
                                // L2 >= L1 OR L1 is end of run, OR L1 has nothing left
                                writer.write(line_reader2 + "\n");
                                System.out.println("Pulled " + line_reader2 + " from L2");
                                line_reader2 = reader2.readLine();
                                
                            }

                        //Else if we have reached the end of run on both files being read, move onto the next temp file to write our 
                        // merged results on.
                        } else if (line_reader1.equals("END_OF_RUN") && line_reader2.equals("END_OF_RUN")){
                            cycle++;
                            line_reader1 = reader1.readLine();
                            line_reader2 = reader2.readLine();
                            writer.write("END_OF_RUN \n");
                            writer.close();
                            writer = new BufferedWriter(new FileWriter((filename + cycle)));
                            System.out.println("Now opening: " + (filename + cycle));
                            System.out.println("Both line readers reached EOR.");
                        }
                    }
                }

                // Update our readers to now read the new files we just created, so next iteration can merge those newly created temp files
                if (i != iterations - 1) {
                    reading += 2;
                    reader1 = new BufferedReader(new FileReader(filename + (reading)));
                    reader2 = new BufferedReader(new FileReader(filename + (reading + 1)));
                    line_reader1 = reader1.readLine();
                    line_reader2 = reader2.readLine();
                }
            }

            writer.close();
            
            
            //Pipe the final merged file using the reader and output writer
            try {
                BufferedWriter pipedOutputWriter = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedReader mergedFileReader = new BufferedReader(new FileReader(filename + 1));
                String fileRead = "tmp";
                while (fileRead != "END_OF_RUN") {
                    fileRead = mergedFileReader.readLine();
                    pipedOutputWriter.write(fileRead + "\n");
                }
                mergedFileReader.close();
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
