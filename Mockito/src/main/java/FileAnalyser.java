import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileAnalyser {

    public int countLetters(String filepath) {
        List<String> data = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filepath))) {
            data = lines.map(line -> line.replaceAll("[^a-zA-Z]", ""))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.stream().mapToInt(String::length).sum();
    }

}
