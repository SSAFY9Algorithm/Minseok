import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] dp = new int[1001][1001];
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        dp[0][0] = 1;
        for (int i=1; i<=n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
            for (int j=1; j<i; j++)
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % 10007;
        }
        System.out.println(dp[n][k]);
    }
}