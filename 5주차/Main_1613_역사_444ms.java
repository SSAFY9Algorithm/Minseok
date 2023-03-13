import java.io.*;
import java.util.*;

public class Main_1613_역사_444ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] dist = new int[n+1][n+1];
		for (int i=1; i<n+1; i++)
			Arrays.fill(dist[i], 100000);
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// a -> b 이면 1
			dist[a][b] = 1;
		}
		// 간선 연결해서 거리 갱신
		for (int m=1; m<n+1; m++)
			for (int i=1; i<n+1; i++)
				for (int j=1; j<n+1; j++)
					dist[i][j] = Math.min(dist[i][j], dist[i][m] + dist[m][j]);
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		for (int i=0; i<s; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 서로 못가면 0
			if (dist[a][b] == 100000 && dist[b][a] == 100000)
				sb.append("0\n");
			// a -> b 못가면 1
			else if (dist[a][b] == 100000)
				sb.append("1\n");
			// a -> b 가면 -1
			else
				sb.append("-1\n");
		}
		System.out.print(sb.toString());
	}
}
