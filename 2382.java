import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	public static class Group{
		private int r, c, k, d; // 위치, 미생물 수, 이동 방향

		public Group(int r, int c, int k, int d) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.d = d;
		}
		
	}
	
	public static final int S = 1, H = 2, J = 3, W = 4; // 상 하 좌 우
	public static int[] dr = {0, -1, 1, 0, 0};
	public static int[] dc = {0, 0, 0, -1, 1};
	public static int N, M, K, map[][], Ccnt;
	public static ArrayList<Group> gList;
	public static void main(String[] args) throws IOException {
		//입력부입니다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer stn = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stn.nextToken());
			M = Integer.parseInt(stn.nextToken());
			K = Integer.parseInt(stn.nextToken());
			map = new int[N][N];
			
			gList = new ArrayList<>();
			gList.add(null);
			for (int i = 1; i <= K; i++) {
				stn = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(stn.nextToken());
				int c = Integer.parseInt(stn.nextToken());
				int k = Integer.parseInt(stn.nextToken());
				int d = Integer.parseInt(stn.nextToken());
				gList.add(new Group(r, c, k, d));
				map[r][c] = i;
			}
			
			Ccnt = gList.size();
			move(0);
			System.out.println("#"+t+" "+total());
		}

	}
	
	
	public static void move(int time) {
		while(true) {
			if(time == M) return;
			int[][] newMap = new int[N][N];
			int[][] max = new int[N][N];
			for (int i = 1; i < Ccnt; i++) {
				Group g = gList.get(i);
				
				if(g.k == 0) continue;
				
				int nr = g.r + dr[g.d];
				int nc = g.c + dc[g.d];
				
				g.r = nr;
				g.c = nc;
				
				// 약물 구역에 들어왔을 경우
				if(nr == 0 || nc == 0 || nr == N-1 || nc == N-1) {
					newMap[nr][nc] = i; // 이동하고
					die(gList.get(i), newMap); // 절반 죽이고 반대 방향
				}
				// 다른 군집이 있을 경우
				else if(newMap[nr][nc] != 0) {
					if(max[nr][nc] < g.k) {
						max[nr][nc] = g.k;
						sum(i, newMap[nr][nc]);
						newMap[nr][nc] = i;
					}else {
						sum(newMap[nr][nc], i); // 합치기	
					}
					
					
				// 아무 군집도 없을 경우
				}else {
					max[nr][nc] = g.k;
					newMap[nr][nc] = i;
				}
			}
			copy(newMap, map);
			time++;
		}
	}
	
	public static void die(Group g, int[][] newMap) {
		g.k = g.k / 2;
		if(g.k == 0) {
			newMap[g.r][g.c] = 0;
			return;
		}
		if(g.d % 2 == 0) {
			g.d--;
		}else {
			g.d++;
		}
	}
	
	public static void sum(int a, int b) {
		Group A = gList.get(a);
		Group B = gList.get(b);
		
		A.k += B.k;
		B.k = 0;
	}
	
	public static int total() {
		int res = 0;
		
		for (int i = 1; i < Ccnt; i++) {
			res += gList.get(i).k;
		}
		
		return res;
	}
	public static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

}
