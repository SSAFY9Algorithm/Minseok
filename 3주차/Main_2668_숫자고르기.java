import java.io.*;
import java.util.*;

public class Main_2668_숫자고르기 {
    static int[] arr;
    static int[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        int cnt = 0;
        arr = new int[n+1];
        visit = new int[n+1];
        String ans = "";
        for (int i=1; i<n+1; i++)
            arr[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        for (int i=1; i<n+1; i++) {
            find(i, i);
        }
        Arrays.sort(visit);
//      사이클 저장되어있으면 StringBuilder에 추가
        for (int i=1; i<n+1; i++) {
            if (visit[i] == 0)
                continue;
            sb.append(visit[i]+"\n");
            ++cnt;
        }
        System.out.print(cnt + "\n" + sb.toString());
    }

    // 사이클 찾기
    static boolean find(int idx, int first) {
        if (visit[idx] != 0)
            return false;
//      사이클 찾으면 idx 저장
        visit[idx] = idx;
        if (arr[idx] == first)
            return true;
        boolean check = find(arr[idx], first);
//      사이클 못찾으면 0 저장
        if (!check)
            visit[idx] = 0;
        return check;

    }
}
