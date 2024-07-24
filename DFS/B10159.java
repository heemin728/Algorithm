import java.util.*;
import java.io.*;

public class B10159 {

    public static void dfs(int index, boolean[] visited, ArrayList<Integer>[] check) {
        for (int i = 0; i < check[index].size(); i++) {
            int next = check[index].get(i);

            if (visited[next]) {
                continue;
            }

            visited[next] = true;
            dfs(next, visited, check);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] in = new ArrayList[N + 1];
        ArrayList<Integer>[] out = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            in[i] = new ArrayList<>();
            out[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            in[a].add(b);
            out[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            int count = 0;

            boolean[] visited = new boolean[N + 1];

            visited[i] = true;

            dfs(i, visited, in);
            dfs(i, visited, out);

            for (int j = 1; j <= N; j++) {
                if (!visited[j]) {
                    count++;
                }
            }
            sb.append(count + "\n");
        }
        System.out.println(sb);
    }
}