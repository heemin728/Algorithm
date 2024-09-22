import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[][] A;
    static int[] dx={0,0,-1,-1,-1,0,1,1,1};
    static int[] dy={0,-1,-1,0,1,1,1,0,-1};
//                   1 2  3 4  5 6 7 8 9
    static ArrayList<int[]> cloud=new ArrayList<>();

    static boolean isInRange(int i,int j){
        return i>=1 && j>=1 && i<=N && j<=N;
    }

    static boolean isInRange2(int i){
        return i>=1 && i<=N;
    }

    static int[] moveCloud(int[] before, int direction, int amount){
        int a=before[0];
        int b=before[1];


        int x=0;
        int y=0;

        int move=amount%N;

        if(isInRange2(a+dx[direction]*move)){
            x=a+dx[direction]*move;
        }
        else{
            int tmp=a+dx[direction]*move;
            if(tmp>N){
                x=tmp-N;
            }
            else{
                x=tmp+N;
            }
        }

        if(isInRange2(b+dy[direction]*move)){
            y=b+dy[direction]*move;
        }
        else{
            int tmp=b+dy[direction]*move;
            if(tmp>N){
                y=tmp-N;
            }
            else{
                y=tmp+N;
            }
        }
        return new int[]{x,y};
    }

    static void waterBug(){
        for(int i=0;i<cloud.size();i++){
            int x=cloud.get(i)[0];
            int y=cloud.get(i)[1];

            int cnt=0;
            for(int k=2;k<=8;k+=2){
                int nx=x+dx[k];
                int ny=y+dy[k];

                if(!isInRange(nx, ny)){
                    continue;
                }

                if(A[nx][ny]>0){
                    cnt++;
                }
            }

            A[x][y]+=cnt;
        }
    }

    static void makeCloud(){
        boolean[][] possible=new boolean[N+1][N+1];

        for(int i=0;i<cloud.size();i++){
            int x=cloud.get(i)[0];
            int y=cloud.get(i)[1];

            possible[x][y]=true;
        }

        cloud.clear();

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(A[i][j] < 2 || possible[i][j]){
                    continue;
                }
                
                cloud.add(new int[]{i,j});
                A[i][j]-=2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        A=new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                A[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        cloud.add(new int[]{N-1,1});
        cloud.add(new int[]{N-1,2});
        cloud.add(new int[]{N,1});
        cloud.add(new int[]{N,2});

        // for(int i=0;i<cloud.size();i++){
        //     System.out.println(cloud.get(i)[0] + " - " + cloud.get(i)[1]);
        // }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            int d=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());

            for(int j=0;j<cloud.size();j++){
                int[] c=cloud.get(j);
                int[] newC=moveCloud(c,d,s);
                cloud.set(j,newC);

                A[newC[0]][newC[1]]++;
            }
            waterBug();
            makeCloud();
        }

        int answer=0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                answer+=A[i][j];
                //System.out.print(A[i][j] + " " );
            }
            //System.out.println();
        }

        System.out.println(answer);
    }
}
