public class Test{
	public static void main(String[] args){

		try{
			System.out.println("Triangle1:");
			Triangle tri = new Triangle(3, 3, 3);
			tri.showInfo();
			tri.getArea();
			System.out.println("Triangle2:");
			Triangle tri2 = new Triangle(1, 2, 3);
			tri2.showInfo();
			tri2.getArea();
		} catch(NotTriangleException e) {
			System.out.println("x, y, and z are unable to be triangle!");
		}

	}
}