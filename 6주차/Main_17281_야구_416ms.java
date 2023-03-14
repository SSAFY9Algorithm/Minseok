import java.io.*;
import java.util.*;

public class Main_17281_야구_416ms {
	static int[] p = new int[9];
	static int[][] arr;
	static int answer, n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][9];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<9; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		p[0] = 3;
		perm(1, 0);
		System.out.println(answer);
	}
	
	// 조합 생성
	static void perm(int val, int flag) {
		if (val == 9) {
			play();
			return;
		}
		for (int i=0; i<8; i++) {
			if (((1 << i) & flag) == 0) {
				p[val] = i;
				if (i > 2)
					p[val]++;
				perm(val+1, (flag|(1<<i)));
			}
		}
	}
	
	static void play() {
		int idx = 0;
		int cnt = 0;
		int[] temp = new int[9];
		for (int i=0; i<n; i++) {
			for (int j=0; j<9; j++)
				temp[p[j]] = arr[i][j];
			int out = 0;
			int first = 0;
			int second = 0;
			int third = 0;
			while (out < 3) {
				// 아웃
				if (temp[idx] == 0)
					out++;
				// 1루타
				else if (temp[idx] == 1) {
					cnt += third;
					third = second;
					second = first;
					first = 1;
				}
				// 2루타
				else if (temp[idx] == 2) {
					cnt += third + second;
					third = first;
					second = 1;
					first = 0;
				}
				// 3루타
				else if (temp[idx] == 3) {
					cnt += third + second + first;
					third = 1;
					second = 0;
					first = 0;
				}
				else {
					cnt += third + second + first + 1;
					third = 0;
					second = 0;
					first = 0;
				}
				idx = ++idx%9;
			}
		}
		answer = Math.max(answer, cnt);
	}
}

