# README: COMPX301 Assignment 1 - External sort merge

Name: Zachary Cui - ID: 1364880
Name: Bhavit Wadhwa - ID: 1516846


## Current working functionality:
- Min Heap functionally complete
- Text files can be piped in using CreateRuns.java which uses the heap to successfuly create multiple runs
- Distributes runs successfuly distributes runs into 2 seperate files
- To test distribute runs independently, use DistributeRuns_standalone and pipe in a text file resembling an output from CreateRuns in this format: 

"
(line of text goes here)
...
(another line of text goes here)
END_OF_RUNS
(another line of text goes here)
...
"
Where ... represents the numerous lines of text being added, and END_OF_RUNS being the demarcation

## Implemented functionality for Merge Runs:
- Ability to pipe out the final merged file into the
- Merged the first iteration of distributed files


## Issue with Merge Runs:
- Merge runs must be tested by running DistributeRuns_standalone to create the distributed files. 
- Depending on size of your test file, some strings may be missing. This behaviour was observed with a test file containing 18000 lines of text.
- Please branch 'Zachary_fixed_merge'




