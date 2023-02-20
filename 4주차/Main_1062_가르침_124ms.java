import java.io.*;
import java.util.*;

public class Main_1062_가르침_124ms {
    static Map<Character, Integer> map = new HashMap<>();
    static int[] comb = new int[400000];
    static int n, k;
    static int size = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] bits = new int[n];

        init();
        for (int i=0; i<n; i++)
            bits[i] = sti(br.readLine());


        if (k < 5) {
            System.out.println(0);
            return;
        }
        int answer = 0;
        for (int i=0; i<size; i++) {
            int cnt = 0;
            for (int j=0; j<n; j++) {
                // 문자열 비트가 비트 조합에 포함되면 +1
                if ((comb[i] & bits[j]) == bits[j])
                    cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
    // 문자열을 비트로 바꿈
    static int sti(String s) {
        int sum = 0;
        for (int i=0; i<s.length(); i++)
            sum |= map.get(s.charAt(i));
        return sum;
    }
    // a n t i c 제외한 알파벳 1bit~21bit 자리수 할당
    static void init() {
        int i = 1;
        for (char a='a'; a<='z'; a++) {
            if (a == 'a' || a == 'n' || a == 't' || a == 'i' || a == 'c')
                map.put(a, 0);
            else {
                map.put(a, i);
                i <<= 1;
            }
        }
        comb(-1, 0, 0);
    }
    // 가능한 비트 조합 생성
    static void comb(int idx, int val, int sum) {
        if (val == k-5) {
            comb[size++] = sum;
            return;
        }
        for (int i=idx+1; i<21; i++) {
            comb(i, val+1, sum + (1<<i));
        }
    }
}