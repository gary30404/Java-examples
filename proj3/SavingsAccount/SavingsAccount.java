public class SavingsAccount{

	private static double annualInterestRate = 0; // %
	private double savingsBalance; //存款餘額

	public SavingsAccount(double  savings){
		savingsBalance  = savings;
	}

	public double calculateMonthlyInterest(){
		savingsBalance = savingsBalance + savingsBalance*(annualInterestRate/100)/12;
		return savingsBalance;
	}

	public void modifyInterestRate(double input){
		System.out.println("annualInterestRate: " + input);
		annualInterestRate = input;
	}
}