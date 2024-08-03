import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        String a = scanner.next();
        String b = scanner.next();
        
        ArrayList<Boolean> start = new ArrayList<>();
        ArrayList<Boolean> start2 = new ArrayList<>();
        ArrayList<Boolean> end = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            start.add(a.charAt(i) - '0' == 1);
            start2.add(a.charAt(i) - '0' == 1);
            end.add(b.charAt(i) - '0' == 1);
        }

        for (int i = 1; i < N; i++) {
            if (start.get(i - 1) != end.get(i - 1)) {
                count++;

                start.set(i - 1, !start.get(i - 1));
                start.set(i, !start.get(i));
                if (i != N - 1) {
                    start.set(i + 1, !start.get(i + 1));
                }
            }
        }

        boolean keep = true;

        for (int i = 0; i < N; i++) {
            if (start.get(i) != end.get(i)) {
                keep = false;
                break;
            }
        }
        if (keep) {
            System.out.println(count);
            return;
        }

        int count2 = 1;
        start2.set(0, !start2.get(0));
        start2.set(1, !start2.get(1));

        for (int i = 1; i < N; i++) {
            if (start2.get(i - 1) != end.get(i - 1)) {
                count2++;

                start2.set(i - 1, !start2.get(i - 1));
                start2.set(i, !start2.get(i));
                if (i != N - 1) {
                    start2.set(i + 1, !start2.get(i + 1));
                }
            }
        }

        keep = true;
        for (int i = 0; i < N; i++) {
            if (start2.get(i) != end.get(i)) {
                keep = false;
                break;
            }
        }
        if (keep) {
            System.out.println(count2);
            return;
        }

        System.out.println("-1");
    }
}
