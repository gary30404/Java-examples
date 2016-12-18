public class Student{
	
	public String name, degree;
	public int age;

	Student(String n, int a, String d){
		name = n;
		age = a;
		degree = d;
	}
}

class Undergraduate extends Student{
	
	String specialty;
	
	Undergraduate(String n, int a, String d){
		super(n, a, d);
	}

	void show(){
		System.out.println("Undergraduate");
		System.out.println("name: " + name);
		System.out.println("age: " + age);
		System.out.println("degree: " + degree);
		System.out.println("specialty: " + specialty);
	}
}

class Graduate extends Student{
	
	String direction;
	
	Graduate(String n, int a, String d){
		super(n, a, d);
	}

	void show(){
		System.out.println("Undergraduate");
		System.out.println("name: " + name);
		System.out.println("age: " + age);
		System.out.println("degree: " + degree);
		System.out.println("direction: " + direction);
	}
}