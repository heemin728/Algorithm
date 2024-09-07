
import java.io.*;

public class Main {

    static int n;
    static String cat;
    static int[] alphabet = new int[26];
    static int answer = 0;

    static int check() {
        int cnt = 0;

        for (int i = 0; i < 26; i++) {
            if (alphabet[i] != 0) {
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cat = br.readLine();

        int start = 0;
        int end = 1;

        alphabet[cat.charAt(0) - 'a']++;
        alphabet[cat.charAt(1) - 'a']++;

        while (true) {

            int cnt = check();

            if (cnt <= n) {
                answer = Math.max(answer, end - start + 1);

                end++;
                if (end >= cat.length()) {
                    break;
                }
                alphabet[cat.charAt(end) - 'a']++;
            } else {
                alphabet[cat.charAt(start) - 'a']--;
                start++;
            }
        }
        System.out.println(answer);
    }
}
