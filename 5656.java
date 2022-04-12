import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N, W, H, totalCnt, min;
	static boolean flag;
	static int[] dc = {1,-1,0,0};
	static int[] dr = {0,0,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer stn = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stn.nextToken());
			W = Integer.parseInt(stn.nextToken());
			H = Integer.parseInt(stn.nextToken());
			int[][] map = new int[H][W];
			min = Integer.MAX_VALUE;
			totalCnt = 0;
			flag = false;
			
			// 입력
			for (int i = 0; i < H; i++) {
				stn = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(stn.nextToken());
					if(map[i][j] != 0) totalCnt++;
				}
			}
			
			go(0, totalCnt, map);
			System.out.println("#"+t+" "+min);
		}
	}
	
	// 중복 조합으로 공 떨어뜨리기
	// cnt = 떨어뜨린 공 갯수
	// start = 조합 반복문 시작
	private static void go(int cnt, int total, int[][] map) {
		// N번 떨어뜨렸을 경우
		if(cnt == N) {
			min = Math.min(min, total);
			return;
		}
		// 모든 벽돌이 다 부서졌으면 더 이상 반복x
		if(total == 0) {
			min = 0;
			flag = true;
		}
		if(flag) return;
		int[][] newMap = new int[H][W];
		total = totalCnt;
		copy(map, newMap);
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if(newMap[r][c] == 1) {
					newMap[r][c] = 0;
					totalCnt--;
					go(cnt+1, totalCnt, newMap);
					break;
				}
				else if(newMap[r][c] >= 2) {
					bomb(r, c, newMap);
					down(newMap);
					go(cnt+1, totalCnt, newMap);
					break;
				}
			}
			copy(map, newMap);
			totalCnt = total;
		}
	}
	
	// 2이상인 블록에 대해 폭탄 효과로 제거시키기
	private static void bomb(int r, int c, int[][] map) {
		int range = map[r][c];
		map[r][c] = 0;
		totalCnt--;
		for (int k = 1; k < range; k++) {
			for (int i = 0; i < 4; i++) {
				int nr = r + (dr[i] * k);
				int nc = c + (dc[i] * k);
				if(nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
				if(map[nr][nc] != 0) {
					bomb(nr, nc, map);
				}
			}
		}
	}
	
	// 폭탄 이후 블록 아래로 정리
	private static void down(int[][] map) {
		for (int c = 0; c < W; c++) {
			ArrayList<Integer> list = new ArrayList<>();
			// 세로줄 탐색하면서 0 아닌 숫자 list에 추가
			for (int r = H-1; r >= 0; r--) {
				if(map[r][c] != 0) {
					list.add(map[r][c]);
					map[r][c] = 0;
				}
			}
			
			// 밑에서부터 list에 있는거 차례로 채우기
			int r = H-1;
			for (int a : list) {
				map[r--][c] = a;
			}
		}
	}
	
	// 2차원 배열 복사
	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
}
