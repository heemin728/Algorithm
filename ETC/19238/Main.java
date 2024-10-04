import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int fuel;
    static int[][] map;
    static int[] taxi=new int[2];
    static ArrayList<int[]> customers=new ArrayList<>();
    static int total=0;

    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};

    static boolean checkRange(int i, int j){
        return i>0 && j>0 && i<=N && j<=N;
    }

    static void fillMap(){
        for(int i=0;i<M;i++){
            int x=customers.get(i)[0];
            int y=customers.get(i)[1];

            map[x][y]=i+2;
        }
    }

    static int[] findNearestCustomers(){
        int num=0;
        int dist=-1;

        Queue<int[]> q=new ArrayDeque<>();
        boolean[][] visited=new boolean[N+1][N+1];

        q.add(new int[]{taxi[0], taxi[1],0});
        visited[taxi[0]][taxi[1]]=true;

        if(map[taxi[0]][taxi[1]]>1){
            return new int[]{map[taxi[0]][taxi[1]]-1,0};
        }

        int ri=-1;
        int rj=-1;

        while(!q.isEmpty()){
            int x=q.peek()[0];
            int y=q.peek()[1];
            int d=q.peek()[2];
            q.poll();

            for(int k=0;k<4;k++){
                int nx=x+dx[k];
                int ny=y+dy[k];

                if(!checkRange(nx, ny) || visited[nx][ny]){
                    continue;
                }

                if(map[nx][ny]==1){
                    continue;
                }

                if(map[nx][ny] > 1){
                    if(dist==-1){
                        dist=d+1;
                        num=map[nx][ny]-1;
                        ri=nx;
                        rj=ny;
                    }
                    else{
                        if(d+1 < dist){
                            dist=d+1;
                            num=map[nx][ny]-1;
                            ri=nx;
                            rj=ny;
                        }
                        else if(d+1==dist){
                            if(nx < ri){
                                num=map[nx][ny]-1;
                                ri=nx;
                                rj=ny;
                            }
                            else if(nx==ri){
                                if(ny < rj){
                                    num=map[nx][ny]-1;
                                    rj=ny;
                                }
                            }
                        }
                    }
                }

                visited[nx][ny]=true;
                q.add(new int[]{nx,ny,d+1});
            }            
        }

        if(ri==-1 && rj==-1){
            return new int[]{-1,-1};
        }
        
        return new int[]{num,dist};
    }

    static int moveTaxi(int num, int dist){

        fuel-=dist;
        boolean possible=false;

        int fromX=customers.get(num-1)[0];
        int fromY=customers.get(num-1)[1];
        taxi[0]=fromX;
        taxi[1]=fromY;
        map[fromX][fromY]=0;
        total--;

        if(fuel<0){
            return -1;
        }

        int ddist=0;

        int destX=customers.get(num-1)[2];
        int destY=customers.get(num-1)[3];

        Queue<int[]> q=new ArrayDeque<>();
        boolean[][] visited=new boolean[N+1][N+1];

        q.add(new int[]{fromX,fromY,0});
        visited[fromX][fromY]=true;

        while(!q.isEmpty()){
            int x=q.peek()[0];
            int y=q.peek()[1];
            int d=q.peek()[2];
            q.poll();

            if(x==destX && y==destY){
                ddist=d;
                possible=true;
                break;
            }

            for(int k=0;k<4;k++){
                int nx=x+dx[k];
                int ny=y+dy[k];

                if(!checkRange(nx, ny) || visited[nx][ny]){
                    continue;
                }

                if(map[nx][ny]==1){
                    continue;
                }

                visited[nx][ny]=true;
                q.add(new int[]{nx,ny,d+1});
            }
        }

        taxi[0]=destX;
        taxi[1]=destY;

        fuel-=ddist;

       if(!possible || fuel < 0){
            return -1;
       }

       fuel+=(ddist*2);
       return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        fuel=Integer.parseInt(st.nextToken());
        total=M;

        map=new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());
        taxi[0]=Integer.parseInt(st.nextToken());
        taxi[1]=Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());

            customers.add(new int[]{a,b,c,d});
        }

        fillMap();
        while(total>0){
            int[] res=findNearestCustomers();
            if(res[0]==-1 && res[1]==-1){
                System.out.println(-1);
                return;
            }
            int r=moveTaxi(res[0], res[1]);
            if(r<0){
                System.out.println("-1");
                return;
            }
        }
       System.out.println(fuel);
    }
}
