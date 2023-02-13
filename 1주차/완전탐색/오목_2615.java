import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[19][19];
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int minY = -1;
        int minX = -1;
        boolean flag = false;

        for (int i=0; i<19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<19; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        br:		for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                if (arr[i][j] == 0) continue;
                for (int k=0; k<4; k++) {
                    int line = 1;
                    minY = i;
                    minX = j;
                    // 한 방향 확인
                    for (int p=1; p<=6; p++) {
                        int y = i + dy[k] * p;
                        int x = j + dx[k] * p;
                        if (y >= 19 || x < 0 || x >= 19 ||arr[y][x] != arr[i][j]) break;
                        if (x < minX) {
                            minY = y;
                            minX = x;
                        }
                        line++;
                    }
                    // 반대 방향 확인
                    for (int p=1; p<=6; p++) {
                        int y = i + dy[k+4] * p;
                        int x = j + dx[k+4] * p;
                        if (y < 0 || x < 0 || x >= 19 || arr[y][x] != arr[i][j]) break;
                        if (x < minX) {
                            minY = y;
                            minX = x;
                        }
                        line++;
                    }
                    if (line == 5) {
                        flag = true;
                        break br;
                    }
                }
            }
        }
        if (flag) {
            System.out.println(arr[minY][minX]);
            System.out.println((minY+1) + " " + (minX+1));
        }
        else
            System.out.println(0);
    }
}