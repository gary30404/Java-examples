import java.text.NumberFormat;
public class Test{
	public static void main(String[] args){

		NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

		SavingsAccount saver1 = new SavingsAccount(2000.0);
		SavingsAccount saver2 = new SavingsAccount(3000.0);

		saver1.modifyInterestRate(4);

		System.out.println("saver1: "+nf.format(saver1.calculateMonthlyInterest()));
		System.out.println("saver2: "+nf.format(saver2.calculateMonthlyInterest()));

		saver1.modifyInterestRate(5);

		System.out.println("saver1: "+nf.format(saver1.calculateMonthlyInterest()));
		System.out.println("saver2: "+nf.format(saver2.calculateMonthlyInterest()));
	}
}