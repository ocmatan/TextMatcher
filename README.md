"# TextMatcher" 

Run the main method to get a running example with the values mentioned in the task, the output will be available in stdout. 

Possible improvements that were out of my time scope:
1. fail-safe file access for better resilience, retries, timeout and error handling.
2. Provide different input for the file processor, other than web resources.
3. Dynamic thread-pool size initialization and file chunk size according to the input file size, for optimized resource utilization.
4. Printer - output results to a file or another destination instead of stdout
5. initialize the aggregator, printer and fileProcessor as singletons 