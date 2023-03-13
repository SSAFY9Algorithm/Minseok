import java.io.*;
import java.util.*;

public class Main_1717_집합의표현_480ms {
	static int[] root;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		root = new int[n+1];
		for (int i=1; i<n+1; i++)
			root[i] = i;
		// 기본 union-find
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (q == 0) {
				int fa = find(a);
				int fb = find(b);
				if (fa > fb)
					root[fa] = fb;
				else
					root[fb] = fa;
			} else 
				sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb.toString());
	}
	static int find(int num) {
		if (root[num] != num)
			return root[num] = find(root[num]);
		return num;
	}
}