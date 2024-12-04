package org.example;

public class Match
{
    public Match(String matchedText, int lineOffset, int charOffset) {
        this.matchedText = matchedText;
        this.lineOffset = lineOffset;
        this.charOffset = charOffset;
    }

    private String matchedText;
    private int lineOffset;
    private int charOffset;

    public String getMatchedText() {
        return matchedText;
    }

    public int getLineOffset() {
        return lineOffset;
    }

    public int getCharOffset() {
        return charOffset;
    }


    @Override
    public String toString() {
        return "Match{" +
                "matchedText='" + matchedText + '\'' +
                ", lineOffset=" + lineOffset +
                ", charOffset=" + charOffset +
                '}';
    }

    public String getMatchOffsetAsString(){
        return "{lineOffset=" + lineOffset +",charOffset=" + charOffset+"}";
    }
}
