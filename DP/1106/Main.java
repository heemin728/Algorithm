import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();

        int C=Integer.parseInt(st.nextToken());
        int N=Integer.parseInt(st.nextToken());

        int[] dp=new int[1000001];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int cost=Integer.parseInt(st.nextToken());
            int customer=Integer.parseInt(st.nextToken());

            for(int j=cost;j<1000001;j++){
                dp[j]=Math.max(dp[j],dp[j-cost]+customer);
            }
        }

        for(int i=1;i<=1000001;i++){
            if(dp[i]>=C){
                System.out.println(i);
                break;
            }
        }
        
    }
}