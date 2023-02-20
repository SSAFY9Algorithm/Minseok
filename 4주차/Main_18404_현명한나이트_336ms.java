import java.io.*;
import java.util.*;

public class Main_18404_현명한나이트_336ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};


        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] visit = new int[n][n];
        int[][] knight = new int[m][2];

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken()) - 1;
        int x = Integer.parseInt(st.nextToken()) - 1;

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            knight[i][0] = Integer.parseInt(st.nextToken()) - 1;
            knight[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(y);
        queue.addLast(x);
        visit[y][x] = 1;
        while (!queue.isEmpty()) {
            y = queue.pollFirst();
            x = queue.pollFirst();
            for (int i=0; i<8; i++) {
                int yy = y + dy[i];
                int xx = x + dx[i];
                if (0 <= yy && yy < n && 0 <= xx && xx < n && visit[yy][xx] == 0) {
                    queue.addLast(yy);
                    queue.addLast(xx);
                    visit[yy][xx] = visit[y][x] + 1;
                }
            }
        }
        for (int[] k : knight)
            sb.append(visit[k[0]][k[1]]-1).append(" ");

        System.out.println(sb.toString());
    }
}