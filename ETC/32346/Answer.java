import java.util.*;

public class Answer {
	static final int DIVIDE = 1000000007;
    static final int MOD = 1000000007;

    static int add(int a, int b) {
        return (a + b) < MOD ? (a + b) : (a + b - MOD);
    }

    static int mul(int a, int b) {
        return (int) ((1L * a * b) % MOD);
    }

    static int pow(int x, int n) {
        if (n == 0) return 1;
        return (n & 1) == 1 ? mul(x, pow(x, n - 1)) : pow(mul(x, x), n / 2);
    }

	static int bino(int n, int r, int[] fac, int[] inv) {
        if (r == 0 || r == n) return 1;
        return mul(fac[n], mul(inv[r], inv[n - r]));
    }


	static int solution(int n,String s){
		int ret=0;

		int[] fac = new int[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i++) {
            fac[i] = mul(fac[i - 1], i);
        }

        int[] inv = new int[n + 1];
        inv[n] = pow(fac[n], MOD - 2);
        for (int i = n; i >= 1; i--) {
            inv[i - 1] = mul(inv[i], i);
        }

        int res = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; i - j >= 0 && i + j - 1 < n; j++) {
                if (s.charAt(i - j) != '>' || s.charAt(i + j - 1) != '<') break;
                int a = i - j;
                int b = n - i - j;
                res = add(res, bino(a + b, a, fac, inv));
            }
        }
		ret=res;

		return ret;
	}

	////////////////////////////////////////////////////////////////////////

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

	public static int solution2(int N, String s){
		Stack<Character> stack=new Stack<>();

        ArrayList<int[]> list=new ArrayList<>();

		System.out.println("string = "+ s);
        //int start=0;
        int cnt=0;
        boolean valid=false;

        for(int i=0;i<s.length();i++){

            //System.out.println("i="+i+", size = "+s.length());
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
                    //System.out.println("N="+N+", - ("+(i-2*cnt)+") - ("+ cnt*2 +") = " + (N-(i-2*cnt)-cnt*2));
                    System.out.println( "2. start = " + (i-2*cnt) + ", end = " + (N-(i-2*cnt)-cnt*2) +", cnt =" + cnt);
                    cnt=0;
                }
            }
            else{
                if(stack.peek() == '>'){
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

        int answer=0;

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
		//System.out.println("ret " +answer);
		return answer;
	}



	///////////////////////////////////////////////////////////////////////


	public static void main(String[] args) {

		System.out.println(solution(6,">><><<"));
		System.out.println(solution2(6,">><><<"));
		//answer2(6,">>><<>");

		//
        
		// int n=6;
		
		// for(int k=0;k<100;k++){
		// 	String s = ""; // 
		// 	Random random = new Random();

		// 	for (int i = 0; i < n; i++) {
		// 		int value = random.nextInt(2); // 
		// 		if (value == 1) {
		// 			s += "<"; // 
		// 		} else {
		// 			s += ">"; //
		// 		}
		// 	}

		// 	//System.out.println(s); // 


		// 	int answer=solution(n,s);
		// 	//System.out.println("real answer = "+ answer);

		// 	int answer2=solution2(n,s);
		// 	//System.out.println("my answer = " + answer2);

		// 	if(answer != answer2){
		// 		System.out.println("when string is "+ s);
		// 		System.out.println("real answer = "+ answer);
		// 		System.out.println("my answer = " + answer2);
		// 		break;
		// 	}
		// }


	}
}