import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};
    static int answer=Integer.MAX_VALUE;

    static boolean isInRange(int i, int j){
        return i>0 && j>0 && i<=N && j<=M;
    }

    static void bfs(){
        Queue<int[]> q=new ArrayDeque<>();

        q.add(new int[]{1,1,1});

        while(!q.isEmpty()){
            int i=q.peek()[0];
            int j=q.peek()[1];
            int cnt=q.peek()[2];
            q.poll();

            //System.out.println("("+i+", "+j+" ) ="+cnt);

            if(i==N && j==M){
                answer=Math.min(answer,cnt);
            }

            for(int k=0;k<4;k++){
                int ni=i+dx[k];
                int nj=j+dy[k];

                // System.out.println("ni="+ni);
                // System.out.println("nj="+nj);
                // if(isInRange(ni,))

                if(!isInRange(ni, nj) || visited[ni][nj]){
                    continue;
                }

                if(map[ni][nj]==0){
                    continue;
                }

                visited[ni][nj]=true;
                q.add(new int[]{ni,nj,cnt+1});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N+1][M+1];
        visited= new boolean[N+1][M+1];

        for(int i=1;i<=N;i++){
            String s=br.readLine();
            for(int j=1;j<=M;j++){
                map[i][j]=s.charAt(j-1)-'0';
            }
        }

        visited[1][1]=true;
        bfs();
        System.out.println(answer);
    }
}
