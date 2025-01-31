import java.io.*;
import java.util.*;

public class Main {
    static int[] fs=new int[4];
    static double[][] chances=new double[4][4];
    static int[] results = new int[6];
    static double answer=0;

    static void calculate(){
        // 1-2, 1-3, 1-4, 2-3, 2-4, 3-4
        int[] win=new int[4];
        double possiblity=1;

        if(results[0]==2){
            win[0]+=3;
            possiblity*=chances[0][1];
        }
        else if(results[0]==0){
            win[1]+=3;
            possiblity*=chances[1][0];
        }
        else{
            win[0]++;
            win[1]++;
            possiblity*=(1-chances[1][0]-chances[0][1]);
        }

        if(results[1]==2){
            win[0]+=3;
            possiblity*=chances[0][2];
            
        }
        else if(results[1]==0){
            win[2]+=3;
            possiblity*=chances[2][0];
        }
        else{
            win[0]++;
            win[2]++;
            possiblity*=(1-chances[2][0]-chances[0][2]);
        }

        
        if(results[2]==2){
            win[0]+=3;
            possiblity*=chances[0][3];
        }
        else if(results[2]==0){
            win[3]+=3;
            possiblity*=chances[3][0];
        }
        else{
            win[0]++;
            win[3]++;
            possiblity*=(1-chances[3][0]-chances[0][3]);
        }

        
        if(results[3]==2){
            win[1]+=3;
            possiblity*=chances[1][2];
        }
        else if(results[3]==0){
            win[2]+=3;
            possiblity*=chances[2][1];
        }
        else{
            win[1]++;
            win[2]++;
            possiblity*=(1-chances[2][1]-chances[1][2]);
        }

        
        if(results[4]==2){
            win[1]+=3;
            possiblity*=chances[1][3];
        }
        else if(results[4]==0){
            win[3]+=3;
            possiblity*=chances[3][1];
        }
        else{
            win[1]++;
            win[3]++;
            possiblity*=(1-chances[3][1]-chances[1][3]);
        }

        
        if(results[5]==2){
            win[2]+=3;
            possiblity*=chances[2][3];
        }
        else if(results[5]==0){
            win[3]+=3;
            possiblity*=chances[3][2];
        }
        else{
            win[2]++;
            win[3]++;
            possiblity*=(1-chances[3][2]-chances[2][3]);
        }

        int beat=0;
        for(int i=1;i<4;i++){
            if(win[0] >= win[i]){
                beat++;
            }
        }

        if(beat >=2 ){
            answer+=possiblity;
        }        
    }
    
    static void combination(int index){
        if(index==6){
            calculate();
            return;
        }
        for(int i=0;i<=2;i++){
            results[index]=i;
            combination(index+1);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        for(int i=0;i<4;i++){
            fs[i]=Integer.parseInt(st.nextToken());
        }
        
        for(int i=0;i<4;i++){
            for(int j=i+1;j<4;j++){
                double chance = (4.0 * fs[i]) / (fs[i] * 5 + fs[j] * 5);
                double chance2 = (4.0 * fs[j]) / (fs[i] * 5 + fs[j] * 5);
                chances[i][j]=chance;
                chances[j][i]=chance2;
            }
        }
        combination(0);
        System.out.printf("%.3f",answer*100);
    }
}
