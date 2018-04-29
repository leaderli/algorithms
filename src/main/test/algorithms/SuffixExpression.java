package algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SuffixExpression {
	private Map<String, execution> executionMap = new HashMap<>();

	{
		executionMap.put("*", (a, b) -> a * b);
		executionMap.put("/", (a, b) -> a / b);
		executionMap.put("-", (a, b) -> a - b);
		executionMap.put("+", (a, b) -> a + b);
	}

	private Stack<String> lexical(String expression) {
		Stack<String> lexical = new Stack<>();
		char[] chars = expression.toCharArray();
		for (char c : chars) {
			String result;
			if (c < '0' || c > '9' || lexical.isEmpty()) {
				lexical.push(c + "");
				continue;
			}

			result = lexical.pop();
			if (result.matches("\\d")) {
				lexical.push(result + c);
			} else {
				lexical.push(result);
				lexical.push(c + "");
			}
		}
		return lexical;
	}

	private Stack<String> getSuffixExpression(Stack<String> lexical) {
		Stack<String> expression = new Stack<>();
		Stack<String> operation = new Stack<>();
		/*
		  0. 从左到右遍历词法组
		  1. 数字直接入表达式栈
		  2. '(' 压入操作栈
		  3. ')' 弹出操作栈直到碰到'(',入表达式栈(丢弃'(')，
		  4.  操作栈顶元素为空或'(',压入
		  5.  操作栈顶元素o1不为空，若当前操作o2优先级大于o1，压入o2
		  6.  操作栈顶元素o1不为空，若当前操作o2优先级小于或等于o1，弹出o1，压入o2
		  7.  最后弹出所有操作栈元素，压入表达式栈
		 */
		for (String s : lexical) {
			System.out.print("e" + expression);
			System.out.println("===o" + operation);
			if (s.matches("\\d+")) {
				expression.push(s);
				continue;
			}
			if ("(".equals(s)) {
				operation.push(s);
				continue;
			}
			if (")".equals(s)) {
				boolean flag = true;
				while (flag) {
					String o = operation.pop();
					if ("(".equals(o)) {
						flag = false;
					} else {
						expression.push(o);
					}
				}

				continue;
			}
			if (operation.isEmpty()) {
				operation.push(s);
				continue;
			}
			String o = operation.peek();
			if (o.equals("(")) {
				operation.push(s);
				continue;
			}
			if (getLevel(s) > getLevel(o)) {
				operation.push(s);
			} else {
				o = operation.pop();
				operation.push(s);
				expression.push(o);
			}

		}
		for (String s : operation) {
			expression.push(s);
		}
		return expression;
	}

	private int getLevel(String s) {
		if ("+".equals(s) || "-".equals(s))
			return 0;
		return 1;
	}

	private int execute(Stack<String> suffix) {
		Stack<Integer> ex = new Stack<>();
		for (String s : suffix) {
			if (s.matches("\\d+")) {
				ex.push(Integer.valueOf(s));
			} else {
				int b = ex.pop();
				int a = ex.pop();
				System.out.println(s);
				ex.push(executionMap.get(s).execute(a, b));
			}
		}
		return ex.pop();
	}

	public static void main(String[] args) {
		System.out.println("1213");
	}
	@Test
	public void test1() {
		System.out.println("1111");
		Stack<String> lexical = lexical("(3*4+5)-8");
		Stack<String> lexical2 = lexical("(3*4+5)-8/8");
		System.out.println(lexical);
		System.out.println(lexical2);
		Stack<String> suffix = getSuffixExpression(lexical);
		Stack<String> suffix2 = getSuffixExpression(lexical2);
		System.out.println(suffix);
		System.out.println(suffix2);
		System.out.println(execute(suffix));
		System.out.println(execute(suffix2));
	}

	private interface execution {
		int execute(int a, int b);
	}
}
