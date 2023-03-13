import java.io.*;

public class Main {
    static char[] s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++) {
            s = br.readLine().toCharArray();
            sb.append(tp(-1, s.length, 0) + "\n");
        }
        System.out.print(sb.toString());
    }
    static int tp(int l, int r, int v) {
        // 좌 우 비교
        while (l++ < r--)
            if (s[l] != s[r])
                // 일치하지 않으면 value 1 증가, 이미 1인경우 2 return
                return v == 0 ? Math.min(tp(l, r+1, v+1), tp(l-1, r, v+1)) : 2;
        return v;
    }
}