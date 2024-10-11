import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        int N=Integer.parseInt(br.readLine());
        ArrayList<Integer> list=new ArrayList<>();

        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        int[] dp=new int[N+1];
        int ans=1;
        // 3 7 5 2 6 1 4
        // 1 2 2 1 3 1 2
        for(int i=0;i<N;i++){
            dp[i]=1;

            for(int j=0;j<i;j++){
                if(list.get(i) > list.get(j)){
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                    ans=Math.max(ans,dp[i]);
                }
            }
        }

        System.out.println(N-ans);
    }    
}
