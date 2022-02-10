import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;


public class Solution {

	static ArrayList<String> list = new ArrayList<String>(); //후위 표기식 배열
	static int j; 
	static String[] nums;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 10; i++) {
			int n = Integer.parseInt(br.readLine());
			j = 0; // j 초기화
			// { 3+(4+5)*6+7 }, { (9+(5*2+1)*5)*8 }
			nums = br.readLine().split(""); // 중위 표기식 배열
			// 후위 표기식 변환 구현
			dfs();
			System.out.println(list);
			Stack<Integer> stack = new Stack<Integer>();
			for(int j=0; j<list.size(); j++) {
				// 후위 표기식 계산기 구현
				// 연산자 일 때
				if(list.get(j).equals("+") || list.get(j).equals("-") || list.get(j).equals("*") || list.get(j).equals("/")){
					int b = stack.pop();
					int a = stack.pop();
					if(list.get(j).equals("+")) {
						stack.push(a+b);
					}else if(list.get(j).equals("-")) {
						stack.push(a-b);				
					}else if(list.get(j).equals("*")) {
						stack.push(a*b);				
					}else if(list.get(j).equals("/")) {
						stack.push(a/b);
					}
				}
				// 숫자 일 때
				else {
					stack.push(Integer.parseInt(list.get(j))); // 숫자 배열과 알파벳 매칭
				}
			}
			System.out.println("#"+(i)+" "+stack.pop());
		}	
	}

	public static void dfs() {
		Stack<String> cStack = new Stack<String>();
		while(true) {
			// 종료 조건
			if(j == nums.length) {
				int l = cStack.size();
				for(int i=0; i<l; i++) {
					list.add(cStack.pop());
				}
				break;
			}
			// ( 가 나온 경우 재귀 호출
			if(nums[j].equals("(")) {
				j++;
				dfs();
			}
			// ) 가 나온 경우 모두 pop하고 return
			else if(nums[j].equals(")")) {
				int l = cStack.size();
				for(int i=0; i<l; i++) {
					list.add(cStack.pop());
				}
				return;
			}
			// 연산자가 나온 경우
			else if(nums[j].equals("+") || nums[j].equals("*")) {
				// 연산자 스택이 비어있는 경우 스택에 push
				if(cStack.isEmpty()) {
					cStack.push(nums[j]); // push
				}
				// 우선순위가 더 낮은 경우 모두 pop하고 현재 연산자 push
				else if(cStack.peek().equals("*") && nums[j].equals("+")) {
					int l = cStack.size();
					for(int k=0; k<l; k++) {
						list.add(cStack.pop()); // 모두 pop
					}
					cStack.push(nums[j]); // 현재 연산자 push
				}
				// 우선순위가 더 높은 경우 연산자 스택에 push
				else {
					cStack.push(nums[j]); // push
				}
			}
			// 숫자가 나온 경우
			else {
				// list에 add
				list.add(nums[j]);
			}
			j++;
		}
	}
}
