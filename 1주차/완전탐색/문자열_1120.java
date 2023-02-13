import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s1 = st.nextToken();
		String s2 = st.nextToken();
		int df = s2.length() - s1.length();
		int answer = s2.length();
		for (int i=0; i<=df; i++) {
			int cnt = 0;
			for (int j=0; j<s1.length(); j++) {
				if (s1.charAt(j) == s2.charAt(i+j))
					cnt++;
			}
			answer = Math.min(answer, s2.length() - (cnt + df));
		}
		System.out.println(answer);
	}
}