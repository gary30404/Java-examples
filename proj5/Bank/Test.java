public class Test{
	public static void main(String[] args){

		oneyearRateAccount oneyearRate = new oneyearRateAccount(1,10000);
		oneyearRate.count();
		oneyearRate.show();
		oneyearNationaldebtAccount oneyearNationaldebt = new oneyearNationaldebtAccount(1,10000);
		oneyearNationaldebt.count();
		oneyearNationaldebt.show();
		interestAccount interest = new interestAccount(1,10000);
		interest.count();
		interest.show();
	}
}