import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] switches = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());

            if (type == 1) changeMultipleSwitches(switches, index, n);
            else changeRangeSwitches(switches, index, n);
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(switches[i] + " ");
        }
    }

    public static void changeMultipleSwitches (int[] switches, int index, int n) {
        for (int i = index; i <= n; i += index) {
            switches[i] = switches[i] == 0 ? 1 : 0;
        }
    }

    public static void changeRangeSwitches (int[] switches, int index, int n) {
        int left = index - 1;
        int right = index + 1;

        int start = -1, end = -1;

        while (true) {
            if (left <= 0 || n < right) break;
            if (switches[left] != switches[right]) break;

            start = left;
            end = right;

            left -= 1;
            right += 1;
        }

        if (start == -1 && end == -1) return;

        for (int i = start; i <= end; i++) {
            switches[i] = switches[i] == 0 ? 1 : 0;
        }
    }
}