import java.io.*;
import java.util.*;

public class SWEA3289 {
    static int N;
    static int M;
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
        int rx = find(x);
        int ry = find(y);

        if (rx < ry) {
            parent[ry] = rx;
        }
        else {
            parent[rx] = ry;
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

            sb.append("#").append(t).append(" ");

            parent = new int[N + 1];
            init();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int num = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (num == 0) {
                    union(a, b);
                } else {
                    sb.append((find(a) == find(b))==true ? "1" : "0");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
