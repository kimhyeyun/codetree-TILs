import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][4];

        if(n == 1 || n == 2) {
            System.out.println(1);
            return;
        }

        if(n == 3) {
            System.out.println(3);
            return;
        }

        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= n; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000007;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000007;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1000000007;
        }

        long answer = (dp[n][1] + dp[n][2] + dp[n][3]) % 1000000007;
        System.out.println(answer);
    }
}