import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0; i<T; i++) {
			int N = sc.nextInt();
			long[] price = new long[N];
			for (int j=0;j<N;j++) {
				price[j] = sc.nextInt();
			}
			/* 가장 뒤를 가장 큰 값으로 지정하고
			 * max 값을 업데이트해 max였으면 수익을 계산하고
			 * max가 아니었으면 새로 업데이트
			 */
			long last = price[N-1];
			long benefit = 0;
			for (int j=N-2;j>=0;j--) {
				if(last > price[j]) {
					benefit = benefit + (last-price[j]);
				}
				else {
					last = price[j];
				}
			}
			System.out.println("#"+(i+1)+" "+benefit);
		}
	}
}
