import java.io.*;
import java.util.*;

public class Main_1738_골목길_200ms {
	static int n, m;
	static int[] dist, before;
	static int[][] arr;
	static ArrayList<Integer>[] load;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 지나온 경로 저장
		before = new int[n+1];
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MIN_VALUE);
		arr = new int[m][3];
		load = new ArrayList[n+1];
		visit = new boolean[n+1];
		
		for (int i=1; i<n+1; i++)
			load[i] = new ArrayList<>();
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[i][0] = a;
			arr[i][1] = b;
			arr[i][2] = c;
			load[a].add(b);
		}
		
		// 시작점
		dist[1] = 0;
		
		// 최대 이득 사이클 존재
		if (Bellman())
			sb.append("-1\n");
		else {
			int idx = n;
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			while (idx != 0) {
				stack.push(idx);
				idx = before[idx];
			}
			while (!stack.isEmpty())
				sb.append(stack.pop() + " ");
		}
		System.out.println(sb.toString());
	}

	// 사이클이 있는지 찾고 + 끝까지 갈 수 있는지
	static boolean Bellman() {
		for (int i=0; i<n; i++) {
			for (int[] x : arr) {
				int a = x[0];
				int b = x[1];
				int c = x[2];
				if (dist[a] != Integer.MIN_VALUE && dist[b] < dist[a] + c) {
					dist[b] = dist[a] + c;
					before[b] = a;
					if (i == n-1 && check(a))
						return true;
				}
			}
		}
		return false;
	}
	
	// 끝까지 갈 수 있는 지 체크
	static boolean check(int idx) {
		if (idx == n)
			return true;
		for (int next : load[idx]) {
			if (!visit[next]) {
				visit[next] ^= true;
				if(check(next)) return true;
			}
		}
		return false;
	}
}