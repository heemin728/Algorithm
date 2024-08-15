import java.util.*;
import java.io.*;

public class Main {

    public static int[] parent;
    public static int N;

    public static void init(){
        for(int i=1;i<=N;i++){
            parent[i]=i;
        }
    }

    public static void union(int x,int y){
        int nx=find(x);
        int ny=find(y);

        if(ny > nx){
            parent[ny]=nx;
        }
        else{
            parent[nx]=ny;
        }
    }

    public static int find(int x){
        if(x==parent[x]){
            return x;
        }

        return parent[x]=find(parent[x]);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        parent=new int[N+1];

        init();

        for(int i=0;i<N-2;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            union(x,y);
        }
        
        for(int i=1;i<N;i++){
            if(find(parent[i]) != find(parent[i+1])){
                System.out.println(i +" " +(i+1));
                break;
            }
        }
        System.out.println();
    }
    
}
