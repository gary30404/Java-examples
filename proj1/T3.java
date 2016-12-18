import java.util.Scanner;


public class T3
{
	public static int gcd(int m, int n){
		return n == 0 ? m : gcd(n, m%n);
	}
	public static int lcm(int m, int n){
		return m * n / gcd(m, n);
	}
	public static void main(String[] args) {
		
		System.out.println("样例输入");
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		String[] num = str.split(" ");

		int x = Integer.parseInt(num[0]);
		int y = Integer.parseInt(num[1]);
		int z = Integer.parseInt(num[2]);
		
		if (z == 1){
			System.out.println("样例输出");
			System.out.println(x+y);
		}
		else if (z == 2){
			System.out.println("样例输出");
			System.out.println(x-y);
		}
		else if (z == 3){
			System.out.println("样例输出");
			System.out.println(x*y);
		}
		else if (z == 4){
			System.out.println("样例输出");
			System.out.println(x/y);
		}
		else if (z == 5){
			System.out.println("样例输出");
			System.out.println(x%y);
		}
		else if (z == 6){
			System.out.println("样例输出");
			System.out.println(gcd(x,y));
		}
		else if (z == 7){
			System.out.println("样例输出");
			System.out.println(lcm(x,y));
		}
	}

}