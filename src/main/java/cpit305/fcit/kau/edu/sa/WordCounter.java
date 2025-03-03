package cpit305.fcit.kau.edu.sa;

import java.nio.file.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class WordCounter {

    public static long countWordsByFileUrl(String downloadUrl) throws IOException {
        // Download file
        Path tempFile = Files.createTempFile("tmpFile", ".txt");
        URL url = new URL(downloadUrl);
        Files.copy(url.openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        // Count words using simple string operations
        long wordCount = 0;
        try {

            return wordCount;
        } finally {

        }
    }
}