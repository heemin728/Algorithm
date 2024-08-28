import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N 입력 받기
        int N = Integer.parseInt(br.readLine());
        List<Integer> v = new ArrayList<>();

        // N개의 숫자 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            v.add(Integer.parseInt(st.nextToken()));
        }

        // 찾고자 하는 cnt 입력 받기
        int find = Integer.parseInt(br.readLine());
        int cnt = N / 2;

        while (cnt >= 1) {
            int num = N / cnt;

            for (int i = 0; i < N; i += num) {
                Collections.sort(v.subList(i, Math.min(i + num, N)));
            }

            if (cnt == find) {
                break;
            }
            cnt /= 2;
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(v.get(i)).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}

