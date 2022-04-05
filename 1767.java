import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	public static class Core{
		private int r, c;

		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int[] dc = {1, 0, -1, 0};
	static int[] dr = {0, 1, 0, -1};
	static int N, min, max, totalCnt, map[][];
	static ArrayList<Core> coreList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			StringTokenizer stn = null;
			
			// 입력
			coreList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				stn = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stn.nextToken());
					// 경계에 있는 코어는 패스
					if((i==0 || j ==0 || i == N-1 || j == N-1) && map[i][j] == 1) continue;
					// 그 외의 코어는 List에 추가
					if(map[i][j] == 1) {
						coreList.add(new Core(i, j));
					}
				}
			}
			totalCnt = coreList.size();
			min = Integer.MAX_VALUE;
			max = 0;
			go(0, 0);
			System.out.println("#"+t+" "+min);
		}
	}
	// 부분 집합에 따른 모든 경우의 수 해보고 min 값 저장
	private static void go(int index, int cCnt) {
		// 종료 조건
		if(index == totalCnt) {
			int res = getLength();
			// 현재 코어 연결된 것이 더 많으면 무조건 그 값으로 설정
			if(max < cCnt) {
				max = cCnt;
				min = res;
			// 연결된 코어 수가 같으면 더 짧은 값으로
			}else if(max == cCnt) {
				min = Math.min(min, res);
			}
			return;
		}
		Core core = coreList.get(index);
		int r = core.r;
		int c = core.c;
		// 현재 코어에 대해서 4방향 연결
		for (int i = 0; i < 4; i++) {
			if(!isAvailable(r, c, i)) continue;
			setStatus(r, c, i, 2);
			go(index+1, cCnt+1);
			setStatus(r, c, i, 0);
		}
		// 연결 안하는 경우
		go(index+1, cCnt);
	}
	
	// 해당 방향으로 연결가능한지 확인
	private static boolean isAvailable(int r, int c, int d) {
		int nr = r;
		int nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;
			if(map[nr][nc] >= 1) return false;
		}
		return true;
	}
	
	// 해당 방향으로 s 값으로 모두 설정
	private static void setStatus(int r, int c, int d, int s) {
		int nr = r;
		int nc = c;
		while(true) {
			// 현재 코어 다음 부터 set
			nr += dr[d];
			nc += dc[d];
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;
			map[nr][nc] = s;		
		}
	}
	
	// 전선 길이
	private static int getLength() {
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 2) res++;
			}
		}
		return res;
	}
}
