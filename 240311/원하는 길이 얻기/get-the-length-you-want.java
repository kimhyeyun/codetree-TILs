import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] lens = new int[n];

        int answer = 0;
        for(int i = 0; i < n; i++) {
            lens[i] = Integer.parseInt(st.nextToken());
            if (lens[i] == 10) answer += 1;
        }

        Arrays.sort(lens);

        for (int len : lens) {
            if (len == 10) continue;
            if (m == 0) break;

            int t = len / 10;
            if (t - 1 <= m) {
                answer += t;
                m -= (t - 1);
            } else {
                answer += m;
                m = 0;
                t = 10 * m;
                
                if (len - t == 10) 
                    answer += 1;
            }
        }

        System.out.println(answer);
    }
}