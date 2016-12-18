import java.util.Scanner;

public class T1
{
	public static void main(String[] args) {
		
		System.out.println("输入样例:");
		Scanner input = new Scanner(System.in);
		String str = input.next();
		int n = Integer.parseInt(str);
		int tmp = 0;
		for (int i=1;i<n;i++){
			if (n%i == 0){
				tmp = tmp  + i;
			}
		}

		if (tmp == n){
			System.out.println("输出样例:");
			System.out.println("yes");
		}
		else{
			System.out.println("输出样例:");
			System.out.println("no");
		}
	}

}