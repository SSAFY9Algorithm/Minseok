import java.io.*;
import java.util.*;

public class Main_1956_운동_480ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] dist = new int[n+1][n+1];
		for (int i=1; i<n+1; i++)
			Arrays.fill(dist[i], 4000000);
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[a][b] = c;
		}
		// 기본 floyd-warshall
		for (int k=1; k<n+1; k++)
			for (int i=1; i<n+1; i++)
				for (int j=1; j<n+1; j++)
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
		int answer = Integer.MAX_VALUE;
		for (int i=1; i<n+1; i++)
			for (int j=1; j<n+1; j++)
				answer = Math.min(answer, dist[i][j] + dist[j][i]);
		System.out.println(answer <= 4000000 ? answer : -1);
	}
}
