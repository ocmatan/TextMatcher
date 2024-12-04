package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    @Test
    void getNextFileChunkBigFileTest() throws Exception{
        var fileProcessor = new FileProcessor("http://norvig.com/big.txt", 10);

        var chunk1 = fileProcessor.getNextFileChunk();
        assertEquals( 0, chunk1.getStartLine());
        assertTrue(chunk1.getData().startsWith("The Project Gutenberg EBook of The Adventures of Sherlock Holmes"));

        var chunk2 = fileProcessor.getNextFileChunk();
        assertEquals( 10, chunk2.getStartLine());
        assertTrue(chunk2.getData().startsWith("header without written permission."));

        var chunk3 = fileProcessor.getNextFileChunk();
        assertEquals( 20, chunk3.getStartLine());
        assertTrue(chunk3.getData().contains("**eBooks Readable By Both Humans and By Computers, Since 1971**"));

    }

    @Test
    void getNextFileChunkSingleChunkTest() throws Exception{
        var fileProcessor = new FileProcessor("https://example-files.online-convert.com/document/txt/example.txt", 100);
        var chunk1 = fileProcessor.getNextFileChunk();
        assertEquals( 0, chunk1.getStartLine());
        assertTrue(chunk1.getData().startsWith("TXT test file"));
        assertTrue(chunk1.getData().endsWith("Feel free to use and share the file according to the license above.\n"));
        assertNull(fileProcessor.getNextFileChunk());




    }
}