package org.example;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class Aggregator {
    private static final Logger logger = Logger.getLogger(Aggregator.class.getName());
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> results;
    private ResultsPrinter printer;
    public Aggregator(ResultsPrinter resultsPrinter) {
        this.results = new ConcurrentHashMap<>();
        this.printer = resultsPrinter;
    }
    public void printAll(){
        logger.info("File scan completed, here are the results:");
        printer.printAll(results);
    }
    public void saveResult(String value, Match match){
        results.computeIfAbsent(value, key -> new ConcurrentLinkedQueue<>()).add(match.getMatchOffsetAsString());
        logger.fine("match found: " + value + ", match details: " + match.getMatchOffsetAsString());
    }
}
