import java.util.*;

public class Main {
    static final int MAX = 10001;
    static final int INF = 98765432;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); 
        int K = scanner.nextInt(); 

        int[] coins = new int[N + 1];
        int[] dp = new int[MAX];

        for (int i = 1; i <= N; i++) {
            coins[i] = scanner.nextInt(); 
        }

        
        Arrays.fill(dp, INF);
        dp[0] = 0; 

        for (int i = 1; i <= N; i++) {
            for (int j = coins[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        if (dp[K] == INF) {
            System.out.println("-1");
        } else {
            System.out.println(dp[K]);
        }

        scanner.close();
    }
}