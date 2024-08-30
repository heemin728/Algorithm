import java.io.*;
import java.util.*;

public class Solution {

    static ArrayList<Integer> Xs;
    static ArrayList<Integer> Ys;

    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        long w;

        public Edge(int a, int b, long w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            if(this.w >e.w){
                return 1;
            }
            else if(this.w==e.w){
                return 0;
            }
            return -1;
        }
    }

    // 
    static long getDist(int a, int b) {
        
        long dx = Xs.get(a) - Xs.get(b);
        long dy = Ys.get(a) - Ys.get(b);
    
        return dx * dx + dy * dy;
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            PriorityQueue<Edge> pq = new PriorityQueue<>();

            ArrayList<long[]>[] list = new ArrayList[N + 1];

            Xs=new ArrayList<>();
            Ys=new ArrayList<>();

            st=new StringTokenizer(br.readLine());
            Xs.add(0);
            for(int i=0;i<N;i++){
                int a=Integer.parseInt(st.nextToken());
                Xs.add(a);
            }

            Ys.add(0);
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                int a=Integer.parseInt(st.nextToken());
                Ys.add(a);
            }

            double E=Double.parseDouble(br.readLine());

            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }
            boolean[] visited = new boolean[N + 1];


            for(int i=1;i<=N;i++){
                for(int j=i+1;j<=N;j++){
                    // i, j 
                    list[i].add(new long[]{j,getDist(i,j)});
                    list[j].add(new long[]{i,getDist(i,j)});
                }
            }

            pq.add(new Edge(0, 1, 0));

            double answer = 0;

            while (!pq.isEmpty()) {

                Edge e = pq.poll();
                int a = e.a;
                int b = e.b;
                long w = e.w;

                if (visited[b]) {
                    continue;
                }
                answer += w;

                visited[b] = true;

                for (int i = 0; i < list[b].size(); i++) {
                    long[] next = list[b].get(i);

                    if (visited[(int) next[0]]) {
                        continue;
                    }

                    pq.add(new Edge(b, (int)next[0], next[1]));
                }
            }
            answer*=E;
            sb.append("#").append(t).append(" ").append(Math.round(answer)).append("\n");
        }
        System.out.println(sb);
    }
}