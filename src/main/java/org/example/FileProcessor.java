package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FileProcessor  implements AutoCloseable{
    private final BufferedReader reader;
    private final int chunkSize;
    private int currentLineOffset;

    public FileProcessor(String fileUrl, int chunkSize) throws Exception {
        this.reader = new BufferedReader(new InputStreamReader(new URL(fileUrl).openStream()));
        this.chunkSize = chunkSize;
        this.currentLineOffset = 0;
    }

    public FileChunk getNextFileChunk() throws Exception {
        StringBuilder chunkBuilder = new StringBuilder();
        int lineCount = 0;
        String line;
        int startLineOffset = currentLineOffset;

        while (lineCount < chunkSize && (line = reader.readLine()) != null) {
            chunkBuilder.append(line).append("\n");
            lineCount++;
            currentLineOffset++;
        }
        return lineCount > 0 ? new FileChunk(startLineOffset, chunkBuilder.toString()) : null;
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }

}
