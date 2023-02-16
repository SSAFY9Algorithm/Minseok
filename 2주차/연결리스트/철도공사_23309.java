import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_23309 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

//		arr[고유번호][0] : 고유번호 이전 역
//		arr[고유번호][1] : 고유번호 다음 역
		int[][] arr = new int[1000001][2];
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
//		처음 역 저장
		int first = Integer.parseInt(st.nextToken());
		int prev = first;
		int next = 0;
		
//		이전, 다음 역 정보 저장
		for (int i=1; i<n; i++) {
			next = Integer.parseInt(st.nextToken());
			arr[prev][1] = next;
			arr[next][0] = prev;
			prev = next;
		}
		
//		원형 링크드리스트로 만듦
		arr[first][0] = prev;
		arr[prev][1] = first;

		while (m-- > 0){
			st = new StringTokenizer(br.readLine());
			String q = st.nextToken();
			int i = Integer.parseInt(st.nextToken());
			if (q.charAt(0) == 'B') {
				int j = Integer.parseInt(st.nextToken());
//				BP
				if (q.charAt(1) == 'P') {
					prev = arr[i][0];
					sb.append(prev + "\n");
					arr[prev][1] = j;
					arr[i][0] = j;
					arr[j][0] = prev;
					arr[j][1] = i;
				}
//				BN
				else {
					next = arr[i][1];
					sb.append(next + "\n");
					arr[next][0] = j;
					arr[i][1] = j;
					arr[j][0] = i;
					arr[j][1] = next;
				}
			}
			else {
//				CP
				if (q.charAt(1) == 'P') {
					int del = arr[i][0];
					prev = arr[del][0];
					next = i;
					sb.append(del + "\n");
					arr[prev][1] = next;
					arr[next][0] = prev;
				}
//				CN
				else {
					int del = arr[i][1];
					prev = i;
					next = arr[del][1];
					sb.append(del + "\n");
					arr[prev][1] = next;
					arr[next][0] = prev;
				}
			}
		}
		System.out.println(sb);
	}
}