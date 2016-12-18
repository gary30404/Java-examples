import java.util.Scanner;


public class T2
{
	public static void main(String[] args) {
		
		System.out.println("样例输入");
		Scanner input = new Scanner(System.in);
		String str = input.next();
		int time = Integer.parseInt(str);

		int hr, min, sec;
		hr = time / 3600;
		min = (time%3600)/60;
		sec  =(time%3600)%60;

		System.out.println("样例输出");
		System.out.println(hr+":"+min+":"+sec);
	}

}