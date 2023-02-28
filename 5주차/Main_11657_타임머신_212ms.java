import java.io.*;
import java.util.*;

public class Main_11657_타임머신_212ms {
	static int n, m;
	static int[][] arr;
	static long[] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dist = new long[n+1];
		arr = new int[m][3];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		if (Bellman())
			sb.append("-1\n");
		else
			for (int i=2; i<n+1; i++)
				sb.append(dist[i] == Integer.MAX_VALUE ? "-1" : dist[i]).append("\n");
		
		
		System.out.print(sb.toString());
	}
	
	// 마지막에도 dist에 변화가 있는지 확인
	static boolean Bellman() {
		for (int i=0; i<n; i++) {
			for (int[] x : arr) {
				int a = x[0];
				int b = x[1];
				int c = x[2];
				if (dist[a] != Integer.MAX_VALUE && dist[b] > dist[a] + c) {
					dist[b] = dist[a] + c;
					if (i == n-1)
						return true;
				}
			}
		}
		return false;
	}
}
