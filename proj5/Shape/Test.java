public class Test{
	public static void main(String[] args){

		Circle cir = new Circle(5.0);
		System.out.println("Circle r = 5, Area: "+cir.getArea());
		Square sq = new Square(5.0);
		System.out.println("Square a = 5, Area: "+sq.getArea());
	}
}