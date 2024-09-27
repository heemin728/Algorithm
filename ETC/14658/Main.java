import java.util.*;
import java.io.*;

public class Main {
    static int N,M,L,K;
    static ArrayList<int[]> star=new ArrayList<>();
    static int[] s=new int[2];
    static boolean[] visited;
    static int answer=0;

    static void count(){
       int cnt=0;
       int a_x=star.get(s[0])[0];
       int a_y=star.get(s[0])[1];
       int b_x=star.get(s[1])[0];
       int b_y=star.get(s[1])[1];

       if(Math.abs(a_x-b_x) > L || Math.abs(a_y-b_y) > L){
            return;
       }

       int x=Math.min(a_x,b_x);
       int y=Math.min(a_y,b_y);

       int x2=x+L;
       int y2=y+L;

       for(int i=0;i<star.size();i++){
           int a=star.get(i)[0];
           int b=star.get(i)[1];

           if(a>=x && a<=x2 && b>=y && b<=y2){
            cnt++;
           }
       }
       answer=Math.max(answer,cnt);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            star.add(new int[]{x,y});
        }
        
        for(int i = 0; i < K; i++) {
            for(int j = i; j < K; j++) {  
                s[0]=i;
                s[1]=j;
                count();
            }
        }
        System.out.println(K-answer);
    }    
}
