import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

     StringTokenizer st=new StringTokenizer(br.readLine());

     int n=Integer.parseInt(st.nextToken());
     int m=Integer.parseInt(st.nextToken());

     int[] arr=new int[n+1];
     int[] sum=new int[n+1];

     st=new StringTokenizer(br.readLine());
     for(int i=1;i<=n;i++){
        arr[i]=Integer.parseInt(st.nextToken());
     }

     for(int i=0;i<m;i++){
        st=new StringTokenizer(br.readLine());
        int a=Integer.parseInt(st.nextToken());
        int b=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        sum[a]+=k;
        sum[b+1]-=k;
     }

     for(int i=2;i<=n;i++){
        sum[i]+=sum[i-1];
     }

     for(int i=1;i<=n;i++){
        arr[i]+=sum[i];
        System.out.print(arr[i] + " ");
     }
    }
}