public class Test{
	public static void main(String[] args){
		
		HugeInteger H1 = new HugeInteger();
		HugeInteger H2 = new HugeInteger();
		H1.inputHugeInteger("10000");
		H2.inputHugeInteger("50000");
		System.out.println("H1:" + H1.outputHugeInteger());
		System.out.println("H2:" + H2.outputHugeInteger());
		System.out.println("H1+H2:" + H1.addHugeIntegers(H2).outputHugeInteger());
		System.out.println("H1-H2:" + H1.substractHugeIntegers(H2).outputHugeInteger());
		System.out.println("isEqualTo : " + H1.isEqualTo(H2));
		System.out.println("isNotEqualTo : " + H1.isNotEqualTo(H2));	
		System.out.println("isGreaterThan : " + H1.isGreaterThan(H2));	
		System.out.println("isLessThan : " + H1.isLessThan(H2));	
		System.out.println("isGreaterThanOrEqualTo : " + H1.isGreaterThanOrEqualTo(H2));	
		System.out.println("isLessThanOrEqualTo : " + H1.isLessThanOrEqualTo(H2));	
		System.out.println("---------------------");
		HugeInteger H3 = new HugeInteger();
		HugeInteger H4 = new HugeInteger();
		H3.inputHugeInteger("-50000");
		H4.inputHugeInteger("-10000");
		System.out.println("H3:" + H3.outputHugeInteger());
		System.out.println("H4:" + H4.outputHugeInteger());
		System.out.println("H3+H4:" + H3.addHugeIntegers(H4).outputHugeInteger());
		System.out.println("H3-H4:" + H3.substractHugeIntegers(H4).outputHugeInteger());
		System.out.println("isEqualTo : " + H3.isEqualTo(H4));
		System.out.println("isNotEqualTo : " + H3.isNotEqualTo(H4));	
		System.out.println("isGreaterThan : " + H3.isGreaterThan(H4));	
		System.out.println("isLessThan : " + H3.isLessThan(H4));	
		System.out.println("isGreaterThanOrEqualTo : " + H3.isGreaterThanOrEqualTo(H4));	
		System.out.println("isLessThanOrEqualTo : " + H3.isLessThanOrEqualTo(H4));
		System.out.println("---------------------");
		HugeInteger H5 = new HugeInteger();
		HugeInteger H6 = new HugeInteger();
		H5.inputHugeInteger("1");
		H6.inputHugeInteger("9999999999999999999999999999999999999999");
		System.out.println("H5:" + H5.outputHugeInteger());
		System.out.println("H6:" + H6.outputHugeInteger());
		System.out.println("H5+H6:" + H5.addHugeIntegers(H6).outputHugeInteger());
		System.out.println("H5-H6:" + H5.substractHugeIntegers(H6).outputHugeInteger());
		System.out.println("isEqualTo : " + H5.isEqualTo(H6));
		System.out.println("isNotEqualTo : " + H5.isNotEqualTo(H6));	
		System.out.println("isGreaterThan : " + H5.isGreaterThan(H6));	
		System.out.println("isLessThan : " + H5.isLessThan(H6));	
		System.out.println("isGreaterThanOrEqualTo : " + H5.isGreaterThanOrEqualTo(H6));	
		System.out.println("isLessThanOrEqualTo : " + H5.isLessThanOrEqualTo(H6));		
	}
}