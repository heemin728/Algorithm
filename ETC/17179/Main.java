import java.util.*;
import java.io.*;

public class Main {
    static int N,M,L;
    static ArrayList<Integer> S=new ArrayList<>();

    static boolean binary(int mid, int cutting){

        if((cutting+1)*mid > L){
            return false;
        }

        int index=-1;
        int cnt=0;

        for(int i=0;i<S.size();i++){
            int diff=0;
            if(index==-1){
                diff=S.get(i);
            }
            else{
                diff=S.get(i)-S.get(index);
            }

            if(diff >= mid && L-S.get(i) >=mid){
                cnt++;
                index=i;
            }
        }

        if(cnt >= cutting){
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++){
            int a=Integer.parseInt(br.readLine());
            S.add(a);
        }
        S.add(L);

        for(int i=0;i<N;i++){
            int cutting=Integer.parseInt(br.readLine());

           int start=0;
           int end=S.get(S.size()-1);
           int mid=0;
           int answer=0;
   
           while(start<=end){
               mid=(start+end)/2;
               boolean res=binary(mid,cutting);

               if(!res){
                end=mid-1;
               }
               else{
                start=mid+1;
                answer=mid;
               }
           }
           sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }    
}
