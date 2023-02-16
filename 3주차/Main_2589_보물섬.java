import java.io.*;
import java.util.*;

// 전체 탐색하여 BFS 돌릴까 하다가 특정 위치에서만 돌리기로 함
// 다행히 시간적 이득은 있는 것 같으나 코드 개길어짐
public class Main_2589_보물섬 {
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new String[n];
        for (int i=0; i<n; i++)
            arr[i] = br.readLine();

        // 가장 먼 육지가 될 후보를 찾기위한 첫번째 Queue
        Deque<Integer> queue = new ArrayDeque<>();
        // 가장 먼 육지가 될 후보 (꼭지점?)
        Deque<Integer> island = new ArrayDeque<>();
        // 처음 BFS 할 때는 true/false 로 중복체크만 막음
        boolean[][] visit = new boolean[n][m];

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (arr[i].charAt(j) == 'L' && !visit[i][j]) {
                    queue.addLast(i);
                    queue.addLast(j);
                    visit[i][j] = true;
                    while (!queue.isEmpty()) {
                        int y = queue.pollFirst();
                        int x = queue.pollFirst();

//						더이상 다음 곳으로 진행할 수 없는지 확인하는 flag
                        boolean flag = true;

//						BFS
                        for (int k=0; k<4; k++) {
                            int yy = y + dy[k];
                            int xx = x + dx[k];
                            if (0 <= yy && yy < n && 0 <= xx && xx < m && arr[yy].charAt(xx) == 'L' && !visit[yy][xx]) {
                                visit[yy][xx] = true;
                                queue.addLast(yy);
                                queue.addLast(xx);
                                flag = false;
                            }
                        }
//						더이상 진행할 수 없는 녀석을 후보로 넣음
                        if (flag) {
                            island.addLast(y);
                            island.addLast(x);
                        }
                    }
                }
            }
        }
//		가장 긴 거리 체크용
        int max = 0;

//		후보들 하나하나 체크
        while (!island.isEmpty()) {

//			int 2차원 배열로 거리 저장
            int[][] check = new int[n][m];
            int y = island.pollFirst();
            int x = island.pollFirst();
            check[y][x] = 1;
            queue.addLast(y);
            queue.addLast(x);
            while (!queue.isEmpty()) {
                y = queue.pollFirst();
                x = queue.pollFirst();
                for (int i=0; i<4; i++) {
                    int yy = y + dy[i];
                    int xx = x + dx[i];
                    if (0 <= yy && yy < n && 0 <= xx && xx < m && arr[yy].charAt(xx) == 'L' && check[yy][xx] == 0) {
                        queue.addLast(yy);
                        queue.addLast(xx);
                        check[yy][xx] = check[y][x] + 1;
//						가장 긴 거리면 변경
                        max = Math.max(max, check[yy][xx]);
                    }
                }
            }
        }
        System.out.println(max-1);
    }
}