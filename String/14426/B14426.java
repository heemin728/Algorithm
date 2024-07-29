import java.util.*;
import java.io.*;

public class B14426 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int answer=0;
        Set<String> prefix=new HashSet<>();

        for(int i=0;i<N;i++){
            String input=br.readLine();

            for(int j=0;j<input.length();j++){
                prefix.add(input.substring(0, j+1));
                System.out.println(input.substring(0,j+1));
            }
        }

        for(int i=0;i<M;i++){
            String input=br.readLine();
            if(prefix.contains(input)) answer++;
        }

        System.out.println(answer);
    }
}