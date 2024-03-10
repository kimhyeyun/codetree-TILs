import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    
        int[] arrs = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arrs[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0 ; i < n - 1; i++) {
            round(arrs, n);
            
            for(int j = 0; j < n; j++) {
                System.out.print(arrs[j] + " ");
            }
            System.out.println();
        }
    }

    public static void round(int[] arrs, int n) {
        for (int i = 1; i < n; i++) {
            if (arrs[i - 1] > arrs[i]) {
                int tmp = arrs[i - 1];
                arrs[i - 1] = arrs[i];
                arrs[i] = tmp;
            }
        }
    }
}