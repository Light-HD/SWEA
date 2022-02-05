import java.util.*;

public class Solution {

	public static int[][] rotate(int[][] arr, int n) {
		int [][] arr90 = new int[n][n];
		for(int i=0; i<n; i++) {
			int k=n-1;
			for (int j=0; j<n; j++) {
				arr90[i][j] = arr[k][i];
				k--;
			}
		}
		return arr90;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0; i<T; i++) {
			System.out.println("#"+(i+1));
			int n = sc.nextInt();
			int[][] arr = new int[n][n];
			/* 행렬 입력 받기 */
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					arr[j][k] = sc.nextInt();
				}
			}
			int[][] arr90 = rotate(arr, n);
			int[][] arr180 = rotate(arr90, n);
			int[][] arr270 = rotate(arr180, n);
			
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					System.out.print(arr90[j][k]);
				}
				System.out.print(" ");
				for(int k=0; k<n; k++) {
					System.out.print(arr180[j][k]);
				}
				System.out.print(" ");
				for(int k=0; k<n; k++) {
					System.out.print(arr270[j][k]);
				}
				System.out.println(" ");
			}
		}
	}
}
//
