import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeFactorsTests {

    private PrimeFactorsCalculator calculator = new PrimeFactorsCalculator();

    @Test
    void testForSmallNumbers() {
        check(Collections.EMPTY_LIST, 0);
        check(Collections.EMPTY_LIST, 1);
        check(Arrays.asList(2), 2);
        check(Arrays.asList(3), 3);
        check(Arrays.asList(2, 2), 4);
        check(Arrays.asList(5), 5);
        check(Arrays.asList(2, 3), 6);
        check(Arrays.asList(7), 7);
        check(Arrays.asList(2, 2, 2), 8);
        check(Arrays.asList(3, 3), 9);
        check(Arrays.asList(2, 5), 10);
    }

    @Test
    void testForBigNumbers() {
        check(Arrays.asList(2, 5, 5, 5));
        check(Arrays.asList(2, 3, 3, 3, 5));
        check(Arrays.asList(2, 3, 3, 3, 11));
    }

    private void check(List<Integer> primesList, int number) {
        assertEquals(primesList, calculator.calculatePrimes(number));
    }

    private void check(List<Integer> primesList) {
        check(primesList, calaculateBigNumber(primesList));
    }

    private int calaculateBigNumber(List<Integer> list) {
        return list.stream().reduce(1, Math::multiplyExact);
    }
}
