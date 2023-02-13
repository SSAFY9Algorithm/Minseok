import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

// Floyd - Warshall 사용
public class Main_1389_케빈베이컨의6단계법칙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][n+1];
        for (int i=1; i<n+1; i++) {
            for (int j=1; j<n+1; j++)
//              최대 거리인 100 저장
                arr[i][j] = 100;
//          나 자신의 거리 0
            arr[i][i] = 0;
        }

//      간선 연결
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
            arr[b][a] = 1;
        }

//      비교
        for (int k=1; k<n+1; k++) {
            for (int i=1; i<n+1; i++) {
                for (int j=1; j<n+1; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int idx = 1;
        for (int i=1; i<n+1; i++) {
            int sum = IntStream.of(arr[i]).sum();
            if (sum < min) {
                idx = i;
                min = sum;
            }
        }
        System.out.println(idx);
    }
}
