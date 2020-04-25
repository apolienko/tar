package tar;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TarTest {

    private String separator = File.separator;

    private String inputFile1 = "src" + separator + "test" + separator + "resources" + separator + "test1.txt";
    private String inputFile2 = "src" + separator + "test" + separator + "resources" + separator + "test2.txt";
    private String outputFile = "src" + separator + "test" + separator + "resources" + separator + "connectedFile.txt";
    private String restoredFile1 = "src" + separator + "test" + separator + "resources" + separator + "test1.txt-restored";
    private String restoredFile2 = "src" + separator + "test" + separator + "resources" + separator + "test2.txt-restored";
    private String nonexistentFile1 = "src" + separator + "test" + separator + "resources" + separator + "EmptyPackage" + "test1.txt";
    private String nonexistentFile2 = "src" + separator + "test" + separator + "resources" + separator + "EmptyPackage" + "test2.txt";


    @Test
    void connect() throws IOException {

        Main.main(new String[]{inputFile1, inputFile2, "-out", outputFile});
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line = reader.readLine();
            assertEquals("src\\test\\resources\\test1.txt", line);
            line = reader.readLine();
            assertEquals("       Hello! It is my first console program", line);
            line = reader.readLine();
            assertEquals("  Some text", line);
            line = reader.readLine();
            assertEquals("234234234323423423", line);
            line = reader.readLine();
            assertEquals("Файл src\\test\\resources\\test1.txt содержит 3 строк(и).", line);
            line = reader.readLine();
            assertEquals("src\\test\\resources\\test2.txt", line);
            line = reader.readLine();
            assertEquals("      123 Checking working capacity", line);
            line = reader.readLine();
            assertEquals("Some words", line);
            line = reader.readLine();
            assertEquals("Файл src\\test\\resources\\test2.txt содержит 2 строк(и).", line);
        }

    }


    @Test
    void spliter() throws IOException {

        Main.main(new String[]{"-u", outputFile});
        try (BufferedReader reader = new BufferedReader(new FileReader(restoredFile1))) {
            String line = reader.readLine();
            assertEquals("       Hello! It is my first console program", line);
            line = reader.readLine();
            assertEquals("  Some text", line);
            line = reader.readLine();
            assertEquals("234234234323423423", line);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(restoredFile2))) {
            String line = reader.readLine();
            assertEquals("      123 Checking working capacity", line);
            line = reader.readLine();
            assertEquals("Some words", line);
        }


    }

    @Test
    void exceptionTests() {

        String[] args1 = new String[]{nonexistentFile1, nonexistentFile2, "-out", outputFile};
        assertThrows(IOException.class, () -> Main.main(args1));

        String[] args2 = new String[]{"-u", nonexistentFile2};
        assertThrows(IOException.class, () -> Main.main(args2));


    }
}