import java.io.*;
import java.util.PriorityQueue;

public class TangerinesAndOranges {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int totalBoxes = 2 * n - 1;
        long[][] boxes = new long[totalBoxes][3];

        long totalMandarins = 0, totalOranges = 0;

        for (int i = 0; i < totalBoxes; i++) {
            String[] input = br.readLine().split(" ");
            boxes[i][0] = Long.parseLong(input[0]); // Мандарины
            boxes[i][1] = Long.parseLong(input[1]); // Апельсины
            boxes[i][2] = i + 1; // Индекс
            totalMandarins += boxes[i][0];
            totalOranges += boxes[i][1];
        }

        long halfMandarins = (totalMandarins + 1) / 2;
        long halfOranges = (totalOranges + 1) / 2;

        PriorityQueue<long[]> maxMandarins = new PriorityQueue<>((a, b) -> (b[0] != a[0]) ? Long.compare(b[0], a[0]) : Long.compare(b[1], a[1]));
        PriorityQueue<long[]> maxOranges = new PriorityQueue<>((a, b) -> (b[1] != a[1]) ? Long.compare(b[1], a[1]) : Long.compare(b[0], a[0]));

        for (long[] box : boxes) {
            maxMandarins.offer(box);
            maxOranges.offer(box);
        }

        boolean[] selectedIndexes = new boolean[totalBoxes + 1];
        int selectedIndexesCount = 0;
        long mandarinSum = 0, orangeSum = 0;

        while (selectedIndexesCount < n) {
            long[] box1 = maxMandarins.peek();
            long[] box2 = maxOranges.peek();

            while (selectedIndexes[(int)box1[2]]) {
                maxMandarins.poll();
                box1 = maxMandarins.peek();
            }
            while (selectedIndexes[(int)box2[2]]) {
                maxOranges.poll();
                box2 = maxOranges.peek();
            }

            long mandarinDeficit = Math.max(0, halfMandarins - mandarinSum);
            long orangeDeficit = Math.max(0, halfOranges - orangeSum);

            long gain1 = Math.min(mandarinDeficit, box1[0]) + Math.min(orangeDeficit, box1[1]);
            long gain2 = Math.min(mandarinDeficit, box2[0]) + Math.min(orangeDeficit, box2[1]);
            long[] profitBox;
            if (gain1 >= gain2){
                profitBox = maxMandarins.poll();
            }
            else{
                profitBox = maxOranges.poll();
            }

            selectedIndexes[(int)profitBox[2]] = true;
            mandarinSum += profitBox[0];
            orangeSum += profitBox[1];

            selectedIndexesCount += 1;
        }



        for (int i = 0; i < totalBoxes + 1; i++) {
            if (selectedIndexes[i]){
                bw.write(i + " ");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
