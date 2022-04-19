import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int sum[];
	static long memo[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스

		// 일의자리 1~n까지의 값 미리 계산
		sum = new int[10];
		sum[1] = 1;
		for (int i = 2; i < 10; i++) {
			sum[i] = sum[i-1] + i;
		}
		
		// 1~9, 1~99, 1~999 값 미리 계산
		memo = new long[16];
		memo[1] = sum[9];
		//0~99 = 20 * memo[1], 0~999 = 300 * memo[1] ...
		for (int i = 2; i < memo.length; i++) {
			memo[i] =  memo[1] * (long)Math.pow(10, i-1) + memo[i-1]*10;
		}
		
		// 테케
		for (int t = 1; t <= T; t++) {
			StringTokenizer stn = new StringTokenizer(br.readLine());
			long A  = Long.parseLong(stn.nextToken());
			long B = Long.parseLong(stn.nextToken());
			
			// 1~B까지의 합 구한다음 1~A-1까지의 합을 빼줌
			System.out.println("#"+t+" "+(solve(B)-solve(A-1)));
			
			
		}
	}
	
	public static long solve(long num) {
		// 0보다 작으면 0 리턴
        if(num<0) return 0;
        // 10보다 작으면 sum[num] 리턴
		if(num<10) return sum[(int)num];
		
		String str = String.valueOf(num);
		int z = str.length()-1; // 자리수 -1
		int x = str.charAt(0) - '0'; // 가장 높은자리 숫자로 변환
		long y = Long.parseLong(str.substring(1)); // x를 제외한 나머지 수
		
		// 3345이면 sum[2]*1000 + 2*(0~999의 합) + 2*(345+1) + 345에 대한 부분 합
		return sum[x-1]* (long) Math.pow(10, z) + x*memo[z] + x*(y+1) + solve(y);
	}
}
