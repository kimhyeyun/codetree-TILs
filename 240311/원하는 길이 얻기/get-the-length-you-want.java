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
        List<int[]> list = new LinkedList<>();

        int answer = 0;
        for(int i = 0; i < n; i++) {
            int len = Integer.parseInt(st.nextToken());
            if (len == 10) {
                answer += 1;
                continue;
            }

            int p = len / 10;
            int r = len % 10;
            
            if (p == 0) continue;
            if (r == 0) list.add(new int[]{p, p - 1});   
            else list.add(new int[]{p, p});
        }

        list.sort((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });

        for (int[] arr : list) {
            if (m == 0) break;
            
            if (arr[1] <= m) {
                answer += arr[0];
                m -= arr[1];
            } else {
                answer += m;
                m = 0;
            }
        }

        System.out.println(answer);
    }
}