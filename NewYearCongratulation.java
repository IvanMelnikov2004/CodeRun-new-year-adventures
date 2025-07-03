import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NewYearCongratulation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = reader.readLine().split(" ");
        List<int[]> list = new ArrayList<>();
        long pqSum = 0;

        int n = Integer.parseInt(line[0]);
        int t = Integer.parseInt(line[1]);
        for (int i = 0; i < n; i++){
            line = reader.readLine().split(" ");
            int xi = Integer.parseInt(line[0]);
            int ti = Integer.parseInt(line[1]);
            int[] pair = {xi, ti};
            list.add(pair);
        }
        list.sort(((o1, o2) -> {
            if (o1[0] == o2[0]){
                return -Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        }));
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++){
            int[] pair = list.get(i);
            int xi = pair[0];
            int ti = pair[1];
            if (pqSum + ti + xi <= t || !pq.isEmpty() && pq.peek() > ti){
                pq.add(ti);
                pqSum += ti;
            }

            while (!pq.isEmpty() && pqSum + xi > t){
                pqSum -= pq.poll();
            }
            res = Math.max(res, pq.size());
        }
        writer.write(res+"");
        reader.close();
        writer.close();
    }
}
