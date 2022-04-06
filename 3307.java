import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int res = 0;
			int N = Integer.parseInt(br.readLine());
			StringTokenizer stn = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(stn.nextToken());
			}
			
			int[] dp = new int[N];
			Arrays.fill(dp, 1);
			//dp[N]에서 N에 해당하는 위치에서의 최대 값 저장
			for (int i = 1; i < N; i++) {
				// 해당 dp를 끝으로 하는 최적해 구하기
				// 앞의 dp들을 확인하여 그 dp 뒤에 서는 경우 중 젤 큰 값
				for(int j = i-1; j >= 0; j--) {
					// 앞에 값이 더 작거나 같으면 해당 값의 뒤에 서기
					if(arr[j] <= arr[i]) {
						dp[i] = Math.max(dp[j]+1, dp[i]);
					}
				}
				res = Math.max(res, dp[i]);
			}
			System.out.println("#"+t+" "+res);
		}
	}
}
