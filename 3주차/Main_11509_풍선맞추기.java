import java.io.*;
import java.util.*;

public class Main_11509_풍선맞추기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

//      최대 높이 배열 (1칸 위 확인 위해 n+1)
        int[] check = new int[10000002];
        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for (int i=0; i<n; i++) {
            int num = Integer.parseInt(st.nextToken());
            ++check[num];
//          한칸 위 존재 O -> 한칸 위 없애고, cnt 증가 X
            if (check[num+1] != 0)
                --check[num+1];
//          한칸 위 존재 X -> cnt 증가
            else
                ++cnt;
        }
        System.out.println(cnt);
    }
}