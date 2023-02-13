import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1068 {
//	ArrayList 배열 50개 생성
	static List<Integer>[] childs = new ArrayList[50];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

//		부모의 정보를 저장하는 배열
		int[] parent = new int[n];
		st = new StringTokenizer(br.readLine());
		int root = -1;
		
		for (int i=0; i<50; i++)
//			자식의 정보를 저장하는 ArrayList 배열
			childs[i] = new ArrayList<Integer>();
		
		for (int i=0; i<n; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p == -1) {
				root = i;
				continue;
			}
//			i번의 부모 저장
			parent[i] = p;
//			부모 배열에 i 정보 추가
			childs[p].add(i);
			
		}
		
		st = new StringTokenizer(br.readLine());
		int rm = Integer.parseInt(st.nextToken());
		
//		루트 삭제시 0
		if (rm == root) {
			System.out.println(0);
			return;
		}
		
//		지울 노드의 부모
		int p = parent[rm];
		for (int i=0; i < childs[p].size(); i++) {
//			부모 노드와 연결 끊음
			if (childs[p].get(i) == rm)
				childs[p].remove(i);
		}
		System.out.println(find(root));
	}
	
	public static int find(int num) {
		int answer = 0;
		boolean flag = false;
//		자식이 있을 경우 탐색
		for (int x : childs[num]) {
			answer += find(x);
			flag = true;
		}
//		자식이 없는 경우 + 1
		if (!flag)
			return 1;
		return answer;
	}
}