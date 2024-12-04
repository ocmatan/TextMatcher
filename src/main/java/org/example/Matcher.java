package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Matcher implements Callable<List<Match>> {

    private static final Logger logger = Logger.getLogger(Matcher.class.getName());
    FileChunk input;
    List<String> textsToMatch;
    Aggregator aggregator;

    Matcher(FileChunk input, List<String> textsToMatch, Aggregator aggregator){
        this.input =  input;
        this.textsToMatch = textsToMatch;
        this.aggregator = aggregator;
    }
    @Override
    public List<Match> call() {
        List<Match> result = new ArrayList<>();
        try{
            String[] lines = input.getData().split("\n");
            int lineIndex = 1;
            for (String line: lines) {
                for(String textToMatch : textsToMatch){
                    String regex = "(?i)\\b" + textToMatch + "\\b|'"+ textToMatch +"\\b"; // Case-insensitive matching and possessive form('s)
                    Pattern pattern = Pattern.compile(regex);
                    java.util.regex.Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        var match = new Match(textToMatch, input.getStartLine()+lineIndex, matcher.start());
                        aggregator.saveResult(textToMatch, match);
                        result.add(match);
                    }
                }
                lineIndex++;
            }
        }catch (Exception e){
            logger.severe("Failed to process file chunk");
        }
        return result;
    }

}