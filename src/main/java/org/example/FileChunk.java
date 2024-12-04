package org.example;

public class FileChunk {
        private final int startLine;
        private final String data;

        public FileChunk(int startLine, String data) {
            this.startLine = startLine;
            this.data = data;
        }

        public int getStartLine() {
            return startLine;
        }

        public String getData() {
            return data;
        }
    }