public class Test{
	public static void main(String[] args){
		
		Complex c1 = new Complex(1.0, 2.0);
		Complex c2 = new Complex(3.0, 4.0);
		System.out.println("c1:1+2i");
		System.out.println("c2:3+4i");
		System.out.println("c1+c2:");
		c1.print(c1.add(c2));
		System.out.println("c1-c2:");
		c1.print(c1.sub(c2));
		}

}