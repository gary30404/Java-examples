public class Test{
	public static void main(String[] args){

		Car c = new Car("Benz", "Black");
		c.r = true;
		c.seats = 4;
		c.showCar();
		c.run();

		Truck t = new Truck("Chery", "Blue");
		t.r = true;
		t.load = 5000;
		t.showTruck();
		t.run();

	}
}