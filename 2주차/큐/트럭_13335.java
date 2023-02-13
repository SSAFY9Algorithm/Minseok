import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13335 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
//		시간 마다 다리 위 무게 배열 : time[1] == 1초일때 다리 위 무게
		int[] time = new int[1000001];
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int p = 1;
		for (int x : arr) {
//			하중 안넘는 시간 찾기
			while (time[p] + x > l)
				p++;
//			다리 길이 지나가는 시간만큼 배열에 트럭 무게 추가
			for (int i=0; i<w; i++)
				time[i+p] += x;
//			다음 트럭이 1초 후 들어올 수 있으니 증가
			p++;
		}
		int idx = 1;
		while (time[idx] != 0)
			idx++;
		System.out.println(idx);
	}
}