import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
//		메인 스택
		Stack<Integer> stack = new Stack<>();
//		처음에 문제 잘못읽어서 만들었는데 없애기 귀찮아서 사용
//		pop하면 1부터 나오게 역순으로 넣음
		Stack<Integer> temp = new Stack<>();
		stack.push(0);
		for (int i=n; i>0; i--) {
			temp.push(i);
		}
		while (n-- > 0) {
			int num = Integer.parseInt(br.readLine());
			while (true) {
//				맨위의 원소가 더 작을경우 push
				if (stack.peek() < num) {
					stack.push(temp.pop());
					sb.append("+\n");
				}
//				맨위의 원소가 더 클 경우 불가능
				else if (stack.peek() > num) {
					System.out.println("NO");
					return;
				}
//				같을 경우 pop
				else {
					stack.pop();
					sb.append("-\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}
}