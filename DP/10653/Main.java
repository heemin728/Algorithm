import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<int[]> list=new ArrayList<>();

    static int distance(int i, int j){
        int x=list.get(i)[0];
        int y=list.get(i)[1];
        int x2=list.get(j)[0];
        int y2=list.get(j)[1];

        return Math.abs(x-x2)+Math.abs(y-y2);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N,K;

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            list.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }

        int[][] dp=new int[N][K+1];

        for(int i=0;i<N;i++){
            for(int j=0;j<=K;j++){
                dp[i][j]=998765432;
            }
        }

        dp[0][0]=0;
        for(int i=1;i<N;i++){

            dp[i][0]=dp[i-1][0]+distance(i-1,i);

            for(int j=1;j<=K;j++){
                if(j>=i){
                    break;
                }
            
                for(int k=0;k<=j;k++){
                    if(i-k-1 ==j-k && j-k!=0){
                        continue;
                    }
                    dp[i][j]=Math.min(dp[i][j],dp[i-k-1][j-k] + distance(i-k-1,i));
                }
            }
        }

        int answer=Integer.MAX_VALUE;

        for(int i=0;i<=K;i++){
            answer=Math.min(answer,dp[N-1][i]);
        }
        System.out.println(answer);
    }
}