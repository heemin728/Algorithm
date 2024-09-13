import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            int building = list.get(i);

            double biggest = 0;

            // before
            for (int j = i - 1; j >= 0; j--) {
                int next = list.get(j);
                double degree = Math.toDegrees(Math.atan2(Math.abs(i-j), building - next));

                // if (building == 7) {
                //     System.out.println("degree = " + degree);
                // }

                if (biggest < degree) {
                    biggest = degree;
                    cnt++;
                }
            }

            // if (building == 7) {
            //     System.out.println("-----");
            // }
           // System.out.println("-----");

            biggest = 0;
            // after
            for (int j = i + 1; j < N; j++) {
                int next = list.get(j);
                double degree = Math.toDegrees(Math.atan2(Math.abs(i-j), building - next));

                // if (building == 7) {
                //     System.out.println("degree = " + degree);
                // }
                if (biggest < degree) {
                    biggest = degree;
                    // possible
                    cnt++;
                }
            }

            // if (building == 7) {
            //     System.out.println(cnt);   
            // }
            answer = Math.max(cnt, answer);
        }
        System.out.println(answer);
    }    
}
