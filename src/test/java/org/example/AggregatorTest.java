package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class AggregatorTest {

    @Test
    void printAllTest() {
        var printerMock = Mockito.mock(ResultsPrinter.class);
        var aggregator = new Aggregator(printerMock);
        aggregator.saveResult("my_value", new Match("text_to_match", 13, 400));
        aggregator.printAll();
        Mockito.verify(printerMock).printAll(Mockito.anyMap());
    }
}