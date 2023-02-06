import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] cases = new String[9*8*7];
        int[] check = new int[9*8*7];
        int idx = 0;

        // 중복 숫자 제거
        for (int i=1; i<10; i++) {
            for (int j=1; j<10; j++) {
                if (i==j) continue;
                for (int k=1; k<10; k++) {
                    if (i==k || j==k) continue;
                    cases[idx++] = Integer.toString(i*100 + j*10 + k);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int t=0; t<n; t++) {
            st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            for (int i=0; i<cases.length; i++) {
                int s = 0;
                int b = 0;
                // 스트라이크, 볼 체크
                for (int j=0; j<3; j++) {
                    for (int k=0; k<3; k++) {
                        if (cases[i].charAt(j) == num.charAt(k)) {
                            if (j==k) s++;
                            else b++;
                        }
                    }
                }
                if (strike == s && ball == b)
                    check[i]++;
            }
        }
        int answer = 0;
        for (int x : check) {
            if (x == n)
                answer++;
        }
        System.out.println(answer);
    }
}