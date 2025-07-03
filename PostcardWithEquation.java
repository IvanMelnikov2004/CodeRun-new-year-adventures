import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class PostcardWithEquation {

    //Посчитаем простые числа решетом Эратосфена
    public static List<Long> getPrimesUpTo(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Long> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add((long) i);
            }
        }
        return primes;
    }

    //Для числа x определим p(x)
    public static int countDistinctPrimeFactors(long x, List<Long> primes) {
        int count = 0;
        for (long prime : primes) {
            if (prime * prime > x) break;
            if (x % prime == 0) {
                count++;
                while (x % prime == 0) {
                    x /= prime;
                }
            }
        }
        if (x > 1) count++;
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(reader.readLine().trim());
        int res = 0;

        //Массив простых чисел
        List<Long> primes = getPrimesUpTo(1_000_000);

        //Перебираем 100 чисел перед n включая n. Если 100 чисел не наберется, то перебираем от 1 до n
        for (long x = Math.max(1, n - 100); x <= n; x++) {
            //Для x находим p(x)
            int p_x = countDistinctPrimeFactors(x, primes);
            //Проверяем удовлетворяет ли x условию.
            if (x + p_x == n) {
                res++;
            }
        }

        writer.write(String.valueOf(res));
        reader.close();
        writer.close();
    }
}