import java.util.*;
import java.io.*;

public class Main {

    public static class Work implements Comparable<Work> {
        int d;
        int w;

        public Work(int d, int w) {
            this.w = w;
            this.d = d;
        }

        public int getDay() {
            return this.d;
        }

        public int getWork() {
            return this.w;
        }

        @Override
        public int compareTo(Work other) {
            return other.w - this.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Work> q = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            q.add(new Work(d, w));
        }

        int getPoint = 0;

        boolean[] days = new boolean[1001];

        while (!q.isEmpty()) {
            int day = q.peek().getDay();
            int point = q.peek().getWork();
            // System.out.print(point + " ");
            q.poll();

            for (int i = day; i >= 1; i--) {
                if (!days[i]) {
                    days[i] = true;
                    getPoint += point;
                    // System.out.print(point + " ");
                    break;
                }
            }
        }
        System.out.println(getPoint);
    }
}
