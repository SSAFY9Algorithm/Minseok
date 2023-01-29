import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = n + m;
        HashSet<Integer> a = new HashSet<>();
        HashSet<Integer> b = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++)
            a.add(Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++)
            b.add(Integer.parseInt(st.nextToken()));

        for (int x : a) {
            if (b.contains(x)) answer -= 2;
        }
        System.out.println(answer);
    }
}
