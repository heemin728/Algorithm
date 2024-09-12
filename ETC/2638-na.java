import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 101;
    static int n, m;
    static int[][] map = new int[MAX][MAX];
    static boolean[][] visit = new boolean[MAX][MAX];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static boolean isInRange(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    static void separateInnerPlace() {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != 0 && j != 0 && i != n - 1 && j != m - 1) continue;
                if (map[i][j] != 0) continue;

                q.offer(new int[]{i, j});
                visit[i][j] = true;
                map[i][j] = 2;

                while (!q.isEmpty()) {
                    int a = q.peek()[0];
                    int b = q.peek()[1];
                    q.poll();

                    for (int k = 0; k < 4; k++) {
                        int newa = a + dx[k];
                        int newb = b + dy[k];

                        if (isInRange(newa, newb) && !visit[newa][newb]) {
                            visit[newa][newb] = true;

                            if (map[newa][newb] == 0) {
                                map[newa][newb] = 2;
                                q.offer(new int[]{newa, newb});
                            }
                        }
                    }
                }
            }
        }
    }

    static int countNear(int i, int j) {
        int ret = 0;

        if (i >= 1 && map[i - 1][j] == 2) ret++;
        if (i <= n - 2 && map[i + 1][j] == 2) ret++;
        if (j >= 1 && map[i][j - 1] == 2) ret++;
        if (j <= m - 2 && map[i][j + 1] == 2) ret++;

        return ret;
    }

    static void zeroFindsTwo(int a, int b) {
        Queue<int[]> zero = new LinkedList<>();
        zero.offer(new int[]{a, b});
        map[a][b] = 2;
        boolean[][] visitZero = new boolean[MAX][MAX];
        visitZero[a][b] = true;

        while (!zero.isEmpty()) {
            int i = zero.peek()[0];
            int j = zero.peek()[1];
            zero.poll();

            for (int k = 0; k < 4; k++) {
                int newi = i + dx[k];
                int newj = j + dy[k];

                if (isInRange(newi, newj) && !visitZero[newi][newj]) {
                    visitZero[newi][newj] = true;
                    if (map[newi][newj] == 0) {
                        map[newi][newj] = 2;
                        zero.offer(new int[]{newi, newj});
                    }
                }
            }
        }
    }

    static boolean bfs() {
        boolean change = false;
        Queue<int[]> store = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 1) continue;
                if (countNear(i, j) >= 2) {
                    change = true;
                    store.offer(new int[]{i, j});
                }
            }
        }

        while (!store.isEmpty()) {
            int a = store.peek()[0];
            int b = store.peek()[1];
            store.poll();
            map[a][b] = 2;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    if (countNear(i, j) > 0) 
                        zeroFindsTwo(i, j);
                }
            }
        }
        return change;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        separateInnerPlace();
        int cnt = 0;
        while (true) {
            boolean out = bfs();
            if (!out) break;
            cnt++;
        }
        sb.append(cnt).append("\n");
        System.out.print(sb);
    }
}
