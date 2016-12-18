class Person{
	public void introduceSelf(){
		System.out.println("I am a person.");
	}
}

class Student extends Person{
	public void introduceSelf(){
		System.out.println("I am a student.");
	}
}
class Emplyee extends Person{
	public void introduceSelf(){
		System.out.println("I am an emplyee.");
	}
}
class Retired extends Person{
	public void introduceSelf(){
		System.out.println("I am a retired.");
	}
}

public class Mainclass{
	public static void intro(Person p){
		p.introduceSelf();
	}
	public static void main(String[] args){
		Person person = new Person();
		Student student = new Student();
		Emplyee emplyee = new Emplyee();
		Retired retired = new Retired();
		System.out.println("單個物件:");
		person.introduceSelf();
		student.introduceSelf();
		emplyee.introduceSelf();
		retired.introduceSelf();
		System.out.println("陣列化:");
		Person[] people = {new Person(), new Student(), new Emplyee(), new Retired()};
		for(int i=0; i<4; i++){
			people[i].introduceSelf();
		}
		System.out.println("參數化:");
		intro(person);
		intro(student);
		intro(emplyee);
		intro(retired);
	}
}