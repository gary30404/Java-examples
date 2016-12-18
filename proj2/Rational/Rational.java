public class Rational {

	private int numerator;
	private int denominator;
	
	public Rational(int n, int d){
		int common = gcd (Math.abs(n), d);
		if (n != 0){
         	n = n / common;
         	d = d / common;
		}
		numerator = n;
		denominator = d;
	}

	public Rational add(Rational b){
		Rational a = this;
		int n = (a.numerator * b.denominator) + (b.numerator * a.denominator);
		int d = a.denominator * b.denominator;
		int common = gcd (Math.abs(n), d);
		if (n != 0){
         	n = n / common;
         	d = d / common;
		}
		return new Rational(n, d);
	}

	public Rational sub(Rational b){
		Rational a = this;
		int n = (a.numerator * b.denominator) - (b.numerator * a.denominator);
		int d = a.denominator * b.denominator;
		int common = gcd (Math.abs(n), d);
		if (n != 0){
         	n = n / common;
         	d = d / common;
		}
		return new Rational(n, d);
	}  

	public Rational mul(Rational b){
		Rational a = this;
		int n = a.numerator * b.numerator;
		int d = a.denominator * b.denominator;
		int common = gcd (Math.abs(n), d);
		if (n != 0){
         	n = n / common;
         	d = d / common;
		}
		return new Rational(n, d);
	}  

	public Rational div(Rational b){
		Rational a = this;
		int n = a.numerator * b.denominator;
		int d = a.denominator * b.numerator;
		int common = gcd (Math.abs(n), d);
		if (n != 0){
         	n = n / common;
         	d = d / common;
		}
		return new Rational(n, d);
	}  

	public void printRational(Rational b){
		if(b.numerator<0){
			if(b.denominator == 1){
				System.out.println(b.numerator);
			}
			else{
				b.numerator = (-1) * b.numerator;
				System.out.println("-"+b.numerator+"/"+b.denominator);
			}
		}
		else {
			if(b.denominator == 1){
				System.out.println(b.numerator);
			}
			else{
				System.out.println(b.numerator+"/"+b.denominator);
			}
		}
	}

	public void printReal(Rational b){
		double real = (double)b.numerator/(double)b.denominator;
		System.out.println(real);
	}

    private int gcd (int n1, int n2){
     	while (n1 != n2)
        if (n1 > n2)
        	n1 = n1 - n2;
        else
            n2 = n2 - n1;
      	return n1;
   	}
}