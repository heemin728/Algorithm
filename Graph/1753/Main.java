import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 99999999;

    static int E, V, K;
    static int[] distance;
    static ArrayList<Node>[] list;

    static class Node implements Comparable<Node>{
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        boolean[] visited=new boolean[V+1];
        distance[start]=0;

        while (!pq.isEmpty()) {
            int current = pq.peek().index;
            int cost = pq.peek().distance;
            pq.poll();
            
            if (visited[current]) {
                continue;
            }

            visited[current] = true;
            for (Node node : list[current]) {
                if (distance[node.index] > node.distance + distance[current]) {
                    distance[node.index] = node.distance + distance[current];
                    pq.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
    
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        distance = new int[V + 1];
        list=new ArrayList[V+1];

        for (int i = 1; i <= V; i++) {
            list[i]=new ArrayList<>();
            distance[i] = INF;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[u].add(new Node(v, w));
        }

        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            System.out.println(distance[i]==INF ? "INF" : distance[i]);
        }
    }
}
