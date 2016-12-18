public class Vehicles{
	public String brand, color;
	public boolean r;
	Vehicles(String b, String c){
		brand = b;
		color = c;
		r = false;
	}

	void run(){
		if(r)
			System.out.println("我已經開動了");
	}

	void showInfo(){
		System.out.println("brand: " + brand);
		System.out.println("color: " + color);
	}
}

class Car extends Vehicles{
	int seats;
	Car(String b, String c){
		super(b, c);
	}
	void showCar(){
		System.out.println("brand: " + brand);
		System.out.println("color: " + color);
		System.out.println("seats: " + seats);
	}
}

class Truck extends Vehicles{
	float load;
	Truck(String b, String c){
		super(b, c);
	}
	void showTruck(){
		System.out.println("brand: " + brand);
		System.out.println("color: " + color);
		System.out.println("load: " + load + " kg");
	}
}