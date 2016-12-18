import java.util.Scanner;


public class T4
{
	public static void main(String[] args) {
		
		System.out.println("样例输入");
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);

		String str = input.next();
		int n = Integer.parseInt(str);
		int x = 0;
		int[] tmp = new int[n];

		for (int i=0;i<n;i++){
			String str1 = input2.nextLine() ;
			x = Integer.parseInt(str1, 16);
			tmp[i] = x;
		}
		System.out.println("样例输出");
		for (int i=0;i<n;i++){
			String oct_str = Integer.toOctalString(tmp[i]);
			System.out.println(oct_str);
		}
	}

}