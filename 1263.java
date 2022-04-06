import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer stn = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stn.nextToken());
			int[][] map = new int[N][N];
			// 입력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stn.nextToken());
				}
			}
			
			//dp[i][j] 에는 i에서 j로 가는 최적 경로를 저장
			int[][] dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dp[i][j] = map[i][j];
					if(i != j && dp[i][j] == 0) dp[i][j] = 9999;
				}
			}
			
			//경유지 k개 고려했을 때의 i,j 최적 경로
			for (int k = 1; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(i == j || k == j || i == k) continue;
						// i j로 가는 직통 경로가 경유해서 가는 것보다 느리면 업데이트
						if(dp[i][j] > dp[i][k]+dp[k][j]) {
							dp[i][j] = dp[i][k]+dp[k][j];
						}
					}
				}				
			}
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int cc = 0;
				for (int j = 0; j < N; j++) {
					cc += dp[i][j]; 
				}
				min = Math.min(min, cc);
			}
			
			System.out.println("#"+t+" "+min);
		}
	}
}
