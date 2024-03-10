import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arrs = new int[n];

        for (int i = 0; i < n; i++) {
            arrs[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        for(int i = n - 2; i >= 0; i--) {
            if (arrs[i] > arrs[i + 1]) {
                answer += (arrs[i] - arrs[i + 1] + 1);
                arrs[i] = arrs[i + 1] - 1;
            } else if(arrs[i] == arrs[i + 1]) {
                answer += 1;
                arrs[i] = arrs[i + 1] - 1;
            }
        }

        System.out.println(answer);
    }
}