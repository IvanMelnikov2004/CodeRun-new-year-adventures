import java.io.*;

public class GameFromCloset {
    public static boolean checkMaps(int[] first, int[] second){
        for (int ch = 0; ch < 30; ch++){
            if (first[ch] > second[ch]) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String res = "NO";
        int[] mp = new int[30];
        int[] mp2 = new int[30];
        int k = Integer.parseInt(reader.readLine());
        String str = reader.readLine();
        String str2 = reader.readLine();
        for (char ch : str2.toCharArray()){
            mp2[ch - 'a'] += 1;
        }
        int i = 0, j = 0;
        while (j < str.length()){
            char ch = str.charAt(j);
            mp[ch - 'a'] += 1;
            while (j - i + 1 > k){
                mp[str.charAt(i) - 'a'] -= 1;
                i += 1;
            }
            if (j - i + 1 == k && checkMaps(mp, mp2)) res = "YES";

            j += 1;

        }

        writer.write(res);
        reader.close();
        writer.close();
    }
}
