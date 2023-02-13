import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            for (int i=0; i<n; i++) {
                String part = br.readLine().split(" ")[1];
                map.merge(part, 1, Integer::sum);
            }
            int answer = 1;
            for (Map.Entry<String, Integer> e : map.entrySet())
                answer *= e.getValue() + 1;
            System.out.println(answer - 1);
        }
    }
}