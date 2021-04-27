import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTests {

    private StringCalculator stringCalculator = new StringCalculator();

    @Test
    void simpleStrings() {
        check(0, "");
        check(1, "1");
        check(2, "2");
        check(3, "1,2");
        check(5, "3,2");
        check(9, "4,5");

    }
    @Test
    void simpleStringsWithEndLine() {
        check(6, "1,2,3");
        check(8, "3\n2,3");
        check(10, "1\n2,3\n4");
    }

    @Test
    void anyDelimiters() {
        check(3, "//;\n1;2");
        check(8, "//#\n1#2#5");
    }

    @Test
    void negativeNumbers() {
        checkNegatives("-1,-2","[-1, -2]");
        checkNegatives("2,-3","[-3]");
    }

    @Test
    void numbersHigherThen1000() {
        check(1003, "//#\n1#2#1000");
        check(3, "//#\n1#2#1001");
        check(1, "//#\n1#1002#1001");
    }

    @Test
    void anyLengthOfDelimiters() {
        check(3, "//[;;;]\n1;;;2");
        check(8, "//[##]\n1##2##5");
    }

    @Test
    void multipleDelimiters() {
        check(6, "//[;][,]\n1;2,3");
        check(8, "//[#][!]\n1!2#5");
    }

    @Test
    void multipleDelimitersOfAnyLength() {
        check(6, "//[;][,]\n1;2,3");
        check(8, "//[#][!!]\n1!!2#5");
        check(9, "//[##][!!!!][@]\n1!!!!2##5@1");
    }

    private void check(int expectedSum, String string) {
        assertEquals(expectedSum, stringCalculator.add(string));
    }

    private void checkNegatives(String string,String negatives){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                stringCalculator.add(string));
        assertEquals("negatives not allowed:" + negatives, exception.getMessage());
    }
}
