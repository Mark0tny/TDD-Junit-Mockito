import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String SEPARATOR = ",";
    private static final String SEPARATOR_NEW_LINE = "\n";
    private static final String EXCEPTION_MESSAGE = "negatives not allowed";

    public int add(String s) {
        List<Integer> numbers = new ArrayList<>();
        String[] tempString = getNumbersFormString(s).split(extractSeparator(s));
        for (String value : tempString) {
            if (!value.isEmpty()) {
                numbers.add(Integer.parseInt(value.trim()));
            }
        }
        checkForNegatives(numbers);
        return sumNumbers(numbers);
    }

    private void checkForNegatives(List<Integer> numbers) {
        List negatives = numbers.stream().filter(number -> number < 0).collect(Collectors.toList());
        if (negatives.size() != 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE + ":" + negatives.toString());
        }
    }

    private int sumNumbers(List<Integer> numbers) {
        return numbers.stream().filter(number -> number <= 1000).reduce(0, Math::addExact);
    }

    private String extractSeparator(String s) {
        if (s.startsWith("//")) {
            if (s.contains("[") && s.contains("]")) {
                return findDelimiters(s);
            }
            return s.substring(2, 3);
        } else {
            return createSimpleSeparator();
        }
    }

    private String getNumbersFormString(String s) {
        if (s.startsWith("//")) {
            return s.substring(s.indexOf("\n"));
        } else return s;
    }

    private String createSimpleSeparator() {
        return SEPARATOR + "|" + SEPARATOR_NEW_LINE;
    }

    private String addBrackets(String s) {
        return "(" + s + ")";
    }

    private String findDelimiters(String s) {
        StringBuilder multipleSeparators = new StringBuilder();
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matchPattern = pattern.matcher(s);
        while (matchPattern.find()) {
            multipleSeparators.append(addBrackets(matchPattern.group(1))).append("|");
        }
        multipleSeparators.deleteCharAt(multipleSeparators.length() - 1);
        return multipleSeparators.toString();
    }


}
