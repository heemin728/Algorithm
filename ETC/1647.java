/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
import java.io.*;

public class Main
{
    static int N,M;
    static int[] parent;
    
    static void init(){
        for(int i=1;i<=N;i++){
            parent[i]=i;
        }
    }
    
    static int find(int x){
        if(parent[x]==x){
            return x;
        }
        
        return parent[x]=find(parent[x]);
    }
    
    static void union(int x,int y){
        int rx=find(x);
        int ry=find(y);
        
        if(rx < ry){
            parent[ry]=rx;
        }
        else{
            parent[rx]=ry;
        }
    }
    
    static class Edge implements Comparable<Edge>{
        int a;
        int b;
        int w;
        
        public Edge(int a,int b,int w){
            this.a=a;
            this.b=b;
            this.w=w;
        }
        
        @Override
        public int compareTo(Edge o){
            return this.w-o.w;
        }
    }
    
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		parent=new int[N+1];
		init();
		
		PriorityQueue<Edge> pq=new PriorityQueue<>();
		
		for(int i=0;i<M;i++){
		    st=new StringTokenizer(br.readLine());
		    
		    int A=Integer.parseInt(st.nextToken());
		    int B=Integer.parseInt(st.nextToken());
		    int W=Integer.parseInt(st.nextToken());
		    
		    pq.add(new Edge(A,B,W));
		}
		
		int cnt=0;
		int answer=0;
		int maximum=0;
		
		while(cnt != N-1){
		    Edge e=pq.poll();
		    int a=e.a;
		    int b=e.b;
		    int w=e.w;
		    
		    if(find(a)==find(b)){
		        continue;
		    }
		    
		    cnt++;
		    union(a,b);
		    answer+=w;
		    maximum=Math.max(maximum,w);
		}
		
		answer-=maximum;
		System.out.println(answer);

	}
}
