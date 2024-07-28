import java.io.*;
import java.util.*;


public class B17404 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int arr[][];
    static int dp[][];
    static int answer = 1000000;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][3];
        dp = new int[n + 1][3];

        for(int i = 1 ; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int j= 0; j < 3; j++) {
            for(int i = 0 ; i < 3; i++) {
                if(i == j) dp[1][i] = arr[1][i];
                else dp[1][i] = 1000000;
            }

            for (int i = 2; i <= n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
            }

            for(int i = 0 ; i < 3; i++)
                if(i != j) answer = Math.min(answer, dp[n][i]);
        }
        System.out.println(answer);
    }
}