import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] W;
    static boolean[] visited;
    static int answer=Integer.MAX_VALUE;

    static void dfs(int start, int cnt, int city, int sum){
        if(cnt==N && start==city){
            answer=Math.min(answer,sum);
            return;
        }

        for(int i=1;i<=N;i++){
            if(visited[i] || W[city][i] <=0){
                continue;
            }

            visited[i]=true;
            dfs(start,cnt+1,i,sum+W[city][i]);
            visited[i]=false;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader  br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        W=new int[N+1][N+1];
        visited=new boolean[N+1];

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                W[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=N;i++){
            dfs(i,0,i,0);
        }
    System.out.println(answer);
    }
}
