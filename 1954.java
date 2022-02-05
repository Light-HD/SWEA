
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i=0; i<T; i++) {
			int n = sc.nextInt();
			int[][] a = new int[n][n];
			int[] dx = {1,0,-1,0};
			int[] dy = {0,1,0,-1};
			int x = 0;
			int y = 0;
			int cnt = 1;
			int div = 0;
			for(int j=0; j<n*n; j++) {
				a[y][x] = cnt;
				cnt++;
				if(x+dx[div] == n || y+dy[div] == n || x+dx[div] == -1 || y+dy[div] == -1 || a[y+dy[div]][x+dx[div]] != 0) {
					if(div == 3) {
						div = 0;
					}else {
						div++;
					}
				}
				x = x+dx[div];
				y = y+dy[div];
			}
			System.out.println("#"+(i+1));
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					System.out.print(a[j][k]+" ");
				}
				System.out.println();
			}
		}
	}
}
//
