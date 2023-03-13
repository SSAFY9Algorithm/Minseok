import java.io.*;
import java.util.*;

public class Main_16398_행성연결_1072ms {
	static int[] root;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long answer = 0;
		int n = Integer.parseInt(st.nextToken());
		// 짧은 순 정렬
		PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		root = new int[n];
		for (int i=0; i<n; i++) {
			root[i] = i;
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if (i <= j) continue;
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j;
				temp[2] = cost;
				heap.add(temp);
			}
		}
		
		while (!heap.isEmpty()) {
			int[] x = heap.poll();
			int fa = find(x[0]);
			int fb = find(x[1]);
			if (fa == fb)
				continue;
			else if (fa < fb)
				root[fb] = root[fa];
			else if (fa > fb)
				root[fa] = root[fb];
			answer += x[2];
		}
		System.out.println(answer);
			
	}
	static int find(int num) {
		if (num != root[num])
			return root[num] = find(root[num]);
		return num;
	}
}
