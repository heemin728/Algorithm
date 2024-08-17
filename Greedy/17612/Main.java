import java.io.*;
import java.util.*;
 
 
public class Main {
	public static int N, K;
	public static char[] arr;
	public static int answer = 500001;

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
    	N = Integer.parseInt(br.readLine());
    	
    	arr = new char[N];
        String s=br.readLine();
		for(int i=0;i<N;i++){
            arr[i]=s.charAt(i);
        }
		
		int balls = 0;
		int redBall = 0;
		int blueBall = 0;		
	
		for(int i=0;i<N;i++) {
			if(arr[i] == 'R') {
				redBall++;
			}
			if(arr[i] == 'B'){
				blueBall++;
			}
		}
			
		for(int i=0;i<N;i++) {
			if(arr[i] == 'R') {
				balls++;
			}else {
				break;
			}
		}
		
		answer = Math.min(answer,  redBall - balls);
		
		balls = 0;
		for(int i=N-1;i>=0;i--) {
			if(arr[i] == 'R') {
				balls++;
			}else {
				break;
			}
		}
		
		answer = Math.min(answer,  redBall - balls);
		
		balls = 0;
		for(int i=0;i<N;i++) {
			if(arr[i] == 'B') {
				balls++;
			}else {
				break;
			}
		}
		
		answer = Math.min(answer,  blueBall - balls);
		
		balls = 0;
		for(int i=N-1;i>=0;i--) {
			if(arr[i] == 'B') {
				balls ++;
			}else {
				break;
			}
		}
		answer = Math.min(answer,  blueBall - balls);	
        sb.append(answer);	
		System.out.println(sb);
    }
    
      
}