package org.example;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        logger.info("Start running ...");
        var chunkSize = Configuration.defaultChunkSize;
        var taskExecutor = Executors.newFixedThreadPool(Configuration.defaultThreadPoolSize);
        var aggregator = new Aggregator(new ResultsPrinter());

        FileChunk chunk;
        try (FileProcessor chunkReader = new FileProcessor(Configuration.file_url, chunkSize)) {
            while ((chunk = chunkReader.getNextFileChunk()) != null) {
                taskExecutor.submit(new Matcher(chunk, Configuration.namesToMatch, aggregator));
            }
        } catch (Exception e) {
            logger.severe("Exception while processing file");
        } finally {
            taskExecutor.shutdown();
            aggregator.printAll();
        }

    }
}