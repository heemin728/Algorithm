import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        
        int x1 = Integer.parseInt(input[0]);
        int y1 = Integer.parseInt(input[1]);
        int x2 = Integer.parseInt(input[2]);
        int y2 = Integer.parseInt(input[3]);

        long cnt = 0;

        for (int x = x1 + 1; x <= x2; x++) {
            int y = y2;

            // 짝수인 경우
            if (x % 2 == 0) {
                if (x >= y) {
                    cnt += (y - y1);
                } else {
                    if (y % 2 == 1) {
                        y--;
                    }

                    // 겹치는 경우
                    if (x > y1 + 1) {
                        int diff = y - x;
                        diff += 2 - (diff % 2);
                        cnt += (diff / 2 + x - y1 - 1);
                    } else { // 겹치지 않는 경우
                        int diff = y - y1 - 1;
                        diff += 2 - (diff % 2);
                        cnt += (diff / 2);
                    }
                }
            } else { // 홀수인 경우
                if (x < y) {
                    if (y % 2 == 1) {
                        y--;
                    }

                    int diff = y - Math.max(x + 1, y1 + 1);
                    diff += 2 - (diff % 2);
                    cnt += (diff / 2);
                }
            }
        }
        System.out.println(cnt);
    }
}
