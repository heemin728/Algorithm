import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        int N=scanner.nextInt();
        int M=scanner.nextInt();

        ArrayList<Integer>[] list=new ArrayList[200001];
        int[] count=new int[N+1];

        for(int i=1;i<=200000;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<N;i++){
            int k=scanner.nextInt();
            for(int j=0;j<k;j++){
                int s=scanner.nextInt();
                list[s].add(i);
            }
        }

        for(int i=0;i<M;i++){
            int s=scanner.nextInt(); // 초밥
            if(list[s].size()>0){
                count[list[s].get(0)+1]++;
                list[s].remove(0);
            }
        }

        for(int i=1;i<=N;i++){
            System.out.print(count[i]+" ");
        }

       // System.out.println(Arrays.toString(count));
    }
}