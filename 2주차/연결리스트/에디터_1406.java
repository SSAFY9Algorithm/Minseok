import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1406 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		String s = st.nextToken();
//		커서 왼쪽의 원소 저장
		Stack<Character> stack = new Stack<>();
//		커서 오른쪽의 원소 저장
		Stack<Character> temp = new Stack<>();
		
		for (int i=0; i<s.length(); i++)
			stack.push(s.charAt(i));
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			String m1 = st.nextToken();
			switch (m1) {
//			왼쪽으로 커서 옮김
			case "L":
				if (!stack.isEmpty())
					temp.push(stack.pop());
				break;
//			오른쪽으로 커서 옮김
			case "D":
				if (!temp.isEmpty())
					stack.push(temp.pop());
				break;
//			커서 왼쪽 문자 삭제
			case "B":
				if (!stack.isEmpty())
					stack.pop();
				break;
//			커서 왼쪽에 문자 추가
			case "P":
				stack.push(st.nextToken().charAt(0));
				break;
			}
		}
//		순서대로 출력하기 위해 옮김
		while (!stack.isEmpty())
			temp.push(stack.pop());
		while (!temp.isEmpty())
			sb.append(temp.pop());
		System.out.println(sb);
	}
}
