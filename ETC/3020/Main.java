import java.io.*;
import java.util.*;

public class Main {
    static int N,H;
    static ArrayList<Integer> up=new ArrayList<>();
    static ArrayList<Integer> down=new ArrayList<>();

    static int binarySearch(double find){
        int ret=-1;

        int start=0;
        int end=up.size()-1;
        int mid=0;

        while(start <= end){

            mid=(start+end)/2;

            if(up.get(mid) < find){
                start=mid+1;
            }
            else if(up.get(mid) > find){
                ret=mid;
                end=mid-1;
            }
        }

        if(ret==-1){
            return 0;
        }
        return up.size()-ret;   
    }

    static int binarySearch2(double find){
        int ret=-1;

        int start=0;
        int end=down.size()-1;
        int mid=0;

        while(start <= end){

            mid=(start+end)/2;

            //
            if(H-down.get(mid) < find){
                end=mid-1;
                ret=mid;
            }
            else if(H-down.get(mid) > find){ // 
                start=mid+1;
            }
        }

        if(ret==-1){
            return 0;
        }
        return down.size()-ret;   
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            if(i%2==0){
                up.add(Integer.parseInt(br.readLine()));
            }
            else{
                down.add(Integer.parseInt(br.readLine()));
            }
        }

        Collections.sort(up);
        Collections.sort(down);

        int m=Integer.MAX_VALUE;
        int cnt=0;

        for(double h=0.5; h<H; h+=1){
            int b1=binarySearch(h);
            int b2=binarySearch2(h);
            
            if(b1+b2==m){
                cnt++;
                continue;
            }
            if(b1+b2 < m){
                cnt=1;
                m=b1+b2;
            }
        }

        System.out.println(m + " "+ cnt);
    }
}
