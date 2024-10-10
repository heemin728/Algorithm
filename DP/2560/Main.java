import java.util.*;
import java.io.*;

public class Main {
    static int a, b,d,N;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        a=Integer.parseInt(st.nextToken());
        b=Integer.parseInt(st.nextToken());
        d=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());

        dp=new long[N+d+1];
        
        for(int i=0;i<a;i++){
            dp[i]=1;
        }

        for(int i=a;i<=N;i++){
            dp[i]=(dp[i-1]+dp[i-a])%1000;
            if(i-b>=0){
                dp[i]=(dp[i]-dp[i-b]+1000)%1000;
            }
        }

        if(N-d>=0){
            dp[N]=(dp[N]-dp[N-d]+1000)%1000;
        }
        System.out.println(dp[N]);
    }
}
