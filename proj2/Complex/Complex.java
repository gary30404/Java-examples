public class Complex {

	private double realPart;
	private double imaginaryPart;
	
	public Complex(double r, double i){
		realPart = r;
		imaginaryPart = i;
	}

	public Complex add(Complex b){
		Complex a = this;
		double r = a.realPart + b.realPart;
		double i = a.imaginaryPart + b.imaginaryPart;
		return new Complex(r, i);
	}

	public Complex sub(Complex b){
		Complex a = this;
		double r = a.realPart - b.realPart;
		double i = a.imaginaryPart - b.imaginaryPart;
		return new Complex(r, i);
	}

	public void print(Complex b){
		if(b.imaginaryPart<0){
			b.imaginaryPart = (-1.0) * b.imaginaryPart;
			System.out.println((int)b.realPart+"-"+(int)b.imaginaryPart+"i");
		}
		else {
			System.out.println((int)b.realPart+"+"+(int)b.imaginaryPart+"i");
		}
	}
}