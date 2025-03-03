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
        FileUtils.createFile(filePath.toString());
        assertTrue(Files.exists(filePath));
    }

    @Test
    void testCreateDirectory() throws IOException {
        Path dirPath = tempDir.resolve("testDir");
        FileUtils.createDirectory(dirPath.toString());
        assertTrue(Files.isDirectory(dirPath));
    }

    @Test
    void testCreateDirectories() throws IOException {
        Path nestedPath = tempDir.resolve("a/b/c");
        FileUtils.createDirectories(nestedPath.toString());
        assertTrue(Files.isDirectory(nestedPath));
    }

    @Test
    void testFileOperations() throws IOException {
        // Create source file
        Path source = tempDir.resolve("source.txt");
        Files.writeString(source, "test content");

        // Test copy
        Path target = tempDir.resolve("target.txt");
        FileUtils.copyFile(source.toString(), target.toString());
        assertTrue(Files.exists(target));

        // Test move
        Path moved = tempDir.resolve("moved.txt");
        FileUtils.moveFile(target.toString(), moved.toString());
        assertTrue(Files.exists(moved));
        assertFalse(Files.exists(target));

        // Test delete
        FileUtils.deleteIfExists(moved.toString());
        assertFalse(Files.exists(moved));
    }

    // Test countWordsByFileUrl
    @Test
    void testCountWordsByFileUrlWithThreeWords() throws IOException {
        Path threeWordsFile = tempDir.resolve("threeWords.txt");
        Files.writeString(threeWordsFile, "one two three");
        long wordCount = WordCounter.countWordsByFileUrl(threeWordsFile.toUri().toString());
        assertEquals(3, wordCount, "Word count should be 3 for a file with three words");
    }

    @Test
    void testCountWordsByFileUrl() throws IOException {
        String testUrl = "https://www.gutenberg.org/files/1342/1342-0.txt";
        long wordCount = WordCounter.countWordsByFileUrl(testUrl);
        assertTrue(wordCount > 0, "Word count should be greater than 0");
    }

    // Test Serializing and Deserializing
    @Test
    void testSerializeAndDeserialize() throws IOException, ClassNotFoundException {
        Student student = new Student("Ahmed Ali", 12345, 4.25);
        Path filePath = tempDir.resolve("student.dat");

        // Serialize the student object
        StudentSerializer.serialize(student, filePath.toString());

        // Deserialize the student object
        Student deserializedStudent = StudentSerializer.deserialize(filePath.toString());

        assertNotNull(deserializedStudent);
        assertEquals(student.getName(), deserializedStudent.getName());
        assertEquals(student.getId(), deserializedStudent.getId());
        assertEquals(student.getGpa(), deserializedStudent.getGpa(), 0.001);
    }
}
