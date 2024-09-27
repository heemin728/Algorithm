import java.util.*;
import java.io.*;

public class Main{
    static int N,M;
    static int[][] map;
    static ArrayList<int[]> virus=new ArrayList<>();
    static int[] activate;
    static boolean[] visited;
    static int[][] smap;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};
    static boolean[][] visit;
    static int empty=0;
    static int answer=Integer.MAX_VALUE;
    static boolean find=false;

    static boolean checkRange(int i, int j){
        return i>=0 && i<N && j>=0 & j<N;
    }

    static void simulate(){
        smap=new int[N][N];
        visit=new boolean[N][N];
        int total=empty;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==1){
                    smap[i][j]=-3; // wall
                    continue;
                }
                if(map[i][j]==2){
                    smap[i][j]=-2; // deactivated virus
                    continue;
                }
                smap[i][j]=-1; // space
            }
        }

        Queue<int[]> q=new ArrayDeque<>();

        int time=0;

        for(int i=0;i<M;i++){
            int x=virus.get(activate[i])[0];
            int y=virus.get(activate[i])[1];
            q.add(new int[]{x,y,0});
        }

        if(total==0){
            answer=0;
            find=true;
            return;
        }

        while(!q.isEmpty()){
            int x=q.peek()[0];
            int y=q.peek()[1];
            int d=q.peek()[2];
            q.poll();


            for(int k=0;k<4;k++){
                int nx=x+dx[k];
                int ny=y+dy[k];

                if(!checkRange(nx, ny) || visit[nx][ny] || smap[nx][ny]==-3){
                    continue;
                }

                visit[nx][ny]=true;

                if(smap[nx][ny]==-1){ // empty
                    total--;
                }

                q.add(new int[]{nx,ny,d+1});
                if(smap[nx][ny] != -2){
                    smap[nx][ny]=d+1;
                }
            }
        }
        
        if(total==0){
            time=0;
            for(int i=0;i<N;i++){
                for(int j = 0; j < N; j++){
                    if(smap[i][j]>0){
                        time=Math.max(time,smap[i][j]);
                    }
                }
            }
            answer=Math.min(answer,time);
            find=true;
        }
    }

    static void makeCombination(int cnt, int index){
        if(cnt==M){
            simulate();
            return;
        }

        for(int i=index;i<virus.size();i++){
            if(visited[i]){
                continue;
            }
            visited[i]=true;
            activate[cnt]=i;
            makeCombination(cnt+1, i);
            visited[i]=false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        activate=new int[M];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());

                if(map[i][j]==2){
                    virus.add(new int[]{i,j});
                }
                if(map[i][j]==0){
                    empty++;
                }
            }
        }
        visited=new boolean[virus.size()];
        makeCombination(0,0);

        if(!find){
            System.out.println("-1");
        }
        else{
            System.out.println(answer);
        }
    }
}