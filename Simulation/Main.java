package Simulation;

import java.io.*;
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {

            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
 
            int[] house = new int[n];
            st = new StringTokenizer(br.readLine());
            int total = 0;
            for(int j = 0; j < n; j++) {
                house[j] = Integer.parseInt(st.nextToken());
                total += house[j];
            }
 E
            if(n == m) {
                if(total < k) System.out.println(1);
                else System.out.println(0);
            } else {
                int count = 0;
 
                int start = 0;
                int end = m - 1;
                int sum = 0;
                for(int j = 0; j <= end; j++) {
                    sum += house[j];
                }
 
                while(start < n) {
                    end++;
                    if(sum < k) count++;
                    sum -= house[start++];
                    sum += house[end % n];
                }
                System.out.println(count);
            }
        }
    }
}
