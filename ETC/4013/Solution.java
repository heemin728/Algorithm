import java.util.*;
import java.io.*;

public class Solution {
    static int T;
    static int K;
    static int[][] magnetic;
    static int[] dir;
    static ArrayList<int[]> turn;

    static void turnCounterClockwise(int n) {
        dir[n] = (dir[n] + 1) % 8;
    }

    static void turnClockwise(int n) {
        dir[n] = (dir[n] - 1 + 8) % 8; // 
    }

    static int getLeft(int n) {
        return (dir[n] + 6) % 8; // 
    }
    
    static int getRight(int n) {
        return (dir[n] + 2) % 8; // 
    }

    static void solution(){
        for(int i=0;i<turn.size();i++){
            int num=turn.get(i)[0];
            int d=turn.get(i)[1];

            
            int[] check=new int[4];
            check[num]=d;

            for(int n=num;n>=1;n--){

                if(magnetic[n][getLeft(n)] != magnetic[n-1][getRight(n-1)]){
                    check[n-1]=-check[n];
                }
                else{
                    break;
                }
            }
            for(int n=num;n<=2;n++){
                if(magnetic[n][getRight(n)] != magnetic[n+1][getLeft(n+1)]){
                    check[n+1]=-check[n];
                }
                else{
                    break;
                }
            }
            
            for(int k=0;k<4;k++){
                if(check[k]==1){
                    turnClockwise(k);
                }
                else if(check[k]==-1){
                    turnCounterClockwise(k);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        
        T=Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            turn=new ArrayList<>();
            magnetic=new int[4][8];
            dir=new int[4];

            K=Integer.parseInt(br.readLine());

            for(int i=0;i<4;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<8;j++){
                    magnetic[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0;i<K;i++){
                st=new StringTokenizer(br.readLine());

                int num=Integer.parseInt(st.nextToken())-1;
                int d=Integer.parseInt(st.nextToken());
                turn.add(new int[]{num,d});
            }

            solution();

            int answer=0;
            for(int i=0;i<4;i++){
                if(magnetic[i][dir[i]]!=0){
                    answer+=Math.pow(2,i);
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}