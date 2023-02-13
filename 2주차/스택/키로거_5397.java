import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_5397 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
//			도대체 왜 계속 시간초과 뜨나 했더니 StringBuilder 안써서 시간초과남
//			어이없음
			StringBuilder sb = new StringBuilder();
			String s = br.readLine();
			
			Stack<Character> stack = new Stack<Character>();
//			커서 이동으로 pop 한 것을 담아둘 배열
			Stack<Character> temp = new Stack<Character>();

			for (int i=0; i<s.length(); i++) {
				char c = s.charAt(i);
//				왼쪽으로 옮기면 임시 스택에 넣음
				if (c == '<') {
					if (!stack.isEmpty())
						temp.push(stack.pop());
				}
//				오른쪽으로 옮기면 임시 스택에서 다시 가져옴
				else if (c == '>') {
					if (!temp.isEmpty())
						stack.push(temp.pop());
				}
//				제거
				else if (c == '-') {
					if (!stack.isEmpty())
						stack.pop();
				}
//				추가
				else
					stack.push(c);
			}
//			순서대로 뽑기 위해 임시 스택에 넣음
//			forEach 사용시 시간 더 오래 걸릴 것 같음
			while (!stack.isEmpty())
				temp.push(stack.pop());
//			System.out.print() 반복시 시간 느림
			while (!temp.isEmpty())
				sb.append(temp.pop());
			System.out.println(sb);
		}
	}
}