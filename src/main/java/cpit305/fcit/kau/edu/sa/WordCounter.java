package cpit305.fcit.kau.edu.sa;

import java.nio.file.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class WordCounter {
    private static final String MOBY_DICK_URL = "https://www.gutenberg.org/cache/epub/2701/pg2701.txt";

    public static long countWords() throws IOException {
        // Download file
        Path tempFile = Files.createTempFile("moby-dick", ".txt");
        URL url = new URL(MOBY_DICK_URL);
        Files.copy(url.openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        // Count words using simple string operations
        long wordCount = 0;
        try {
            return wordCount;
        } finally {

        }
    }
}