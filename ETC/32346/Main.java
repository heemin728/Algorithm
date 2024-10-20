import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static final int DIVIDE = 1000000007;

    public static long combination(int n, int k) {
        if (k > n) return 0;
        if (k == 0 || k == n) return 1;
        if (k > n / 2) k = n - k; // 

        long result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());

        String s=br.readLine();
        
        Stack<Character> stack=new Stack<>();

        ArrayList<int[]> list=new ArrayList<>();

        //int start=0;
        int cnt=0;
        boolean valid=false;

        for(int i=0;i<s.length();i++){

            char c=s.charAt(i);

            if(stack.isEmpty()){
                //if(c=='>'){
                    stack.push(c);
                    if(valid){
                        valid=false;
                        //list.add(cnt,);
                        list.add(new int[]{i-2*cnt,N-(i-2*cnt)-cnt*2,cnt});
                        System.out.println("1. start = " + (i-2*cnt) + ", end = " + (N-(i-2*cnt)-cnt*2) +", cnt =" + cnt);
                       // System.out.println(cnt + " " + (i-2*cnt));
                        cnt=0;
                    }
                //}
                continue;
            }

            if(c=='>'){
                stack.push(c);
                if(valid){
                    valid=false;
                    //list.add(cnt,);
                   // System.out.println("i="+i);
                    // N=
                    list.add(new int[]{i-2*cnt,N-(i-2*cnt)-cnt*2,cnt});
                    System.out.println("N="+N+", - ("+(i-2*cnt)+") - ("+ cnt*2 +") = " + (N-(i-2*cnt)-cnt*2));
                    System.out.println("i="+i + " 2. start = " + (i-2*cnt) + ", end = " + (N-(i-2*cnt)-cnt*2) +", cnt =" + cnt);
                    cnt=0;
                }
            }
            else{
                if(stack.peek() == '>' ){
                    valid=true;
                    cnt++;
                    //start=i-1;
                    stack.pop();
                }
                else{
                    if(valid){
                        valid=false;
                        list.add(new int[]{i-cnt*2,N-(i-cnt*2)-cnt*2,cnt});
                        //System.out.println("N="+N +", i="+i+", cnt ="+cnt);
                        /**
                         */
                        System.out.println("3. start = " + (i-cnt*2) + ", end = " + (N-(i-cnt*2)-cnt*2) +", cnt =" + cnt);
                        //System.out.println(cnt + " " + (i+1));
                        cnt=0;
                    }
                }
            }
        }

        if(valid){
            list.add(new int[]{N-2*cnt,N-(N-2*cnt)-cnt*2,cnt});
            System.out.println("4. start = " + (N-2*cnt) + ", end = " + (N-(N-2*cnt)-cnt*2) +", cnt =" + cnt);
           // System.out.print(cnt + " " + (N-cnt*2));
        }

        // for(int i=0;i<list.size();i++){
        //     System.out.println("start = " + list.get(i)[0] + ", end = " + list.get(i)[1] + ", cnt = " + list.get(i)[2]);
        // }

        long answer=0;

        for(int i=0;i<list.size();i++){
            int start=list.get(i)[0];
            int end=list.get(i)[1];
            int count=list.get(i)[2];

            for(int k=count;k>=1;k--){
                // 계산
                long tmp=combination(start+end, start)%DIVIDE;
                answer+=tmp;
                answer%=DIVIDE;
                //

                start++;
                end++;
            }
        }
        System.out.println(answer);
    }
}