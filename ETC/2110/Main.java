import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int C=Integer.parseInt(st.nextToken());

        ArrayList<Integer> list=new ArrayList<>();

        for(int i=0;i<N;i++){
            int a=Integer.parseInt(br.readLine());

            list.add(a);
        }

        Collections.sort(list);

        int answer=0;
        int start=1;
        int end=list.get(list.size()-1)-list.get(0);
        int mid=(start+end)/2;

        int flag=0;

        while(start <= end){

            if(flag>50){
                break;
            }

            int wifi=1;
            mid=(start+end)/2;            
            int before=0;

            for(int i=1;i<N;i++){
                if(list.get(i)-list.get(before) >= mid){
                    wifi++;
                    before=i;
                }
            }

            if(wifi>=C){
                start=mid+1;
                answer=mid;
            }
            else{
                end=mid-1;
            }

            flag++;
        }
        System.out.println(answer);
    }
}
