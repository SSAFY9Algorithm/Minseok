import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i=0; i<n; i++)
            arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int l = 1;
        int r = arr[n-1] - arr[0];
        int answer = 0;

        // 가능한 거리 체크
        while (l <= r) {
            int mid = (l+r) / 2;
            int cnt = 1;
            int check = arr[0];
            for (int i=1; i<n; i++) {
                // 비교했을때 거리 이상이라면 cnt 증가
                if (arr[i] - check >= mid) {
                    cnt += 1;
                    check = arr[i];
                }
            }
            if (cnt < c)
                r = mid-1;
            else {
                l = mid+1;
                answer = mid;
            }
        }
        System.out.println(answer);
    }
}