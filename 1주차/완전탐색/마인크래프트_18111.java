import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int block[] = new int[257];
        int time[] = new int[257];

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int min = 257;
        int max = -1;

        int[][] arr = new int[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                int put = Integer.parseInt(st.nextToken());
                arr[i][j] = put;
                max = Math.max(max, put);
                min = Math.min(min, put);
            }
        }
        if (max == min) System.out.println("0 " + max);
        else {
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    for (int k=min; k<=max; k++) {
                        if (arr[i][j] < k) {
                            time[k] += k - arr[i][j];
                            block[k] -= k - arr[i][j];
                        }
                        else if (arr[i][j] > k){
                            time[k] += 2 * (arr[i][j] - k);
                            block[k] += arr[i][j] - k;
                        }
                    }
                }
            }
            int answer = Integer.MAX_VALUE;
            int idx = -1;
            for (int i=min; i<=max; i++) {
                if (time[i] <= answer && block[i] >= -b) {
                    answer = time[i];
                    idx = i;
                }
            }
            System.out.println(answer + " " + idx);
        }
    }
}