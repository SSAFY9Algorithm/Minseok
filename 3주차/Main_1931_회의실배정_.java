import java.io.*;
import java.util.*;

public class Main_1931_회의실배정_ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int arr[][] = new int[n][2];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
//		끝나는 시간으로 정렬, 같은 경우 시작 시간 비교
		Arrays.sort(arr, (o1, o2) -> (o1[1] == o2[1]) ? o1[0]-o2[0] : o1[1]-o2[1]);
		int end = arr[0][1];
		int cnt = 1;
		for (int i=1; i<n; i++) {
			if (end <= arr[i][0]) {
				end = arr[i][1];
				++cnt;
			}
		}
		System.out.println(cnt);
	}
}
