import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int i;
        for (i=0; i<n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        for (i=n-2; i>=0; i--) {
            if (arr[i] < arr[i+1]) {
                for (int j=n-1; j>i; j--) {
                    if (arr[i] < arr[j]) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        break;
                    }
                }
                Arrays.sort(arr, i+1, n);
                break;
            }
        }
        if (i == -1) {
            System.out.println(-1);
        }
        else {
            for (i=0; i<n; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
}