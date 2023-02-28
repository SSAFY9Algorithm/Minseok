import java.io.*;
import java.util.*;

public class Main_1260_DFS와BFS_232ms {
    static StringBuilder sb = new StringBuilder();
    static boolean[][] arr;
    static boolean[] bst;
    static boolean[] dst;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        arr = new boolean[n+1][n+1];
        bst = new boolean[n+1];
        dst = new boolean[n+1];
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = true;
            arr[b][a] = true;
        }
        DFS(v);
        sb.append("\n");
        BFS(v);
        System.out.print(sb.toString());
    }

    static void BFS(int idx) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(idx);
        bst[idx] = true;
        while (!queue.isEmpty()) {
            int num = queue.pollFirst();
            sb.append(num).append(" ");
            for (int i=1; i<n+1; i++) {
                if (arr[num][i] && !bst[i]) {
                    queue.addLast(i);
                    bst[i] = true;
                }
            }
        }
        sb.append("\n");
    }

    static void DFS(int idx) {
        sb.append(idx).append(" ");
        dst[idx] = true;
        for (int i=1; i<n+1; i++) {
            if (arr[idx][i] && !dst[i])
                DFS(i);
        }
    }
}