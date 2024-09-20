import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int dead=0;
    static int answer=0;

    static ArrayList<Egg> list=new ArrayList<>();
    static boolean[] broken;

    static class Egg{
        int s;
        int w;

        public Egg(int s, int w){
            this.s=s;
            this.w=w;
        }
    }

    static void solution(int hand, int index, int eggs){
        if(hand>=N){
            answer=Math.max(answer,eggs);
            return;
        }

        if(broken[hand]){
            solution(hand+1,0,eggs);
        }
        else{
            int hand_s=list.get(hand).s;
            int hand_w=list.get(hand).w;

            boolean notBrokenEgg=false;

            for(int i=index;i<N;i++){
                if(hand==i || broken[i]){
                    continue;
                }
                notBrokenEgg=true;

                int target_s=list.get(i).s;
                int target_w=list.get(i).w;

                int new_hand_s=hand_s-target_w;
                int new_target_s=target_s-hand_w;

                int new_eggs=eggs;

                if(new_hand_s <= 0){
                    broken[hand]=true;
                    new_eggs++;

                    list.get(hand).s=new_hand_s;

                    if(new_target_s<=0){
                        broken[i]=true;
                        new_eggs++;
                    }

                    list.get(i).s=new_target_s;
                    
                    solution(hand+1,0,new_eggs);

                    list.get(hand).s=hand_s;
                    list.get(i).s=target_s;
                    broken[hand]=false;
                    if(new_target_s<=0){
                        broken[i]=false;
                    }
                }
                else{

                    list.get(hand).s=new_hand_s;
                    list.get(i).s=new_target_s;

                    if(new_target_s<=0){
                        broken[i]=true;
                        solution(hand+1,0,eggs+1);
                    }
                    else{
                        solution(hand+1,0,eggs);
                    }

                    if(new_target_s<=0){
                        broken[i]=false;
                    }

                    list.get(hand).s=hand_s;
                    list.get(i).s=target_s;
                }
            }

            if(!notBrokenEgg){
                solution(hand+1, 0, eggs);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        broken=new boolean[N+1];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            int s=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            list.add(new Egg(s,w));
        }
        
        solution(0,0,0);
        System.out.println(answer);
    }
}
