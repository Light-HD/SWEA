package mine;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0; i<T; i++) {
			boolean test = true;
			int[][] row = new int[9][9];
			int[][] col = new int[9][9];
			int[][] rect = new int[9][9];
			int[][] mat = new int[9][9];
			/* 스도쿠(9*9) 입력받기 */
			for(int j=0; j<9; j++) {
				for(int k=0; k<9; k++) {
					mat[j][k] = sc.nextInt();
					/* 스도쿠 가로 검증 */
					if(row[j][mat[j][k]-1] > 0) {
						test = false;
					}else {
						row[j][mat[j][k]-1]++;
					}
				}
			}
			/* 스도쿠 세로 검증 */
			if(test) {
				for(int j=0; j<9; j++) {
					for(int k=0; k<9; k++) {
						if(col[j][mat[k][j]-1] > 0 ) {
							test = false;
							break;
						}else {
							col[j][mat[k][j]-1]++;
						}
					}
					if(!(test)) break;
				}
			}
			/* 박스 검증 */
			if(test) {
				for(int j=0; j<9; j++) {
					for(int k=0; k<9; k++) {
						if(rect[j][mat[(k/3)+(j/3)*3][(k%3)+((j%3)*3)]-1] > 0) {
							test = false;
						}else {
							rect[j][mat[(k/3)+(j/3)*3][(k%3)+((j%3)*3)]-1]++;
						}
					}
					if(!(test)) break;
				}
			}
			/* 검증 결과 출력 */
			System.out.print("#"+(i+1)+" ");
			if(test) System.out.println("1");
			else System.out.println("0");
		}
	}
}

