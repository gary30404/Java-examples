public class Test{
	public static void main(String[] args){
		
		Rational R1 = new Rational(4, 3);
		Rational R2 = new Rational(3, 2);
		System.out.println("R1:");
		R1.printRational(R1);
		System.out.println("R2:");
		R2.printRational(R2);
		System.out.println("R1 real:");
		R1.printReal(R1);
		System.out.println("R2 real:");
		R2.printReal(R2);
		System.out.println("R1+R2:");
		R1.printRational(R1.add(R2));
		System.out.println("R1-R2:");
		R1.printRational(R1.sub(R2));
		System.out.println("R1*R2:");
		R1.printRational(R1.mul(R2));
		System.out.println("R1/R2:");
		R1.printRational(R1.div(R2));
		}
}