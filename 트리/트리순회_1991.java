import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1991 {
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		arr = new int[n][2];
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
//			int 변환하여 index로 사용
			int root = cvt(st.nextToken().charAt(0));
			int left = cvt(st.nextToken().charAt(0));
			int right = cvt(st.nextToken().charAt(0));
			arr[root][0] = left;
			arr[root][1] = right;
		}
		System.out.println(preOrder(0) + "\n" + inOrder(0) + "\n" + postOrder(0));
	}
//	 char -> int
	public static int cvt(char c) {
		if (c == '.') 
			return -1;
		return (int)(c-65);
	}
//	 int -> char
	public static char cvt(int i) {
		return (char)(i+65);
	}
	
	public static String preOrder(int num) {
		if (num == -1)
			return "";
		return cvt(num) + preOrder(arr[num][0]) + preOrder(arr[num][1]);
	}
	
	public static String inOrder(int num) {
		if (num == -1)
			return "";
		return inOrder(arr[num][0]) + cvt(num) + inOrder(arr[num][1]);
	}
	
	public static String postOrder(int num) {
		if (num == -1)
			return "";
		return postOrder(arr[num][0]) + postOrder(arr[num][1]) + cvt(num);
	}
}