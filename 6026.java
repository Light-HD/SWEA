import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int M, N;
	static long fac[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer stn = new StringTokenizer(br.readLine());
			M = Integer.parseInt(stn.nextToken());
			N = Integer.parseInt(stn.nextToken());
			long p = 1000000007;
			
			// n까지의 팩토리얼 미리 fac 배열에 저장해두기
			fac = new long[N+1];
			fac[0] = 1;
			
			for (int i = 1; i <= N; i++) {
				fac[i] = fac[i-1] * i % p;
			}
			
			// N -> M 전사 함수
			// MCM * M^N - MCM-1 * (M-1)^N + MCM-2 * (M-2)^N ...
			long res = 0;
			for (int i = 0; i < M; i++) {
				long temp = combination(M, M-i, p) * power(M-i, N, p) % p;
				if(i % 2 == 0) {
					res = (res + temp + p) % p;
				}else {
					res = (res - temp + p) % p;
				}
			}

			System.out.println("#"+t+" "+res);
		}
	}
	
	
	// n!/((n-r)!r!) 구하기
	private static long combination(int n, int r, long p) {
		if(n == r || r == 0) {
			return 1;
		}
		long k = power(fac[n-r], p-2, p);
		long j = power(fac[r], p-2, p);
		long a = k * j % p;
		return a * fac[n] % p;
	}
	
	// x x^2, x^3, x^4 ... 를 소수로 모듈러 연산시 일정한 패턴으로 순환되는 것을 이용
	private static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		
		while(y > 0) {
			if(y % 2 == 1) {
				res = (res * x) % p;
			}
			y = y >> 1;
			x = (x * x) % p;
		}
		return res;
	}
}
