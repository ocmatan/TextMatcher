package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
class MatcherTest {

    private Aggregator aggregatorMock;

    @BeforeEach
    void setUp(){
        aggregatorMock = mock(Aggregator.class);//clear mock before each run
    }

    @Test
    void TestMatcherCallSingleLineAllNameOptions() {
        FileChunk fileChunk = new FileChunk(0, "John is walking with Johnny and Johnson to John's house. Also, john went home.");
        var matcher = new Matcher(fileChunk, Arrays.asList("John"), aggregatorMock);
        try{
            var matchResultList = matcher.call();
            assertEquals(3, matchResultList.size());
            assertEquals(1, matchResultList.get(0).getLineOffset());
            assertEquals(0, matchResultList.get(0).getCharOffset());
            assertEquals(1, matchResultList.get(1).getLineOffset());
            assertEquals(43, matchResultList.get(1).getCharOffset(), 43);
            assertEquals(1, matchResultList.get(2).getLineOffset(), 1);
            assertEquals(63, matchResultList.get(3).getCharOffset());
           matchResultList.forEach(match-> verify(aggregatorMock).saveResult("John", match));

        }catch (Exception e){ }
    }
    @Test
    void TestMatcherCallMultipleLines() {
        FileChunk fileChunk = new FileChunk(0, "John is walking with Johnny.\nHome is where john is.\nLast line without result");
        var matcher = new Matcher(fileChunk, Arrays.asList("John"), aggregatorMock);
        try{
            var matchResultList = matcher.call();
            assertEquals(matchResultList.size(), 2);
            assertEquals(1, matchResultList.get(0).getLineOffset());
            assertEquals(0, matchResultList.get(0).getCharOffset());
            assertEquals(2, matchResultList.get(1).getLineOffset());
            assertEquals(14, matchResultList.get(1).getCharOffset());
            matchResultList.forEach(match-> verify(aggregatorMock).saveResult("John", match));
        }catch (Exception e){ }

    }

    @Test
    void TestMatcherCallMultipleTextsToMatch() {
        FileChunk fileChunk = new FileChunk(0, "Steven is walking with Carl to John's house");
        var matcher = new Matcher(fileChunk, Arrays.asList("Steven","Carl", "John"), aggregatorMock);

        try{
            var matchResultList = matcher.call();
            assertEquals(3, matchResultList.size());
            assertEquals("Steven", matchResultList.get(0).getMatchedText());
            assertEquals(1, matchResultList.get(0).getLineOffset());
            assertEquals(0, matchResultList.get(0).getCharOffset());
            verify(aggregatorMock).saveResult("Steven", matchResultList.get(0));

            assertEquals("Carl", matchResultList.get(1).getMatchedText());
            assertEquals(1, matchResultList.get(1).getLineOffset());
            assertEquals(23, matchResultList.get(1).getCharOffset());
            verify(aggregatorMock).saveResult("Carl", matchResultList.get(1));

            assertEquals("John", matchResultList.get(2).getMatchedText());
            assertEquals(1, matchResultList.get(2).getLineOffset());
            assertEquals(31, matchResultList.get(2).getCharOffset());
            verify(aggregatorMock).saveResult("John", matchResultList.get(2));

        }catch (Exception e){ }

    }
}