import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];
        int[][] dp = new int[n+1][100001];
        int lim = 100000;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 기본적인 Knapsack 이용하며 인원 넘었을 때의 최소 가격 체크
        for (int i=1; i<n+1; i++) {
            for (int j=0; j<=lim; j++) {
                if (j < arr[i-1][0])
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-arr[i-1][0]] + arr[i-1][1]);
                if (dp[i][j] >= c) {
                    lim = j;
                    break;
                }
            }
        }
        System.out.println(lim);
    }
}