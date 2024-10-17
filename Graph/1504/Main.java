import java.util.*;
import java.io.*;

public class Main {
    static int N, E;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node> {
        long next;
        long cost;

        public Node(long next, long cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static long[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            long cur = pq.peek().next;
            long cost = pq.peek().cost;
            pq.poll();

            if (cost > dist[(int) cur]) continue;

            for (Node neighbor : graph[(int) cur]) {
                long nn = neighbor.next;
                long cc = neighbor.cost;

                if (dist[(int) nn] > dist[(int) cur] + cc) {
                    dist[(int) nn] = dist[(int) cur] + cc;
                    pq.add(new Node(nn, dist[(int) nn]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) { 
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c)); 
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long[] distFromStart = dijkstra(1);
        long[] distFromV1 = dijkstra(v1);
        long[] distFromV2 = dijkstra(v2);

        long answer = Math.min(distFromStart[v1] + distFromV1[v2] + distFromV2[N],
                                distFromStart[v2] + distFromV2[v1] + distFromV1[N]);

        if (answer >= Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }
}
