public class Test{
	public static void main(String[] args){

		System.out.println("Employee");
		Employee e = new Employee();
		e.SetName("Linda");
		e.SetAge(25);
		e.SetGender("Female");
		e.SetSalary(60000);
		e.showEmployeeInfo();

		System.out.println("Manager");
		Manager m = new Manager();
		m.SetName("Gary");
		m.SetAge(28);
		m.SetGender("Male");
		m.SetSalary(120000);
		m.SetTeamSize(4);
		m.SetPosition("Vice President");
		m.showManagerInfo();
	}
}