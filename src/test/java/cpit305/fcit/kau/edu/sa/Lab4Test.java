package cpit305.fcit.kau.edu.sa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class Lab4Test {
        @TempDir
        Path tempDir;

        @Test
        void testCreateFile() throws IOException {
            Path filePath = tempDir.resolve("test.txt");
            String result = FileUtils.createFile(filePath.toString());
            assertTrue(result.contains("created successfully"));
            assertTrue(Files.exists(filePath));
        }

        @Test
        void testCreateDirectory() throws IOException {
            Path dirPath = tempDir.resolve("testDir");
            String result = FileUtils.createDirectory(dirPath.toString());
            assertTrue(result.contains("created successfully"));
            assertTrue(Files.isDirectory(dirPath));
        }

        @Test
        void testCreateDirectories() throws IOException {
            Path nestedPath = tempDir.resolve("a/b/c");
            String result = FileUtils.createDirectories(nestedPath.toString());
            assertTrue(result.contains("created successfully"));
            assertTrue(Files.isDirectory(nestedPath));
        }

        @Test
        void testFileOperations() throws IOException {
            // Create source file
            Path source = tempDir.resolve("source.txt");
            Files.writeString(source, "test content");

            // Test copy
            Path target = tempDir.resolve("target.txt");
            String copyResult = FileUtils.copyFile(source.toString(), target.toString());
            assertTrue(copyResult.contains("copied"));
            assertTrue(Files.exists(target));

            // Test move
            Path moved = tempDir.resolve("moved.txt");
            String moveResult = FileUtils.moveFile(target.toString(), moved.toString());
            assertTrue(moveResult.contains("moved"));
            assertTrue(Files.exists(moved));
            assertFalse(Files.exists(target));

            // Test delete
            String deleteResult = FileUtils.deleteIfExists(moved.toString());
            assertTrue(deleteResult.contains("deleted successfully"));
            assertFalse(Files.exists(moved));
        }
    }
