import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        arr=new int[N][M];
        dp=new int[N][M];

        int ans=0;

        for(int i=0;i<N;i++){
            String s=br.readLine();

            for(int j=0;j<M;j++){
                arr[i][j]=s.charAt(j)-'0';

                if(i==0 || j==0){
                    dp[i][j]=arr[i][j];
                    ans=Math.max(ans,dp[i][j]);
                }
            }
        }

        for(int i=1;i<N;i++){
            for(int j=1;j<M;j++){
                if(arr[i][j]==0){
                    dp[i][j]=0;
                    continue;
                }
                
                if(arr[i-1][j-1]==0 || dp[i-1][j]==0 || dp[i][j-1]==0){
                    dp[i][j]=1;
                    continue;
                }

                int m=Math.min(dp[i-1][j], dp[i][j-1]);
                m=Math.min(m,dp[i-1][j-1]);
                dp[i][j]=m+1;

                ans=Math.max(dp[i][j],ans);
            }
        }
        System.out.println(ans*ans);
    }
}
