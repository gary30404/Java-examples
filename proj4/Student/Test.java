public class Test{
	public static void main(String[] args){

		Undergraduate s1 = new Undergraduate("Linda", 20, "bachelor");
		s1.specialty = "computer vision";
		s1.show();
		Graduate s2 = new Graduate("Gary", 23, "master");
		s2.direction = "deep learning";
		s2.show();

	}
}