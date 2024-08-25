import java.io.IOException;
import java.util.*;

public class Main {

    static int N,M;
    static ArrayList<Integer>list[];
    static boolean visited[];
    static int answer=0;

    static void dfs(int cnt){
        for(int i=0;i<list[cnt].size();i++){
            int next=list[cnt].get(i);

            if(visited[next]){
                continue;
            }

            answer++;
            visited[next]=true;
            dfs(next);          
        }
    }
    public static void main(String[] args) throws IOException{
        Scanner scanner =new Scanner(System.in);
        N=scanner.nextInt();
        M=scanner.nextInt();

        list=new ArrayList[N+1];
        visited=new boolean[N+1];

        for(int i=0;i<=N;i++){
            list[i]=new ArrayList<>();
        }
        
        for(int i=0;i<M;i++){
            int a=scanner.nextInt();
            int b=scanner.nextInt();

            list[a].add(b);
            list[b].add(a);

        }
        visited[1]=true;
        dfs(1);
        System.out.println(answer);

    }
}
