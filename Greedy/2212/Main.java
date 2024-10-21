import java.util.*;
import java.io.*;

public class Main {
    static int N,K;
    static ArrayList<Integer> sensors;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
    
        N=Integer.parseInt(br.readLine());
        K=Integer.parseInt(br.readLine());

        sensors=new ArrayList<>();
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            sensors.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(sensors);

        ArrayList<Integer> diff=new ArrayList<>();
        for(int i=0;i<N-1;i++){
            diff.add(sensors.get(i+1)-sensors.get(i));
        }

        Collections.sort(diff);

        int answer=0;
        for(int i=0;i<N-K;i++){
            answer+=diff.get(i);
        }

        System.out.println(answer);
    }
}
