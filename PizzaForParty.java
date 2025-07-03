import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PizzaForParty {
    static class Query {
        int L, R, index;

        Query(int L, int R, int index) {
            this.L = L - 1; // Переводим в 0-индексацию
            this.R = R - 1;
            this.index = index;
        }
    }

    static int BLOCK_SIZE;
    static int[] arr;
    static int[] freq;
    static int[] countFreq;
    static int[] answer;
    static int maxFreq;
    static int mode;

    static void add(int index) {
        int val = arr[index];


        if (freq[val] != 0){
            countFreq[freq[val]]--;
        }
        freq[val]++;


        countFreq[freq[val]]++;


        if (freq[val] > maxFreq) {
            maxFreq = freq[val];
            mode = val;
        }
        else if (freq[val] == maxFreq && val < mode) {
            mode = val;
        }
    }

    static void remove(int index) {
        int val = arr[index];


        countFreq[freq[val]]--;
        if (freq[val] == maxFreq && countFreq[freq[val]] == 0) {
            maxFreq--;
        }


        freq[val]--;
        countFreq[freq[val]]++;

        if (freq[val] + 1 == maxFreq && mode == val) {

            for (int num = 0; num < freq.length; num++) {
                if (freq[num] == maxFreq) {
                    mode = num;
                    break;
                }
            }
        }
    }

    static int[] processQueries(int[] inputArr, Query[] queries) {
        arr = inputArr;
        int n = arr.length;
        int q = queries.length;
        BLOCK_SIZE = (int) Math.sqrt(n);
        freq = new int[500001];
        countFreq = new int[500001];
        answer = new int[q];
        maxFreq = 0;
        mode = -1;


        Arrays.sort(queries, (a, b) -> {
            int blockA = a.L / BLOCK_SIZE, blockB = b.L / BLOCK_SIZE;
            if (blockA == blockB) {
                return Integer.compare(a.R, b.R);
            }
            return Integer.compare(blockA, blockB);
        });

        int curL = 0, curR = -1;
        for (Query query : queries) {
            while (curR < query.R) add(++curR);
            while (curL > query.L) add(--curL);
            while (curR > query.R) remove(curR--);
            while (curL < query.L) remove(curL++);


            int segmentSize = query.R - query.L + 1;
            int threshold = segmentSize / 2;

            answer[query.index] = (maxFreq > threshold) ? mode : 0;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Query[] queries = new Query[Q];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            queries[i] = new Query(L, R, i);
        }

        int[] results = processQueries(arr, queries);
        for (int res : results) {
            bw.write(res + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
