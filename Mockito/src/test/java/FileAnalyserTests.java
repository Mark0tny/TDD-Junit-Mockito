import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileAnalyserTests {

    private FileAnalyser fileAnalyser = new FileAnalyser();

    @Test
    void emptyFile() {
        check(0, "fileAnalyser/emptyData.txt");
    }

    @Test
    void fileWithOnlyChars() {
        check(9, "fileAnalyser/data_9.txt");
        check(5, "fileAnalyser/data_5.txt");
        check(3, "fileAnalyser/data_3.txt");
    }

    private String getPath(String fileName) {
        String absolutePath = fileName;
        try {
            URL res = getClass().getClassLoader().getResource(fileName);
            File file = null;
            file = Paths.get(res.toURI()).toFile();
            absolutePath = file.getAbsolutePath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return absolutePath;
    }

    private void check(int expectedSum, String fileName) {
        assertEquals(expectedSum, fileAnalyser.countLetters(getPath(fileName)));
    }
}
