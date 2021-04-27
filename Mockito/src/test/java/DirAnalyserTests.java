import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DirAnalyserTests {

    private static final String EMPTYDIR = "emptyDir";
    private static final String DIR = "dirAnalyser";
    private static final String TXT = "txt";
    private static final String XML = "xml";

    @Test
    void emptyDir() {
        FileAnalyser fileAnalyser = mock(FileAnalyser.class);
        DirAnalyser dirAnalyser = new DirAnalyser(fileAnalyser);
        assertEquals(0, dirAnalyser.countLetters(getPath(EMPTYDIR), TXT));
    }

    @Test
    void dirWithTxtFiles() {
        FileAnalyser fileAnalyser = mock(FileAnalyser.class);
        DirAnalyser dirAnalyser = new DirAnalyser(fileAnalyser);
        when(fileAnalyser.countLetters(getPath("dirAnalyser/data_6.txt"))).thenReturn(6);
        when(fileAnalyser.countLetters(getPath("dirAnalyser/data_9.txt"))).thenReturn(9);
        when(fileAnalyser.countLetters(getPath("dirAnalyser/data_18.txt"))).thenReturn(18);
        assertEquals(33, dirAnalyser.countLetters(getPath(DIR), TXT));
    }

    @Test
    void dirWithXMLFiles() {
        FileAnalyser fileAnalyser = mock(FileAnalyser.class);
        DirAnalyser dirAnalyser = new DirAnalyser(fileAnalyser);
        when(fileAnalyser.countLetters(getPath("dirAnalyser/data1.xml"))).thenReturn(2);
        when(fileAnalyser.countLetters(getPath("dirAnalyser/data2.xml"))).thenReturn(3);
        assertEquals(5, dirAnalyser.countLetters(getPath(DIR), XML));
    }

    private String getPath(String fileName) {
        String absolutePath = fileName;
        try {
            URL res = getClass().getClassLoader().getResource(fileName);
            File file = null;
            if (res != null){
                file = Paths.get(res.toURI()).toFile();
                absolutePath = file.getAbsolutePath();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return absolutePath;
    }
}
