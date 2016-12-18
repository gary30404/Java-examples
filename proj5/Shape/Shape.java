public abstract interface Shape{
     public abstract double getArea();
     double PI = 3.14;
} 

class Circle implements Shape{
	double radius;
	Circle(double r){
		radius = r;
	}
	public double getArea(){
		return radius*radius*PI;
	}
}

class Square implements Shape{
	double side;
	Square(double a){
		side = a;
	}
	public double getArea(){
		return side*side;
	}
}