import java.io.*;
import java.util.*;

public class Main_9663_NQueen_3948ms {
    static int n;
    static int answer = 0;
    static int[] arr;
    static int[] brr;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        brr = new int[n];

        nq(0);
        System.out.println(answer);
    }


    static void nq(int val) {
        if (val == n) {
            answer++;
            return;
        }
ct:		for (int i=0; i<n; i++) {
            int num = 1<<i;
            if (brr[i] != 0)
                continue;
            for (int j=1; val-j>=0; j++) {
                if ((arr[val-j] & num<<j) == num<<j)
                    continue ct;
                if (num>>j > 0 && (arr[val-j] & num>>j) == num>>j)
                    continue ct;
            }
            for (int j=1; val+j<n; j++) {
                if ((arr[val+j] & num<<j) == num<<j)
                    continue ct;
                if (num>>j > 0 && (arr[val+j] & num>>j) == num>>j)
                    continue ct;
            }
            arr[val] = num;
            brr[i] = 1;
            nq(val+1);
            arr[val] = 0;
            brr[i] = 0;

        }
    }
}