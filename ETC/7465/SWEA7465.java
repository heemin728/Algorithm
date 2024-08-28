import java.io.*;
import java.util.*;

public class SWEA7465 {

    static int N, M;
    static int[] parent;

    static void init() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    static void union(int x, int y) {
        int rX = find(x);
        int rY = find(y);

        if (rX < rY) {
            parent[rY] = rX;
        }
        else {
            parent[rX] = rY;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            Set<Integer> s = new HashSet<>();

            parent = new int[N + 1];
            init();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                union(x, y);    
            }

            for (int i = 1; i <= N; i++) {
                s.add(find(i));
            }
            sb.append("#").append(t).append(" ").append(s.size()).append("\n");
        }
        System.out.println(sb);
    }
}
