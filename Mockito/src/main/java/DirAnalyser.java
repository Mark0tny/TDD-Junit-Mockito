import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirAnalyser {

    private FileAnalyser fileAnalyser;

    DirAnalyser(FileAnalyser fileAnalyser) {
        this.fileAnalyser = fileAnalyser;
    }

    public int countLetters(String dirPath, String fileExtension) {
        int sum = 0;
        try (Stream<Path> walk = Files.walk(Paths.get(dirPath))) {
            List<String> result = walk.map(Path::toString)
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
            sum = calculateSum(result);
        } catch (IOException e) {
            return 0;
        }
        return sum;
    }

    private int calculateSum(List<String> filePaths) {
        int sum = 0;
        for (String path : filePaths) {
            sum += fileAnalyser.countLetters(path);
        }
        return sum;
    }
}
