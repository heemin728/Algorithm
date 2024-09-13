import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int direction = 3;
    static int bottom = 6;
    static int right = 3;
    static int up = 2;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[][] map;
    static int answer = 0;
    static int cx = 1;
    static int cy = 1;
    // north : 1, south : 2, west : 3, east : 4

    static boolean isInRange(int i, int j) {
        return i > 0 && j > 0 && i <= N && j <= M;
    }

    static void moveDice() {

        if (isInRange(cx + dx[direction], cy + dy[direction])) {
            cx += dx[direction];
            cy += dy[direction];
        } else {
            if (direction == 0 || direction == 2) {
                direction++;
            } else {
                direction--;
            }

            cx += dx[direction];
            cy += dy[direction];
        }

        if (direction == 0) {
            int tmp = up;
            up = 7 - bottom;
            bottom = tmp;
        } else if (direction == 1) {
            int tmp = 7 - up;
            up = bottom;
            bottom = tmp;
        } else if (direction == 2) {
            int tmp = 7 - right;
            right = bottom;
            bottom = tmp;
        } else if (direction == 3) {
            int tmp = right;
            right = 7 - bottom;
            bottom = tmp;
        }
    }
    
    static int getPoint() {
        int point = 0;

        int num = map[cx][cy];
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M + 1];

        queue.add(new int[] { cx, cy });
        point++;
        visited[cx][cy] = true;

        while (!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (!isInRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] != num) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[] { nx, ny });
                point++;

            }
        }
        return num*point;
    }
    
    static void updateDirection() {

        if (bottom == map[cx][cy]) {
            return;
        }

        if (bottom > map[cx][cy]) {
            switch (direction) {
                case 0:
                    direction = 3;
                    break;
                case 1:
                    direction = 2;
                    break;
                case 2:
                    direction = 0;
                    break;
                case 3:
                    direction = 1;
                    break;
            }
        }
        else if (bottom < map[cx][cy]) {
            switch (direction) {
                case 0:
                    direction = 2;
                    break;
                case 1:
                    direction = 3;
                    break;
                case 2:
                    direction = 1;
                    break;
                case 3:
                    direction = 0;
                    break;
            }
        }
    }
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            moveDice();
            answer += getPoint();
            updateDirection();
        }

        System.out.println(answer);
    }
}