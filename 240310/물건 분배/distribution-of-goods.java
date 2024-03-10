import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sizes.add(a * b);
        }

        Collections.sort(sizes, Collections.reverseOrder());
        for (int i = 1; i <= k; i++) {
            if (sizes.get(i - 1) >= n) {
                System.out.println(i);
                return;
            }
            n -= sizes.get(i-1);
        }
    }
}