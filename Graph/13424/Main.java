import java.util.*;
import java.io.*;

public class Main {
    static int N,M,K;
    static ArrayList<Node>[] list;
    static ArrayList<Integer> fLocation;
    static int[][] dist;
    static final int MAX=99999999;

    static class Node implements Comparable<Node>{
        int to;
        int w;

        public Node(int to, int w){
            this.to=to;
            this.w=w;
        }

        public int compareTo(Node o){
            if(o.w > this.w){
                return 1;
            }
            else{
                return -1;
            }
        }

    }

    static void dijkstra(int num){

        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.add(new Node(num,0));
        dist[num][num]=0;

        while(!pq.isEmpty()){
            Node n=pq.poll();

            int node=n.to;
            int weight=n.w;

            for(int i=0;i<list[node].size();i++){
                Node n2=list[node].get(i);

                int nNode=n2.to;
                int nWeight=n2.w;

                if(dist[num][nNode] > weight+nWeight){
                    dist[num][nNode]=weight+nWeight;
                    pq.add(new Node(nNode,dist[num][nNode]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        int T=Integer.parseInt(br.readLine());
        
        for(int t=1;t<=T;t++){
            st=new StringTokenizer(br.readLine());

            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            list=new ArrayList[N+1];
            fLocation=new ArrayList<>();
            dist=new int[N+1][N+1];
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    dist[i][j]=MAX;
                }
            }

            for(int i=1;i<=N;i++){
                list[i]=new ArrayList<>();
            }

            for(int i=0;i<M;i++){
                st=new StringTokenizer(br.readLine());

                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                int c=Integer.parseInt(st.nextToken());

                list[a].add(new Node(b,c));
                list[b].add(new Node(a,c));

            }

            K=Integer.parseInt(br.readLine());

            st=new StringTokenizer(br.readLine());
            for(int i=0;i<K;i++){
                fLocation.add(Integer.parseInt(st.nextToken()));
            }

            int answer=0;
            int sum=MAX;
            for(int i=0;i<K;i++){
                dijkstra(fLocation.get(i));
            }

            for(int i=1;i<=N;i++){
                int tmp=0;
                for(int j=0;j<fLocation.size();j++){
                    tmp+=dist[fLocation.get(j)][i];
                }

                if(tmp < sum){
                    sum=tmp;
                    answer=i;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
