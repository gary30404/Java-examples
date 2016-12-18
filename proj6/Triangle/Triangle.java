public class Triangle{
	
	public float a;
	public float b;
	public float c;

	Triangle(float x, float y, float z){
		a = x;
		b = y;
		c = z;
	}

	public void showInfo() throws NotTriangleException{

		if (a + b <= c)
			throw new NotTriangleException();
		else if (a + c <= b)
			throw new NotTriangleException();
		else if (b + c <= a)
			throw new NotTriangleException();
		else
			System.out.println("x: "+a+", y: "+b+", z: "+c);
	}

	public void getArea() throws NotTriangleException{

		if (a + b <= c)
			throw new NotTriangleException();
		else if (a + c <= b)
			throw new NotTriangleException();
		else if (b + c <= a)
			throw new NotTriangleException();
		else {
			float s = 0;
			s = (a+b+c)/2;
			s = (float) Math.sqrt((s*(s-a)*(s-b)*(s-c)));
			System.out.println("Triangle Area: "+s);
		}
	}
}

class NotTriangleException extends Exception {
		public  NotTriangleException(){ }
}