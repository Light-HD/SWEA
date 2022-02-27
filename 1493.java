import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer stn = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(stn.nextToken());
			int q = Integer.parseInt(stn.nextToken());
			int n = 1;
			int px, py;
      // p의 위치 구하기
			while(true) {
      // 계차수열 공식 an = a1 + n-1시그마bn
				if(1 + (n*(n-1))/2 <= p && (n+1)*n/2 >= p) {
					int diff = p-(1+(n*(n-1)/2));
					px = 1+diff;
					py = n-diff;
					break;
				}
				n++;
			}
			int qx, qy;
			n = 1;
      // q의 위치 구하기
			while(true) {
				if(1 + (n*(n-1))/2 <= q && (n+1)*n/2 >= q) {
					int diff = q-(1 +(n*(n-1))/2);
					qx = 1+diff;
					qy = n-diff;
					break;
				}
				n++;
			}
      // 위치에서 더해주기
			int x = px+qx;
			int y = py+qy;
			n = x+y-1;
      // 다시 역으로 구하기
			int res = 1+(n*(n-1))/2 + (x-1);
			System.out.println("#"+t+" "+res);
		}
	}
}
