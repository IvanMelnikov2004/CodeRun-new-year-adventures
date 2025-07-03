import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static int solve(int n, int[] a) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int p : a) {
            int start = p - (n - 1);
            int end = p + 1;
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
        }

        int current = 0;
        int maxCount = 0;
        for (int change : map.values()) {
            current += change;
            if (current > maxCount) {
                maxCount = current;
            }
        }
        return n - maxCount;
    }


}
