import java.io.*;
import java.util.*;

public class Main_10282_해킹_1444ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			List<int[]>[] arr = new ArrayList[n+1];
			int[] dist = new int[n+1];
			for (int i=1; i<n+1; i++) {
				arr[i] = new ArrayList<>();
				dist[i] = Integer.MAX_VALUE;
			}
			
			for (int i=0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				arr[b].add(new int[] {a, s});
			}
			
			// 다익스트라 이용
			PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
			heap.add(new int[] {c, 0});
			dist[c] = 0;
			while (!heap.isEmpty()) {
				int[] temp = heap.poll();
				int idx = temp[0];
				int degree = temp[1];
				if (dist[idx] < degree)
					continue;
				for (int[] tp : arr[idx]) {
					int next = tp[0];
					int weight = tp[1];
					if (dist[next] > degree + weight) {
						dist[next] = degree + weight;
						heap.add(new int[] {next, dist[next]});
					}
				}
			}
			int cnt = 0;
			int answer = 0;
			for (int i=1; i<n+1; i++) {
				if (dist[i] == Integer.MAX_VALUE) continue;
				answer = Math.max(answer, dist[i]);
				cnt++;
			}
			sb.append(cnt + " " + answer + "\n");
		}
		System.out.println(sb.toString());
	}
}
