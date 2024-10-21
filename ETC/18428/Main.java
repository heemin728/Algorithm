import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] board;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};

    static boolean checkRange(int i, int j){
        return i>=0 && j>=0 && i<N && j<N;
    }

    static boolean solution(int i1, int j1, int i2, int j2, int i3, int j3){
        boolean possible=true;

        board[i1][j1]='O';
        board[i2][j2]='O';
        board[i3][j3]='O';

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[i][j]!='S'){
                    continue;
                }
                
                 for(int k=0;k<4;k++){
                    int index=1;

                    while(true){

                        int nx=i+dx[k]*index;
                        int ny=j+dy[k]*index;

                        index++;

                        if(!checkRange(nx, ny) || board[nx][ny]=='O'){
                            break; 
                        }

                        if(board[nx][ny]=='T'){

                            board[i1][j1]='X';
                            board[i2][j2]='X';
                            board[i3][j3]='X';
                    
                            return false;
                        }
                    }
                 }
            }
        }

        board[i1][j1]='X';
        board[i2][j2]='X';
        board[i3][j3]='X';

        return possible;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());

        board=new char[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                String line=st.nextToken();
                board[i][j]=line.charAt(0);
            }
        }

        boolean flag=false;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){

                if(flag){
                    break;
                }
                
                if(board[i][j] != 'X'){
                    continue;
                }

                for(int i2=i;i2<N;i2++){
                    for(int j2=(i2==i ? j+1 : 0);j2<N;j2++){

                        if(flag){
                            break;
                        }

                        if(board[i2][j2]!='X'){
                            continue;
                        }

                        for(int i3=i2;i3<N;i3++){
                            for(int j3=(i3==i2 ? j2+1 : 0);j3<N;j3++){

                                if(flag){
                                    break;
                                }

                                if(board[i3][j3]!='X'){
                                    continue;
                                }

                                flag = solution(i, j, i2, j2, i3, j3);
                            }
                        }
                    }
                }
            }
        }

        if(flag){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }  
    }
}
