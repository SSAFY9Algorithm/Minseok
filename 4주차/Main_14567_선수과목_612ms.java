package temp;

import java.io.*;
import java.util.*;

public class Main_14567_선수과목_612ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] deg = new int[n+1];
        Deque<Integer>[] arr = new ArrayDeque[n+1];
        Deque<Integer> queue = new ArrayDeque<>();
        int[] answer = new int[n+1];
        for (int i=1; i<n+1; i++)
            arr[i] = new ArrayDeque<>();
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());
            arr[pre].add(post);
            deg[post]++;
        }
        for (int i=1; i<n+1; i++) {
            if (deg[i] == 0) {
                queue.addLast(i);
                answer[i] = 1;
            }
        }
        while (!queue.isEmpty()) {
            int idx = queue.pollFirst();
            while (!arr[idx].isEmpty()) {
                int next = arr[idx].pollFirst();
                deg[next]--;
                if (deg[next] == 0) {
                    queue.addLast(next);
                    answer[next] = answer[idx] + 1;
                }
            }
        }
        for (int i=1; i<n+1; i++)
            sb.append(answer[i]).append(" ");
        System.out.println(sb);

    }
}
