import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_14713 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
//		Queue를 여러개 가지고 있는 ArrayList 생성
		List<Queue<String>> queues = new ArrayList<Queue<String>>();
		
		int queueSize = 0;
		for (int i=0; i<n; i++) {
//			Queue 문자열 Split하여 저장
			Queue<String> queue = new LinkedList<>();
			for (String s : br.readLine().split(" "))
				queue.add(s);
			queues.add(queue);
			queueSize += queue.size();
		}
		String[] answer = br.readLine().split(" ");
		
//		탐험가가 앵무새보다 단어 적게 말했을 때 고려
		if (queueSize != answer.length) {
			System.out.println("Impossible");
			return;
		}
		
//		한 문장을 읽어가며 앵무새의 각각 문장의 첫번째 단어를 비교
		for (String s : answer) {
			boolean flag = false;
			for (Queue<String> queue : queues) {
//				같으면 제거
				if (s.equals(queue.peek())) {
					queue.poll();
					flag = true;
					break;
				}
			}
//			앵무새의 모든 문장의 첫번째 단어랑 매치되지 않으면 불가능 
			if (!flag) {
				System.out.println("Impossible");
				return;
			}
		}
		System.out.println("Possible");
	}
}
