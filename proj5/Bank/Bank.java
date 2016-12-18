public abstract class Bank{
	
	protected double oneyearRate = 0.0178;
	protected double oneyearNationaldebt = 0.0198;
	protected double interestRate = 0.0078;

	public abstract double count();
	public abstract void show();
}

class oneyearRateAccount extends Bank{

	private int year;
	private double money;
	oneyearRateAccount(int y, double m){
		year = y;
		money = m;
	}
	public double count(){
		for (int i=1; i<=year; i++){	
			money = money * (1.0 + oneyearRate);
		}
		return money;

	}
	public void show(){
		System.out.println("總金額: "+money);
	}

}

class oneyearNationaldebtAccount extends Bank{

	private int year;
	private double money;
	oneyearNationaldebtAccount(int y, double m){
		year = y;
		money = m;
	}
	public double count(){
		for (int i=1; i<=year; i++){	
			money = money * (1.0 + oneyearNationaldebt);
		}
		return money;

	}
	public void show(){
		System.out.println("總金額: "+money);
	}

}

class interestAccount extends Bank{

	private int year;
	private double money;
	interestAccount(int y, double m){
		year = y;
		money = m;
	}
	public double count(){
		for (int i=1; i<=year; i++){	
			money = money * (1.0 + interestRate);
		}
		return money;

	}
	public void show(){
		System.out.println("總金額: "+money);
	}

}
