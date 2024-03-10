import java.io.*;
import java.util.*;
public class Main {
    static int n, answer;
    static int[] arrs;
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arrs = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            arrs[i] = Integer.parseInt(st.nextToken());
        }

        // answer = Integer.MAX_VALUE;
        // sol(0, new int[n], new boolean[n]);

        // System.out.println(answer);

        Arrays.sort(arrs);
        for(int i = 0; i < n; i++) {
            answer += (arrs[i] * (n - i));
        }

        System.out.println(answer);
    }

    static void sol(int count, int[] indexes, boolean[] isSelected) {
        if (count == n) {
            answer = Math.min(answer, cal(indexes));
            return;
        }

        for (int i = 0; i < n ; i++) {
            if (isSelected[i]) continue;

            isSelected[i] = true;
            indexes[count] = i;
            sol(count + 1, indexes, isSelected);
            isSelected[i] = false; 
        }

    }

    static int cal (int[] indexes) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (arrs[indexes[i]] * (n - i)); 
        }

        return sum;
    }
}