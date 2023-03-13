import java.io.*;
import java.util.*;

public class Main_1774_우주신과의교감_676ms {
	static int[] root;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// 좌표 저장용 배열
		int[][] arr = new int[n][3];
		root = new int[n+1];
		
		// 좌표 저장
		for (int i=1; i<n+1; i++) {
			root[i] = i;
			st = new StringTokenizer(br.readLine());
			arr[i-1][0] = i;
			arr[i-1][1] = Integer.parseInt(st.nextToken());
			arr[i-1][2] = Integer.parseInt(st.nextToken());
		}
		
		// 연결되어있는 점 저장 
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int fa = find(Integer.parseInt(st.nextToken()));
			int fb = find(Integer.parseInt(st.nextToken()));
			if (fa < fb)
				root[fb] = root[fa];
			else if (fa > fb)
				root[fa] = root[fb];
		}
		
		// 힙으로 오름차순 정렬
		PriorityQueue<double[]> heap = new PriorityQueue<>((o1, o2) -> Double.compare(o1[0], o2[0]));
		for (int i=0; i<n-1; i++)
			for (int j=i+1; j<n; j++)
				heap.add(new double[] {Math.sqrt(Math.pow(arr[i][1] - arr[j][1], 2) + Math.pow(arr[i][2] - arr[j][2], 2)), i+1, j+1});
		
		double answer = 0;
		// 확인
		while (!heap.isEmpty()) {
			double[] temp = heap.poll();
			int fa = find((int)temp[1]);
			int fb = find((int)temp[2]);
			if (fa == fb)
				continue;
			else if (fa < fb)
				root[fb] = root[fa];
			else
				root[fa] = root[fb];
			answer += temp[0];
		}
		System.out.printf("%.2f\n", answer);
	}
	
	static int find(int num) {
		if (num != root[num])
			return root[num] = find(root[num]);
		return num;
	}
}
