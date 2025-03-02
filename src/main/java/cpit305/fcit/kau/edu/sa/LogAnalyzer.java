package cpit305.fcit.kau.edu.sa;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.nio.channels.*;
import java.nio.MappedByteBuffer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LogAnalyzer {
    private static final Map<String, Integer> PATTERNS = new HashMap<>();
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Map<String, Integer> analyzeLogPatterns(Path logFile) throws IOException {
        try (FileChannel channel = FileChannel.open(logFile, StandardOpenOption.READ);
             FileLock lock = channel.lock(0, Long.MAX_VALUE, true)) {

            MappedByteBuffer buffer = channel.map(
                    FileChannel.MapMode.READ_ONLY, 0, channel.size());

            String content = new String(buffer.array());
            Map<String, Integer> patterns = new HashMap<>();

            countPattern(content, "[ERROR]", patterns);
            countPattern(content, "[WARN]", patterns);
            countPattern(content, "[INFO]", patterns);

            return patterns;
        }
    }

    private static void countPattern(String content, String pattern, Map<String, Integer> patterns) {
        int count = 0;
        int lastIndex = 0;
        while ((lastIndex = content.indexOf(pattern, lastIndex)) != -1) {
            count++;
            lastIndex += pattern.length();
        }
        patterns.put(pattern, count);
    }

    public static void appendEntry(Path logFile, String entry) throws IOException {
        try (FileChannel channel = FileChannel.open(logFile,
                StandardOpenOption.WRITE, StandardOpenOption.APPEND);
             FileLock lock = channel.lock()) {

            String timestamp = LocalDateTime.now().format(FORMATTER);
            String logEntry = String.format("%s %s%n", timestamp, entry);

            channel.write(ByteBuffer.wrap(logEntry.getBytes()));
        }
    }
}
