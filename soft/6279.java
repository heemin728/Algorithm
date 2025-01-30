import java.io.*;
import java.util.*;

public class Main {

    static int N,K;
    static String s;

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        s=br.readLine();

        int component=0;
        int robot=0;
        int answer=0;
        while(component < N && robot < N){
            if(s.charAt(robot) != 'P'){
                robot++;
                continue;
            }

            if(s.charAt(component) != 'H'){
                component++;
                continue;
            }

            if(Math.abs(robot-component) <= K){
                answer++;
                robot++;
                component++;
            }
            else{
                if(robot > component){
                    component++;
                }
                else{
                    robot++;
                }
            }            
        }
        System.out.println(answer);
    }
}
