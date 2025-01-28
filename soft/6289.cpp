import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static ArrayList<Integer> Ws=new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        boolean[] best=new boolean[N];
        for(int i=0;i<N;i++){
            best[i]=true;
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int num=Integer.parseInt(st.nextToken());
            Ws.add(num);
        }
        for(int j=0;j<M;j++){
            st=new StringTokenizer(br.readLine());

            int a=Integer.parseInt(st.nextToken())-1;
            int b=Integer.parseInt(st.nextToken())-1;

            int numA=Ws.get(a);
            int numB=Ws.get(b);

            if(numA > numB){
                best[b]=false;
            }
            else if(numA < numB){
                best[a]=false;
            }
            else{
                best[a]=false;
                best[b]=false;
            }
        }

        int answer=0;
        for(int i=0;i<N;i++){
            if(best[i]){
                answer++;
            }
        }
        System.out.println(answer);
    }
}
