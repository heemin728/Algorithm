import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MAX = 21;
    static int R, C;
    static int[][] map = new int[MAX][MAX];
    static int[] alphabet = new int[27]; // 'A'-64 이런 식으로 26까지 저장
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visit = new boolean[MAX][MAX];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dimensions = br.readLine().split(" ");
        R = Integer.parseInt(dimensions[0]);
        C = Integer.parseInt(dimensions[1]);

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                map[i][j] = c - 64; // 'A'에서 1을 빼서 숫자로 변환
            }
        }

        alphabet[map[0][0]] = 1;
        dfs(0, 0, 1);
        System.out.println(ans);
    }

    static boolean isInRange(int i, int j) {
        return i >= 0 && j >= 0 && i < R && j < C;
    }

    static void dfs(int i, int j, int count) {
        visit[i][j] = true;
        boolean keepGoing = false;

        for (int k = 0; k < 4; k++) {
            int newi = i + dx[k];
            int newj = j + dy[k];

            // 다음 칸이 이동 가능
            if (isInRange(newi, newj) && !visit[newi][newj]) {
                int nextWord = map[newi][newj];

                // 새로운 알파벳 !
                if (alphabet[nextWord] == 0) {
                    alphabet[nextWord]++;
                    dfs(newi, newj, count + 1);
                    keepGoing = true;
                    alphabet[nextWord]--;
                    visit[newi][newj] = false;
                }
            }
        }
        if (!keepGoing) {
            ans = Math.max(ans, count);
        }
    }
}
