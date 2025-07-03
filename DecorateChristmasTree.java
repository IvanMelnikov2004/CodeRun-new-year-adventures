import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DecorateChristmasTree {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = Integer.parseInt(reader.readLine().split(" ")[0]);
        writer.write(""+((1 << k) - 1));
        reader.close();
        writer.close();
    }

}
