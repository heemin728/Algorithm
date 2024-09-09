import java.io.*;
import java.util.*;

public class Main2{
    static class FireBall{
        int index;
        int r;
        int c;
        int m;
        int s;
        int d;

        public FireBall(int index, int r, int c, int m, int s, int d){
            this.index=index;
            this.r=r;
            this.c=c;
            this.m=m;
            this.s=s;
            this.d=d;
        }
    }

    static int N,M,K;
    static ArrayList<FireBall>[][] map;
    static ArrayList<FireBall> balls;
    static int[] dx={-1,-1,0,1,1,1,0,-1};
    static int[] dy={0,1,1,1,0,-1,-1,-1};
    static int index=0;

    static void move(){
        for(int i=0;i<balls.size();i++){
            FireBall f=balls.get(i);

            if(f.m<=0){
                continue;
            }

            // original cordinates
            int x=f.r;
            int y=f.c;

            map[x][y].remove(f);

            // new cordinates

            int nx=x+dx[f.d]*f.s;
            int ny=y+dy[f.d]*f.s;

            if(nx<=0){
                nx+=N;
            }
            if(ny<=0){
                ny+=N;
            }
            if(nx>N){
                nx-=N;
            }
            if(ny>N){
                ny-=N;
            }

            f.r=nx;
            f.c=ny;
            
          // System.out.println("x="+x+", y="+y +" => nx="+nx+", ny="+ny);
            map[nx][ny].add(f);
        }
    }

    static void divide(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(map[i][j].size() <= 1){
                    continue;
                }

                // should combine and divide;

                int mass=0;
                int velocity=0;
                int dir=0;

                boolean isEven=false;
                boolean isOdd=false;

                for(int t=0;t<map[i][j].size();t++){
                    FireBall f=map[i][j].get(t);
                    mass+=f.m;
                    velocity+=f.s;

                    if(f.d % 2 == 0){
                        isEven=true;
                    }
                    else{
                        isOdd=true;
                    }
                }

                if(isOdd && isEven){
                    dir=1;
                }
                mass=mass/5;
                velocity=velocity/map[i][j].size();

                // deactivate. 
                for(int t=0;t<map[i][j].size();t++){
                    int id=map[i][j].get(t).index;
                    balls.get(id).m=-1;
                }
                map[i][j].clear();

                if(mass==0){
                    continue;
                }

                // make new FireBalls
                for(int t=0;t<4;t++){
                    FireBall f=new FireBall(index++,i,j,mass,velocity,t*2+dir);
                    // System.out.println("index -> "+index);
                    balls.add(f);
                    map[i][j].add(f);
                }
                
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        map=new ArrayList[N+1][N+1];
        balls=new ArrayList<>();

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                map[i][j]=new ArrayList<>();
            }
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            int r=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());

            FireBall fb=new FireBall(index++, r, c, m, s, d);
            //System.out.println(".index -> "+index);
            map[r][c].add(fb);
            balls.add(fb);
        }

        // for(FireBall ball:balls){
        //     System.out.println("("+ball.r +", "+ball.c +" )");
        // }

        for(int i=0;i<K;i++){
            move();

            // System.out.println("-----moved----- \n");
            // for(int j=0;j<balls.size();j++){
            //     FireBall f=balls.get(j);
            //     System.out.println("("+f.r+", "+f.c+") -> m="+f.m+", s="+f.s+" , d="+f.d);
            // }
            // System.out.println("------------\n");
            divide();
            // System.out.println("-----devided----- \n");
            // for(int j=0;j<balls.size();j++){
            //     FireBall f=balls.get(j);
            //     System.out.println("("+f.r+", "+f.c+") -> m="+f.m+", s="+f.s+" , d="+f.d);
            // }
            // System.out.println("------------\n");
            // System.out.println();

        }

        int answer=0;
       // System.out.println(balls.size());
        for(int i=0;i<balls.size();i++){
            // System.out.println(i+" -> " + balls.get(i).m);
            if(balls.get(i).m<=0){
                continue;
            }
            answer+=balls.get(i).m;
        }
        System.out.println(answer);

        // for(FireBall ball:balls){
        //     System.out.println("("+ball.r +", "+ball.c +" )");
        // }

    }
}