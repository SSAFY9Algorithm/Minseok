import java.io.*;
import java.util.*;

public class Main_17829_222풀링 {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(rec(n, 0, 0));
    }

    static int rec(int n, int y, int x) {
        int[] sub = new int[4];
        if (n == 1)
            return arr[y][x];

//		네칸으로 나누기
        for (int i=0; i<2; i++)
            for (int j=0; j<2; j++)
                sub[i*2+j] = rec(n/2, n/2*i + y, n/2*j + x);

        Arrays.sort(sub);
        return sub[2];
    }
}