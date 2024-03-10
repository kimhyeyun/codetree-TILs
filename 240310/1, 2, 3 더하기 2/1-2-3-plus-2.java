import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][4];

        if(n == 1 || n == 2) {
            System.out.println(1);
            return;
        }

        dp[1][1] = 1;
        dp[2][2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000007;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000007;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][1]) % 1000000007;
        }

        int answer = (dp[n][1] + dp[n][2] + dp[n][3]) % 1000000007;
        System.out.println(answer);
    }
}