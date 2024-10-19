import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static ArrayList<Integer> men = new ArrayList<>();
    static ArrayList<Integer> women = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            men.add(Integer.parseInt(st.nextToken()));
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            women.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(men);
        Collections.sort(women);

        int p1=0;
        int p2=women.size()-1;

        int cnt=0; 

        while(true){
            if(p1 >= men.size() || p2 < 0){
                break;
            }

            if(men.get(p1) < 0 ){
                if(women.get(p2) < 0){
                    p1++;
                    continue;
                }
                if(women.get(p2) > 0 && (-1)*men.get(p1) > women.get(p2)){
                    cnt++;
                    p1++;
                    p2--;
                }
                else{
                    p2--;
                }
            }
            else{
                if(women.get(p2) < 0 && men.get(p1) < (-1)*women.get(p2)){
                    cnt++;
                    p1++;
                    p2--;
                }
                else{
                    p2--;
                }
            }
        }
        System.out.println(cnt);
    }    
}