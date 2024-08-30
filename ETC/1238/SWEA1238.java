import java.io.*;
import java.util.*;

public class SWEA1238 {
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int answer = 0;

    static void bfs(int start) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { start, 0 });
        int maximum = 0;
        int before = 0;
        
        while (!q.isEmpty()) {
            int from = q.peek()[0];
            int depth = q.peek()[1];
            q.poll();

            if (depth != before) {
                before = depth;
                maximum = 0;
            }

            for (int i = 0; i < list[from].size(); i++) {
                int to = list[from].get(i);

                if (visited[to]) {
                    continue;
                }
                maximum = Math.max(maximum, to);
                answer = maximum;
                visited[to] = true;
                q.add(new int[] { to, depth + 1 });
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());

            int len = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            list = new ArrayList[101];
            visited = new boolean[101];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= 100; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < len; i += 2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                list[from].add(to);
            }
            visited[start] = true;
            bfs(start);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}