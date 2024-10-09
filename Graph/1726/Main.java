import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[][] board;
    static int[] start=new int[3];
    static int[] dest=new int[3];
    static int[] dx={0,0,0,1,-1};
    static int[] dy={0,1,-1,0,0};

    static boolean checkRange(int i, int j){
        return i>0 && j>0 && i<=N && j<=M;
    }

    static int bfs(){

        Queue<int[]> q=new ArrayDeque<>();
        boolean[][][] visited=new boolean[N+1][M+1][5];

        q.add(new int[]{start[0],start[1],start[2],0});
        visited[start[0]][start[1]][start[2]]=true;

        while(!q.isEmpty()){
            int x=q.peek()[0];
            int y=q.peek()[1];
            int d=q.peek()[2];
            int depth=q.peek()[3];
            q.poll();

            if(x==dest[0] && y==dest[1] && d==dest[2]){
                return depth;
            }

            if(d==1 || d==2){
                if(!visited[x][y][3]){
                    visited[x][y][3]=true;
                    q.add(new int[]{x,y,3,depth+1});
                }
                if(!visited[x][y][4]){
                    visited[x][y][4]=true;
                    q.add(new int[]{x,y,4,depth+1});
                }
            }
            else if(d==3 || d==4){
                if(!visited[x][y][1]){
                    visited[x][y][1]=true;
                    q.add(new int[]{x,y,1,depth+1});
                }
                if(!visited[x][y][2]){
                    visited[x][y][2]=true;
                    q.add(new int[]{x,y,2,depth+1});
                }
            }

            for(int a=1;a<=3;a++){
                int nx=x+dx[d]*a;
                int ny=y+dy[d]*a;

                if(!checkRange(nx, ny) || board[nx][ny]==1){
                    break;
                }
                if(visited[nx][ny][d]){
                    continue;
                }
                q.add(new int[]{nx,ny,d,depth+1});
                visited[nx][ny][d]=true;
            }

        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        board=new int[N+1][M+1];

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<3;i++){
            start[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<3;i++){
            dest[i]=Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs());
    }
}