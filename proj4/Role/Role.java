public class Role{
	protected String name, gender;
	protected int age;
	Role(){
		name = null;
		age = 0;
		gender = null;
	}
	public void SetName(String n){
		name = n;
	}
	public void SetAge(int a){
		age = a;
	}
	public void SetGender(String g){
		gender = g;
	}
	public String GetName(){
		return name;
	}
	public int GetAge(){
		return age;
	}
	public String GetGender(){
		return gender;
	}
	void showInfo(){
		System.out.println("Name: " + GetName());
		System.out.println("Age: " + GetAge());
		System.out.println("Gender: " + GetGender());
	}
}

class Employee extends Role{
	protected int salary;
	Employee(){
		super();
		salary = 0;
	}
	public void SetSalary(int s){
		salary = s;
	}
	public int GetSalary(){
		return salary;
	}
	void showEmployeeInfo (){
		System.out.println("Name: " + GetName());
		System.out.println("Age: " + GetAge());
		System.out.println("Gender: " + GetGender());
		System.out.println("Salary: " + GetSalary());
	}
}

class Manager extends Employee{
	private int teamSize;
	private String position;
	Manager(){
		super();
		teamSize = 0;
		position = null;
	}
	public void SetTeamSize(int t){
		teamSize = t;
	}
	public void SetPosition(String p){
		position = p;
	}
	public int GetTeamSize(){
		return teamSize;
	}
	public String GetPosition(){
		return position;
	}
	void showManagerInfo (){
		System.out.println("Name: " + GetName());
		System.out.println("Age: " + GetAge());
		System.out.println("Gender: " + GetGender());
		System.out.println("Salary: " + GetSalary());
		System.out.println("TeamSize: " + GetTeamSize());
		System.out.println("Position: " + GetPosition());
	}
}