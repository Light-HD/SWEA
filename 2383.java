import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	public static class Person implements Comparable<Person>{
		private int r, c, time, downCnt, status; // 위치, 계단까지 도착시간, 계단 내려간 칸수, 상태

		public Person(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		// 초기 설정 함수
		public void init() {
			time = downCnt = 0;
			status = M;
		}

		@Override
		public int compareTo(Person o) {
			return time - o.time;
		}
		
	}
	
	public static class Stair{
		private int r, c, len; // 위치, 길이

		public Stair(int r, int c, int len) {
			super();
			this.r = r;
			this.c = c;
			this.len = len;
		}
		
	}
	
	public static final int M=1, W=2, D=3, C=4; // move wait down complete 
	public static int N, map[][], res, min, ct;
	public static boolean ck[];
	public static ArrayList<Person> pList;
	public static ArrayList<Stair> sList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N]; // 맵
			pList = new ArrayList<>(); // 사람 배열리스트
			sList = new ArrayList<>(); // 계단 배열리스트
			min = Integer.MAX_VALUE;
			
			// map 입력 및 계단 사람 입력
			StringTokenizer stn = null;
			for (int r = 0; r < N; r++) {
				stn = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(stn.nextToken());
					if(map[r][c] == 1) {
						pList.add(new Person(r, c));
					}else if(map[r][c] >= 2) {
						sList.add(new Stair(r, c, map[r][c]));
					}
				}
			}
			
			ct = pList.size(); // 사람 수
			ck = new boolean[ct]; // 부분집합 나누기
			subSet(0); // 부분 집합
			System.out.println("#"+t+" "+min);
		}
    }
	
	public static void subSet(int cnt) {
		if(cnt == ct) {
			makeList();
			return;
		}
		
		ck[cnt] = true;
		subSet(cnt+1);
		ck[cnt] = false;
		subSet(cnt+1);
		
	}
	
	// 1번계단 2번계단 그룹 나누기
	public static void makeList() {
		ArrayList<Person> aList = new ArrayList<>();
		ArrayList<Person> bList = new ArrayList<>();
		for (int i = 0; i < ct; i++) {
			Person p = pList.get(i);
			p.init();
			// 1번 계단
			if(ck[i]) {
				p.time = Math.abs(p.r - sList.get(0).r) + Math.abs(p.c - sList.get(0).c);
				aList.add(p);
			// 2번 계단
			}else {
				p.time = Math.abs(p.r - sList.get(1).r) + Math.abs(p.c - sList.get(1).c);
				bList.add(p);
			}
		}
		int res = go(aList, bList);
		min = Math.min(min, res);
	}
	
	// 1번 계단, 2번 계단 중 더 오래 걸린 쪽 리턴
	public static int go(ArrayList<Person> aList, ArrayList<Person> bList) {
		int atime = 0, btime = 0;
		
		if(aList.size() > 0) atime = goDown(aList, sList.get(0).len);
		if(bList.size() > 0) btime = goDown(bList, sList.get(1).len);
		
		return atime > btime? atime : btime ;
	}
	
	// 계단 내려가기
	public static int goDown(ArrayList<Person> list, int height) {
		
		Collections.sort(list); // 정렬
		
		int time = list.get(0).time; // 첫 사람이 계단에 도착한 시점부터 시작
		int size = list.size();
		int ingCnt = 0, cCnt = 0; // 내려가고 있는 사람 수, 다 내려간 사람 수
		
		while(true) {
			for (int i = 0; i < size; i++) {
				Person p = list.get(i);
				// 다 내려온 사람이면 패스
				if(p.status == C)  continue;
				
				// 계단에 도착했으면
				if(p.time == time) {
					p.status = W; // 대기 상태로 변경
				// 대기 상태이고 계단 공간 있으면
				}else if(p.status == W && ingCnt < 3) {
					p.status = D; // 내려가는 상태로 변경
					p.downCnt = 1; // 1부터 시작
					ingCnt++; // 내려가는 사람 수 증가
				// 내려가고 있는 사람이면
				}else if(p.status == D) {
					// 덜 내려갔으면
					if(p.downCnt < height) {
						p.downCnt++; // 마저 내려가고
					// 다 내려갔으면
					}else {
						p.status = C; // 내려간 상태로 변경
						ingCnt--; // 계단 공간 1개 비워주기
						cCnt++; // 다 내려간 사람 수 추가
					}
				}
			}
			// 모든 사람 다 내려갔으면 종료
			if(cCnt == size) break;
			time++; // 시간 1초 증가
		}
		
		return time;
	}
}
