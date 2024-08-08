import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 21;
    static int[][] arr = new int[MAX][MAX];
    static List<Integer>[] likes = new ArrayList[MAX * MAX];
    static int N;
    static int emptySpace;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static boolean isInRange(int i, int j) {
        return i > 0 && j > 0 && i <= N && j <= N;
    }

    static List<int[]> countMaxLikes(int student, List<Integer> like) {
        List<int[]> ret = new ArrayList<>();
        int tMax = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] != 0) {
                    continue;
                }

                int cnt = 0;
                int tEmpty = 0;

                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if (!isInRange(ni, nj)) continue;

                    if (arr[ni][nj] == 0) tEmpty++;

                    if (like.contains(arr[ni][nj])) {
                        cnt++;
                    }
                }

                if (cnt > tMax) {
                    ret.clear();
                    ret.add(new int[]{i, j});
                    emptySpace = tEmpty;
                    tMax = cnt;
                } else if (cnt == tMax) {
                    emptySpace = Math.max(emptySpace, tEmpty);
                    ret.add(new int[]{i, j});
                }
            }
        }
        return ret;
    }

    static void countMaxSpaces(int student, List<int[]> res) {
        for (int[] coordinate : res) {
            int x = coordinate[0];
            int y = coordinate[1];
            int cnt = 0;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (!isInRange(nx, ny)) continue;

                if (arr[nx][ny] == 0) cnt++;
            }

            if (emptySpace == cnt) {
                arr[x][y] = student;
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int students = N * N;

        for (int i = 1; i <= students; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            likes[student] = new ArrayList<>();

            while (st.hasMoreTokens()) {
                int l = Integer.parseInt(st.nextToken());
                likes[student].add(l);
            }

            List<int[]> res = countMaxLikes(student, likes[student]);
            countMaxSpaces(student, res);
            emptySpace = 0; 
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int s = arr[i][j];
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if (!isInRange(ni, nj)) continue;

                    if (likes[s].contains(arr[ni][nj])) {
                        cnt++;
                    }
                }

                answer += Math.pow(10, cnt - 1);
            }
        }
        System.out.println(answer);
    }
}
