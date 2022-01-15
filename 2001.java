import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0;i<T;i++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int fly[][] = new int[N][N];
			/* 파리 영역 입력 받기 */
			for(int j=0;j<N;j++) {
				for (int k=0;k<N;k++) {
					fly[j][k] = sc.nextInt();
				}
			}
			int max = 0; //max 초기값
			/* 파리채 크기를 파리 영역(N*N)에 반복하는 수 = N-M+1 */
			for(int j=0;j<N-M+1;j++) {
				for (int k=0;k<N-M+1;k++) {
					int sum = 0;
					/* 파리채 크기(M*M)만큼 처음부터 합산 구하기 */
					for(int a=j;a<j+M;a++) {
						for (int b=k;b<k+M;b++) {
							sum = sum + fly[a][b];
						}
					}
					if(max < sum) {
						max = sum;
					}
				}
			}
			System.out.println("#"+(i+1)+" "+max);
		}
	}
}
