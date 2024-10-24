import java.util.*;
import java.io.*;

public class Main{
    static int N,M;
    static int[] answer;
    static ArrayList<Integer>[] list;

    static void dfs(int cnt){

        for(int i=0;i<list[cnt].size();i++){
            answer[list[cnt].get(i)]+=answer[cnt];
            dfs(list[cnt].get(i));
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        answer=new int[N+1];
        list=new ArrayList[N+1];

        for(int i=0;i<=N;i++){
            list[i]=new ArrayList<>();
        }


        st=new StringTokenizer(br.readLine());
        
        st.nextToken();
        for(int i=2;i<=N;i++){
            int num=Integer.parseInt(st.nextToken());

            list[num].add(i);
        }

        int i=0;
        int w=0;

        for(int j=0;j<M;j++){
            st=new StringTokenizer(br.readLine());
             
            i=Integer.parseInt(st.nextToken());
            w=Integer.parseInt(st.nextToken());

            answer[i]+=w;
        }

        dfs(1);

        for(int j=1;j<=N;j++){
            System.out.print(answer[j] + " ");
        }
        System.out.println();
    }
}
