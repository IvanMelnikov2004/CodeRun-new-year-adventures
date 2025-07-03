import java.io.*;
import java.util.*;

public class ToCityForFair {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[n];
        //Представим граф в виде списка смежности
        for (int i = 0; i < m; i++){
            line = reader.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            graph.get(from - 1).add(to - 1);
            graph.get(to - 1).add(from - 1);
        }

        int optimal = 0;
        //Перебираем все ноды
        for (int i = 0; i < n; i++){
            List<Integer> stack = new ArrayList<>();
            int currentNumbersOfNodes = 0;
            stack.add(i);
            //итеративный обход от текущей ноды
            while (!stack.isEmpty()){

                int curNode = stack.get(stack.size() - 1);
                if (!visited[curNode]){
                    //Считаем кол-во нод в компоненте
                    currentNumbersOfNodes += 1;
                    //помечаем посещенные ноды, чтобы не попадать в них при следующем переборе и текущем обходе
                    visited[curNode] = true;
                }
                stack.remove(stack.size() - 1);
                if (!graph.get(curNode).isEmpty()){
                    //переход к следующей вершине
                    for (int nextNode : graph.get(curNode)){

                        if (!visited[nextNode]){
                            stack.add(nextNode);
                            currentNumbersOfNodes += 1;
                            visited[nextNode] = true;
                        }
                    }
                }

            }
            optimal += currentNumbersOfNodes != 0 ? currentNumbersOfNodes - 1 : 0;
        }
        writer.write(m - optimal + "");
        reader.close();
        writer.close();

    }
}