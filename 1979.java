package my_practice;

import java.util.*;

public class swea {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i=0;i<T;i++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int p[][] = new int[N][N];
			/* 퍼즐 값 입력 받기 */
			for (int j=0;j<N;j++) {
				for (int k=0;k<N;k++) {
					p[j][k] = sc.nextInt();
				}
			}
			int count_a[]= new int[N];
			int total = 0;
			/* 가로 길이 재기 */
			for (int j=0;j<N;j++) {
				for (int k=0;k<N;k++) {
					if(p[j][k] == 1) count_a[j]++;
					if(p[j][k] == 0) {
						if(count_a[j] == K) total++; // 0이 나왔을 때의 가로 count 개수 판단
						count_a[j] = 0;
					}
				}
				if(count_a[j] == K) total++; // 0이 안나오고 끝났을 때 (마지막)
			}
			int count_b[] = new int[N];
			/* 세로 길이 재기 */
			for (int j=0;j<N;j++) {
				for (int k=0;k<N;k++) {
					if(p[k][j] == 1) count_b[j]++;
					if(p[k][j] == 0) {
						if(count_b[j] == K) total++;
						count_b[j] = 0;
					}
				}
				if(count_b[j] == K) total++;
			}
			System.out.println("#"+(i+1)+" "+total);
		}
	}
}
