import java.io.*;
public class Test{
	public static void main(String[] args){
		Lab lab = new Lab();
		int t = 0;
		String s = null;
		double x = 0;
		try{
			System.out.println("exp(2.5,c), c: ");
			t = lab.readKeyboardInt();
			System.out.println("exp: "+lab.exp(2.5, t));
			System.out.println("approach(x), x: ");
			s = lab.readKeyboardLine();
			x = Double.parseDouble(s);
			System.out.println("approach: "+lab.approach(x));
		} catch (MyException e){
		} catch (IOException e2){
		} 
	}
}