import java.io.*;
import java.util.*;

public class Main_12026_BOJ거리_136ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		int[] visit = new int[n];
		Arrays.fill(visit, 1000001);
		visit[0] = 0;
		Map<Character, Integer> d = new TreeMap<>();
		d.put('B', 0);
		d.put('O', 1);
		d.put('J', 2);
		for (int i=0; i<n; i++)
			for (int j=i+1; j<n; j++)
				if ((d.get(s.charAt(i))+1)%3 == d.get(s.charAt(j)))
					visit[j] = Math.min(visit[j], visit[i] + (j-i) * (j-i));
		System.out.println(visit[n-1] != 1000001 ? visit[n-1] : -1);
	}
} 
