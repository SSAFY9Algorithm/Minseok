import java.io.*;
import java.util.*;

public class Main_16437_양구출작전 {
    static Deque<Integer>[] list;
    //  +양 vs -늑대
    static long[] life;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        life = new long[n+1];
        list = new ArrayDeque[n+1];
        visit = new boolean[n+1];
        for (int i=0; i<n+1; i++) {
            list[i] = new ArrayDeque<>();
        }
        for (int i=2; i<n+1; i++) {
            st = new StringTokenizer(br.readLine());
            char animal = st.nextToken().charAt(0);
            long many = Long.parseLong(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            life[i] = animal == 'W' ? -many : many;
            list[i].add(j);
            list[j].add(i);
        }
        System.out.println(dfs(1));
    }
    static long dfs(int start) {
        long sum = life[start];
        visit[start] = true;
        while (!list[start].isEmpty()) {
            int next = list[start].pollFirst();
            if (!visit[next]){
                long sub = dfs(next);
                if (sub > 0)
                    sum += sub;
            }
        }
//      마지막이 늑대인 경우 체크
        return Math.max(sum, 0);
    }
}