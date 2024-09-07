import java.io.*;
import java.util.*;

class FireBall {
    int m, s, d;

    FireBall(int m, int s, int d) {
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

public class Main {
    static int N, M, K;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<FireBall>[][] balls;

    static void moveBall() {
        List<FireBall>[][] tmpBall = new ArrayList[N+1][N+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmpBall[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (FireBall b : balls[i][j]) {
                    int s = b.s % N;
                    int d = b.d;

                    int nx = i + dx[d] * s;
                    int ny = j + dy[d] * s;

                    if (nx < 1) nx += N;
                    if (nx > N) nx -= N;
                    if (ny < 1) ny += N;
                    if (ny > N) ny -= N;

                    tmpBall[nx][ny].add(b);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                balls[i][j].clear();
                balls[i][j].addAll(tmpBall[i][j]);
            }
        }
    }

    static void divideBall() {
        List<FireBall>[][] tmpBall = new ArrayList[N+1][N+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmpBall[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (balls[i][j].size() == 1) {
                    tmpBall[i][j].addAll(balls[i][j]);
                } else if (balls[i][j].size() >= 2) {
                    int totalS = 0;
                    int totalM = 0;
                    boolean even = false;
                    boolean odd = false;

                    for (FireBall b : balls[i][j]) {
                        int dir = b.d;
                        if (dir % 2 == 0) even = true;
                        else odd = true;

                        totalS += b.s;
                        totalM += b.m;
                    }

                    int m = totalM / 5;
                    int s = totalS / balls[i][j].size();
                    int dir = (even && odd) ? 1 : 0;

                    if (m > 0) {
                        for (int d = dir; d < 8; d += 2) {
                            tmpBall[i][j].add(new FireBall(m, s, d));
                        }
                    }
                }
                balls[i][j].clear();
                balls[i][j].addAll(tmpBall[i][j]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        balls = new ArrayList[N+1][N+1];

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                balls[i][j]=new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            balls[x][y].add(new FireBall(m, s, d));
        }

        for (int i = 0; i < K; i++) {
            moveBall();
            divideBall();
        }

        long answer = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (FireBall b : balls[i][j]) {
                    answer += b.m;
                }
            }
        }
        System.out.println(answer);
    }
}