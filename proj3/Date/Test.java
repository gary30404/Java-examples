public class Test{
	public static void main(String[] args){
		System.out.println("Date1: ");
		Date d1 = new Date("07","06","2016");
		Date d2 = new Date("June", 6, 2016);
		Date d3 = new Date(2016, 7, 6);
		System.out.println("Date2: ");
		
		Date d4 = new Date("02","04","2013");
		Date d5 = new Date("February", 4, 2013);
		Date d6 = new Date(2013, 2, 4);

		System.out.println("dateDistance :"+d3.dateDistance(d6));
		
		System.out.println("Date3: 1999 年 2 月 29 日");
		Date d7 = new Date(1999,2,29);
		
	
	}
}