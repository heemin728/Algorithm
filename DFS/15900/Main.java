import java.util.*;
import java.io.*;

public class Main {

    public static ArrayList<Integer>[] list;
    public static boolean[] visited;
    public static int answer=0;

    public static void dfs(int node,int count){

        for(int i=0;i<list[node].size();i++){
            int next=list[node].get(i);
            if(visited[next])continue;

            visited[next]=true;
            dfs(list[node].get(i),count+1);
            visited[next]=false;
        }

        if(node!=1 && list[node].size()==1){
            if(count%2==1){
                answer++;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        list=new ArrayList[N+1];
        visited=new boolean[N+1];

        for(int i=1;i<=N;i++){
            list[i]=new ArrayList<>();
            visited[i]=false;
        }


        for(int i=0;i<N-1;i++){
            st=new StringTokenizer(br.readLine());

            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }
        visited[1]=true;
        dfs(1,0);
        
        if(answer%2==1){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    } 
}
