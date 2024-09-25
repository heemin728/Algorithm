import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[][] blocks;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};
    static int point=0;
    static int cx=0;
    static int cy=0;
    static int max_cnt=0;
    static boolean[][] visit2;

    static boolean inRange(int i, int j){
        return i>0 && i<=N && j>0 && j<=N;
    }

    static boolean findGroup(){
        Queue<int[]> queue=new ArrayDeque<>();
        boolean possible=false;
        visit2=new boolean[N+1][N+1];
        max_cnt=0;
        int max_rainbow=0;
        cx=0;
        cy=0;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(blocks[i][j]<1 || visit2[i][j]){
                    continue;
                }
                
                // c
                visit2[i][j]=true;

                int cnt=1;
                int rainbow=0;
                boolean[][] visited=new boolean[N+1][N+1];
                queue.add(new int[]{i,j});
                visited[i][j]=true;
            
                while(!queue.isEmpty()){
                    int[] cur=queue.poll();
                    int x=cur[0];
                    int y=cur[1];
                    
                    for(int k=0;k<4;k++){
                        int nx=x+dx[k];
                        int ny=y+dy[k];

                        if(!inRange(nx, ny) || visited[nx][ny]){
                            continue;
                        }

                        if(blocks[nx][ny]==-1){
                            continue;
                        }

                        if(blocks[nx][ny]!=0 && blocks[nx][ny]!=blocks[i][j]){
                            continue;
                        }

                        visited[nx][ny]=true;
                        queue.add(new int[]{nx,ny});

                        cnt++;
                        if(blocks[nx][ny]==0){
                            rainbow++;
                        }
                        else{
                            visit2[nx][ny]=true;
                        }

                    }
                }

                if(cnt >=2){
                    possible=true;
                }
                if(cnt > max_cnt){
                    max_cnt=cnt;
                    max_rainbow=rainbow;
                    cx=i;
                    cy=j;
                }
                else if(cnt== max_cnt){
                    if(rainbow >= max_rainbow ){
                        max_rainbow=rainbow;
                        cx=i;
                        cy=j;
                    }
                }

            }
        }
        if(!possible){
            return false;
        }
        else{
            return true;
        }

    }
    
    static void getPoint(){
        point+=max_cnt*max_cnt;
    }

    static void removeBlock(){

        Queue<int[]> queue=new ArrayDeque<>();
        boolean[][] visited=new boolean[N+1][N+1];
        queue.add(new int[]{cx,cy});
        visited[cx][cy]=true;
        int point=1;

        while(!queue.isEmpty()){
            int[] cur=queue.poll();
            int x=cur[0];
            int y=cur[1];

            for(int k=0;k<4;k++){
                int nx=x+dx[k];
                int ny=y+dy[k];

                if(!inRange(nx, ny) || visited[nx][ny]){
                    continue;
                }

                if(blocks[nx][ny]==-1){
                    continue;
                }

                if(blocks[nx][ny]!=0 && blocks[nx][ny]!=blocks[cx][cy]){
                    continue;
                }
                
                
                visited[nx][ny]=true;
                queue.add(new int[]{nx,ny});
                blocks[nx][ny]=-2;
                point++;
            }
        }

        blocks[cx][cy]=-2;
    }

    static void gravity(){
        for(int j=1;j<=N;j++){
            Queue<int[]> q=new ArrayDeque<>();

            for(int i=N;i>=1;i--){
                q.add(new int[] {blocks[i][j],i});
            }

            int i=N;
            while(!q.isEmpty()){
                int n=q.peek()[0];
                int x=q.peek()[1];
                q.poll();
                
                if(n==-2){
                    continue;
                }
                if(n==-1){
                    blocks[x][j]=-1;
                    i=x-1;
                    continue;
                }
                blocks[i][j]=n;
                if(i!=x){
                    blocks[x][j]=-2;
                }
                i--;
            }
            for(int x=i-1;x>=1;x--){
                blocks[x][j]=-2;
            }
        }
    }

    static void rotate(){
        int[][] tmp=new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                tmp[i][j]=blocks[i][j];
            }
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                blocks[i][j]=tmp[j][N-i+1];
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        blocks=new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                blocks[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int k=0;
        while(true){
            if(k>10){
             //   break;
            }
            k++;
            boolean res=findGroup();
            if(!res){
                break;
            }
            getPoint();
            removeBlock();
            gravity();
            rotate();
            gravity();
        }

        System.out.println(point);
    }
}
