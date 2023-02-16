import java.io.*;

// 이거 진짜 왜틀린지 모르겠음 디버그좀 도와줘
// 심지어 파이썬으로 푼 문젠데
public class Main_1992_쿼드트리 {
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new String[n];
        for (int i=0; i<n; i++)
            arr[i] = br.readLine();
        System.out.println(hungry(n, 0, 0));

    }
    static String hungry(int n, int y, int x) {
        if (n == 1)
            return arr[y].charAt(x) + "";

        StringBuilder ret = new StringBuilder();
        for (int i=0; i<2; i++)
            for (int j=0; j<2; j++)
                ret.append(hungry(n/2, n/2*i + y, n/2*j + x));

        for (int i=0; i<3; i++)
            if (ret.charAt(i) != ret.charAt(i+1))
                return "("+ret+")";
        return ret.charAt(0) + "";
    }
}

