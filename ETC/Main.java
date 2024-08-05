import java.util.Scanner;

public class Main {
    static final int MAX = 1000001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        int K = scanner.nextInt(); 
        int N = scanner.nextInt(); 

        long[] line = new long[K];

        long ave = 0;
        long mVal = 0;

        for (int i = 0; i < K; i++) {
            line[i] = scanner.nextLong();
            ave += line[i];
        }

        ave = ave / N;

        long start = 1;
        long end = ave;

        while (start <= end) {
            long count = 0;

            long mid = (start + end) / 2;

            for (int i = 0; i < K; i++) {
                count += line[i] / mid;
            }

            if (count >= N) {
                start = mid + 1;
                mVal = Math.max(mVal, mid);
            } else {
                end = mid - 1;
            }

        }

        System.out.println(mVal);
        scanner.close();
    }
}