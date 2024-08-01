import java.util.*;
import java.io.*;

class Field {
    int left;
    int right;
}

public class Main {

    public static int N;
    public static ArrayList<Integer>[] list;
    public static boolean[] visited;
    public static Field[] fields;

    public static int count = 1;

    public static void dfs(int node) {
        visited[node] = true;

        fields[node].left = count++;

        for (int i = 0; i < list[node].size(); i++) {
            int next = list[node].get(i);

            if (visited[next]) {
                continue;
            }

            visited[next] = true;

            dfs(next);
        }
        fields[node].right = count++;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        fields = new Field[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            fields[i] = new Field();
            visited[i] = false;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int next = Integer.parseInt(st.nextToken());
                if (next != -1) {
                    list[num].add(next);
                }

            }
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i]);
        }

        int root = Integer.parseInt(br.readLine());

        dfs(root);

        for (int i = 1; i <= N; i++) {
            System.out.println(i + " " + fields[i].left + " " + fields[i].right);
        }

    }
}
