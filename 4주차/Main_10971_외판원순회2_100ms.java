package temp;

import java.io.*;
import java.util.*;

public class Main_10971_외판원순회2_100ms {
    static boolean[] visit;
    static int min = Integer.MAX_VALUE;
    static int n;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        visit = new boolean[n];
        for (int i=0; i<n; i++) {
            visit[i] = true;
            rec(i, i, 1, 0);
            visit[i] = false;
        }
        System.out.println(min);

    }
    static void rec(int first, int idx, int val, int sum) {
        if (val == n) {
            if (arr[idx][first] == 0)
                return;
            sum += arr[idx][first];
            min = Math.min(min, sum);
            return;
        }
        if (min <= sum)
            return;
        for (int i=0; i<n; i++) {
            if (visit[i] || arr[idx][i] == 0) continue;
            visit[i] = true;
            rec(first, i, val+1, sum + arr[idx][i]);
            visit[i] = false;
        }
    }
}
