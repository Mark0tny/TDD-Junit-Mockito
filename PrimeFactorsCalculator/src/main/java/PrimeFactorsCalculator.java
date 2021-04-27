import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeFactorsCalculator {

    public List calculatePrimes(int number) {
        ArrayList<Integer> primes = new ArrayList();
        for (int i = 2; i <= number; i++) {
            while (number % i == 0) {
                number /= i;
                primes.add(i);
            }
        }
        return primes;
    }
}
