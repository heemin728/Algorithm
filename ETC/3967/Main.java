import java.util.*;
import java.io.*;

public class Main {
    static char[][] map=new char[5][9];
    static int[] arr=new int[12];
    static boolean[] visited=new boolean[13];
    static boolean keep=true;

    static int intoInt(char c){
        if(c=='x'){
            return 0;
        }

        return c-'A'+1;
    }

    static boolean check(){
        int[] sums = new int[6];
        sums[0] = arr[0] + arr[2] + arr[5] + arr[7];
        sums[1] = arr[1] + arr[2] + arr[3] + arr[4];
        sums[2] = arr[1] + arr[5] + arr[8] + arr[11];
        sums[3] = arr[7] + arr[8] + arr[9] + arr[10];
        sums[4] = arr[4] + arr[6] + arr[9] + arr[11];
        sums[5] = arr[0] + arr[3] + arr[6] + arr[10];

        for(int i=0;i<=5;i++){
            if(sums[i]!=26){
                return false;
            }
        }
        return true;        
    }

    static void updateMap(){
        int index=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(map[i][j]=='.'){
                    continue;
                }
                map[i][j]=(char)(arr[index++]+64);
            }
        }
    }

    static void dfs(int cnt){
        if(!keep){
            return;
        }
        if(cnt==12){
            if(check()){
                keep=false;
                updateMap();
            }
            return;
        }

        if(arr[cnt]!=0){
            dfs(cnt+1);
        }
        else{
            for(int i=1;i<=12;i++){
                if(visited[i]){
                    continue;
                }
                visited[i]=true;
                arr[cnt]=i;
                dfs(cnt+1);
                arr[cnt]=0;
                visited[i]=false;
            }            
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        int index=0;
        for(int i=0;i<5;i++){
            String s=br.readLine();
            for(int j=0;j<9;j++){
                map[i][j]=s.charAt(j);

                if(map[i][j]!='.'){
                    int num=intoInt(map[i][j]);
                    arr[index++]=num;
                    visited[num]=true;
                }
            }
        }
        dfs(0);
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
