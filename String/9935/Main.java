import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        String input=br.readLine();
        String bomb=br.readLine();

        Stack<Character> stack=new Stack<>();
        Stack<Integer> index=new Stack<>();

        for(int i=0;i<input.length();i++){
            char c=input.charAt(i);
            stack.push(c);

            boolean findAny=false;
            int k=0;
            for(k=0;k<bomb.length();k++){
                if(c==bomb.charAt(k)){
                    findAny=true;
                    break;
                }
            }

            if(!findAny){
                index.push(-1);
                continue;
            }
            
            index.push(k);
            
            // end of string
            if(k==bomb.length()-1){
                Stack<Character>tmp=new Stack<>();
                Stack<Integer> tmpIndex=new Stack<>();

                
                while(!stack.isEmpty() && k>=0){
                    
                    int pop=index.pop();
                    char pc=stack.pop();

                    tmp.add(pc);
                    tmpIndex.add(pop);
            
                    if((stack.isEmpty() && k!=0) || pop != k){
                        // out
                        while(!tmp.isEmpty()){
                           stack.push(tmp.pop());
                           index.push(tmpIndex.pop());
                        }
                        break;
                    }
                    k--;
                }
            }
        }

        if(stack.isEmpty()){
            System.out.println("FRULA");
            return;
        }

        ArrayList<Character> list=new ArrayList<>();
        while(!stack.isEmpty()){
            char c=stack.pop();
            list.add(c);
        }
        
        for(int i=list.size()-1;i>=0;i--){
            sb.append(list.get(i));
        }
        System.out.println(sb);
    }
}