import java.io.*;
import java.util.*;

public class Main {
    static int N,M,L;
    static ArrayList<Integer> list=new ArrayList<>();

    static boolean solution(int value){
        boolean ret=false;

        int cnt=0;

        for(int i=1;i<list.size();i++){
            int d=list.get(i)-list.get(i-1);

            if(d / value > 0){
                cnt+=d/value;
            }
            if(d%value==0){
                cnt--;
            }
        }

        if(cnt <= M){
            ret=true;
        }
 
        return ret;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.add(0);
        list.add(L);
        Collections.sort(list);

        int start=1;
        int mid=0;
        int end=L;
        
        int answer=0;

        while(start <= end){
            mid=(start+end)/2;

            boolean result=solution(mid);
            if(result){
                answer=mid;
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }

        System.out.println(answer);
    }    
}
