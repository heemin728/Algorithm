import java.util.*;
import java.io.*;

public class Main {
    static int[] fruits = new int[1002];
    static int[][][] dp = new int[1002][32][3];
    static int answer=0;
    static int T,W;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        T=Integer.parseInt(st.nextToken());
        W=Integer.parseInt(st.nextToken());

        for (int i = 1; i <= T; ++i) {
            fruits[i] = Integer.parseInt(br.readLine());
        }

        //
        for (int i = 1; i <= T; ++i) {

            for(int j=0;j<=W;j++){

                if(i<j){
                    break;
                }
                if(j==0){
                    if(fruits[i]==1){
                        dp[i][j][1]=dp[i-1][j][1]+1;
                    }
                    else{
                        dp[i][j][1]=dp[i-1][j][1];
                    }
                    continue;
                }

                if(fruits[i]==1){
                    dp[i][j][1]=Math.max(dp[i-1][j-1][2],dp[i-1][j][1])+1;
                    dp[i][j][2]=Math.max(dp[i-1][j-1][1], dp[i-1][j][2]);
                }
                else{
                    dp[i][j][1]=Math.max(dp[i-1][j-1][2],dp[i-1][j][1]);
                    dp[i][j][2]=Math.max(dp[i-1][j-1][1], dp[i-1][j][2])+1;
                }
                answer=Math.max(answer,dp[i][j][1]);
                answer=Math.max(answer,dp[i][j][2]);  
            }
        }
        System.out.println(answer);
    }
}
