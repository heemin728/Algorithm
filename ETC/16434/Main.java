import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long answer=0;
    static long Hatk=0;
    static long Hmax=0;
    static long Hcur=Hmax;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        Hatk=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int flag=Integer.parseInt(st.nextToken());
            long a=Long.parseLong(st.nextToken());
            long h=Long.parseLong(st.nextToken());

            if(flag==1){
                answer=Math.min(answer,Hcur-=(((h+Hatk-1)/Hatk-1)*a));
            }
            else if(flag==2){
                Hatk+=a;
                Hcur=Math.min(Hcur+h,Hmax);
            }
        }
        System.out.println(answer*(-1)+1);
    }
}