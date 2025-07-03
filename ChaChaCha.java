import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class ChaChaCha {
    public static char solve(String str){
        Map<Character, Integer> translateFrom = new HashMap<>();
        Map<Integer, Character> translateTo = new HashMap<>();
        char x = 'A';
        int z = 28;
        int n = 0;
        float s = 0;
        char bad = 'A';

        // Заполнение словарей отображения
        while (x <= 'Z'){
            translateFrom.put(x, z);
            translateTo.put(z--, x++);
        }

        //Ищем худшую оценку и сумму численных представлений оценок
        for (char cur : str.toCharArray()){
            bad = (char) Math.max(bad, cur);
            s += translateFrom.get(cur);
            n += 1;
        }

        //Вычисление средней оценки без учета худшей
        char mid = translateTo.get(Math.round(s / n));

        //Вычисление результата
        return (char)(Math.max(bad - 1, mid));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = reader.readLine();
        writer.write(solve(str));
        reader.close();
        writer.close();
    }

}
