import java.io.*;
import java.util.*;

public class Solution {

    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int w;

        public Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }

    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            ArrayList<int[]>[] list = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                list[i] = new ArrayList<>();
            }
            boolean[] visited = new boolean[V + 1];


            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                list[A].add(new int[] { B, C });
                list[B].add(new int[] { A, C });

            }

            pq.add(new Edge(0, 1, 0));

            long answer = 0;

            while (!pq.isEmpty()) {

                Edge e = pq.poll();
                int b = e.b;
                int w = e.w;

                if (visited[b]) {
                    continue;
                }
                answer += w;

                visited[b] = true;

                for (int i = 0; i < list[b].size(); i++) {
                    int[] next = list[b].get(i);

                    if (visited[next[0]]) {
                        continue;
                    }

                    pq.add(new Edge(b, next[0], next[1]));
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}