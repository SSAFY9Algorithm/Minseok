import java.io.*;
import java.util.*;

public class Main_1005_ACMCraft_832ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(st.nextToken());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] build = new int[n+1];
            Deque<Integer>[] arr = new ArrayDeque[n+1];
            Deque<Integer> queue = new ArrayDeque<>();
            int[] deg = new int[n+1];
            int[] time = new int[n+1];
            for (int i=1; i<n+1; i++)
                arr[i] = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());
            for (int i=1; i<n+1; i++)
                build[i] = Integer.parseInt(st.nextToken());

            for (int i=0; i<k; i++) {
                st = new StringTokenizer(br.readLine());
                int pre = Integer.parseInt(st.nextToken());
                int post = Integer.parseInt(st.nextToken());
                arr[pre].addLast(post);
                deg[post]++;
            }
            for (int i=1; i<n+1; i++)
                if (deg[i] == 0) {
                    queue.addLast(i);
                    time[i] = build[i];
                }
            while (!queue.isEmpty()) {
                int idx = queue.pollFirst();
                while (!arr[idx].isEmpty()) {
                    int next = arr[idx].pollFirst();
                    time[next] = Math.max(time[next], time[idx] + build[next]);
                    deg[next]--;
                    if (deg[next] == 0) {
                        queue.addLast(next);
                    }
                }
            }

            sb.append(time[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.print(sb.toString());
    }

}