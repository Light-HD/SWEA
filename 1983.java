import java.util.*;

public class  {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0;i<T;i++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int score[][] = new int[N][3];
			/* 학생 점수 입력 받기 (중간 기말 과제) */
			for (int j=0;j<N;j++) {
				for (int k=0;k<3;k++) {
					score[j][k] = sc.nextInt();
				}
			}
			/* 학생들의 총점 구하기 */
			double total[] = new double[N];
			for (int j=0;j<N;j++) {
				total[j] = score[j][0]*0.35 + score[j][1]*0.45 + score[j][2]*0.2;
			}
			/* 본인보다 높은 학생 수 세기 */
			int count = 1; //본인 등수 알기 위해 1부터 시작
			for (int j=0;j<N;j++) {
				if (total[j] > total[K-1]) count++;
			}
			String grade = "";
			int a = (int) Math.ceil(count/(N/10.0)); // 총 학생 10명 비율로 맞춰서 등급 계산
			switch(a) {
			case 1 :
				grade = "A+";
				break;
			case 2 :
				grade = "A0";
				break;
			case 3 :
				grade = "A-";
				break;
			case 4 :
				grade = "B+";
				break;
			case 5 :
				grade = "B0";
				break;
			case 6 :
				grade = "B-";
				break;
			case 7 :
				grade = "C+";
				break;
			case 8 :
				grade = "C0";
				break;
			case 9 :
				grade = "C-";
				break;
			case 10 :
				grade = "D0";
				break;
			}
			System.out.println("#"+(i+1)+" "+grade);
		}
	}
}
