import java.util.*;
import java.io.*;

public class Main {
    static int N,Q;
    static int n;
    static int[][] A;
    static ArrayList<Integer> Ls=new ArrayList<>();
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};

    static void turn(int w, int x, int y){
       w*=2;
       int[][] tmp=new int[w][w];

       for(int i=0;i<w;i++){
            for(int j=0;j<w;j++){
                tmp[i][j]= A[x+i][y+j];
            }
       }

       for(int i=0;i<w;i++){
            for(int j=0;j<w;j++){
                A[i+x][j+y]=tmp[w-1-j][i];
            }
       }
    }

    static boolean checkRange(int i, int j){
        return i>=0 && j>=0 && i<n && j<n;
    }

    static void fireStorm(int L){
        int w=(int)Math.pow(2,L);
        
        for(int i=0;i<n;i+=w){
            for(int j=0;j<n;j+=w){
                turn(w/2,i,j);
            }
        }

        Queue<int[]> q= new ArrayDeque<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int cnt=0;

                for(int k=0;k<4;k++){
                    int ni=i+dx[k];
                    int nj=j+dy[k];

                    if(checkRange(ni, nj) && A[ni][nj] > 0){
                        cnt++;
                    }
                }

                if(A[i][j] >0 && cnt < 3){
                    q.add(new int[]{i,j});
                }
            }
        }


        while(!q.isEmpty()){
            int x=q.peek()[0];
            int y=q.peek()[1];
            q.poll();

            A[x][y]--;
        }
    }

    static int[] findAnswer(){
        Queue<int[]> q=new ArrayDeque<>();
        boolean[][] visited=new boolean[n][n];

        int total=0;
        int area=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                total+=A[i][j];

                if(A[i][j]==0 || visited[i][j]){
                    continue;
                }

                q.add(new int[]{i,j});
                visited[i][j]=true;

                int cnt=1;

                while(!q.isEmpty()){
                    int x=q.peek()[0];
                    int y=q.peek()[1];
                    q.poll();

                    for(int k=0;k<4;k++){
                        int nx=x+dx[k];
                        int ny=y+dy[k];

                        if(!checkRange(nx, ny) || visited[nx][ny]){
                            continue;
                        }

                        if(A[nx][ny]!=0){
                            visited[nx][ny]=true;
                            q.add(new int[]{nx,ny});
                            cnt++;
                        }
                    }
                }
                area=Math.max(area,cnt);
            }
        }
        return new int[]{total,area};
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        Q=Integer.parseInt(st.nextToken());

        n=(int)Math.pow(2,N);
        A=new int[n][n];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                A[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<Q;i++){
            int L=Integer.parseInt(st.nextToken());
            fireStorm(L);
        }
        int[] answer=findAnswer();
        System.out.println(answer[0]);
        System.out.println(answer[1]);       
    }
}
