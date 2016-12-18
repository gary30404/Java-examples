public class HugeInteger
{
	
	private int digitArray[]; // integer array for storing the digits of the huge integer
	private int neg;
	private int overflow;
	final static int MAX_INT_SIZE = 40; // constant for the maximum number of digits
	
	public HugeInteger(){
		// digitArray will be automatically initialized to contain only 0's
		digitArray = new int[MAX_INT_SIZE];
		neg = 0;
		overflow = 0;
	}
	
	public HugeInteger(String value)
	{
		this();
		this.inputHugeInteger(value);
	}
	
	//converts a string representation of a huge integer into the HugeInteger format.
	public void inputHugeInteger(String str){
		int value = 0;
		int index = 0;
		String[] ary = str.split("");
		if (ary[0].equals("-")){
			neg = 1;
			str = str.replace("-","");
		}
		if (str.length() > MAX_INT_SIZE)
			str = str.substring(str.length()-MAX_INT_SIZE);
		if ((str.length() <= MAX_INT_SIZE) && (str.length() > 0)){	
			for(int counter = 1; counter <= str.length(); counter++){
				int charDigit = str.length() - counter;
				value = Character.getNumericValue(str.charAt(charDigit));
				index = MAX_INT_SIZE-counter;
				if (Character.isDigit(str.charAt(charDigit))){
					if( (index < MAX_INT_SIZE) && (index >= 0)){
						if( (value < 10) && (value >= 0)){
							this.digitArray[index] = value;
						}
						else{
						System.out.println("setIndex failed: value should be an integer in the range 0-9.");
						}
					}
					else{
					System.out.println("setIndex failed: index out of bounds.");
					}
				}
			}
		}
		else{
			// Alert the user if the string was too large or too small
			System.out.printf("parse failed: value must contain %d or fewer characters", MAX_INT_SIZE);
		}
	}
	
	
	public HugeInteger addHugeIntegers(HugeInteger b){
		int sum = 0;
		int carry = 0;
		int index = 0;
		HugeInteger a = this;
		HugeInteger result = new HugeInteger();
		int i = 0;
		boolean check;
		while( (digitArray[i] == b.digitArray[i]) && (i < MAX_INT_SIZE) ){
			i++;
		}
		if(i == MAX_INT_SIZE){
			check = false;
		}
		else{
			check = (a.digitArray[i] > b.digitArray[i]);
		}
		if ((a.neg == 1) && (b.neg == 0)){
			
			for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
				index = MAX_INT_SIZE -counter;
				if ((index < MAX_INT_SIZE) && (index >= 0))
					if(check){
						result.neg = 1;
						sum = a.digitArray[index] - b.digitArray[index] - carry;
					}
					else
						sum = b.digitArray[index] - a.digitArray[index] - carry;
				else{
					// Alert the user that the index is out of bounds
					System.out.println("getIndex failed: index out of bounds");
					//return -1;
				}
				if(sum >= 0){
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( (sum < 10) && (sum >= 0)){
							result.digitArray[MAX_INT_SIZE-counter] = sum;
						}
					}
					carry = 0;
				}	
				else{
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( ((sum+10) < 10) && ((sum+10) >= 0)){
							result.digitArray[MAX_INT_SIZE-counter] = sum + 10;
						}
					}
					carry = 1;
				}
			}

			return result;


		}
		else if ((a.neg == 0) && (b.neg == 1)){
			
			for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
				index = MAX_INT_SIZE -counter;
				if ((index < MAX_INT_SIZE) && (index >= 0))
					if(check)
						sum = a.digitArray[index] - b.digitArray[index] - carry;
					else{
						result.neg = 1;
						sum = b.digitArray[index] - a.digitArray[index] - carry;
					}
				else{
					// Alert the user that the index is out of bounds
					System.out.println("getIndex failed: index out of bounds");
					//return -1;
				}
				if(sum >= 0){
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( (sum < 10) && (sum >= 0)){
							result.digitArray[MAX_INT_SIZE-counter] = sum;
						}
					}
					carry = 0;
				}	
				else{
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( ((sum+10) < 10) && ((sum+10) >= 0)){
							result.digitArray[MAX_INT_SIZE-counter] = sum + 10;
						}
					}
					carry = 1;
				}
			}

			return result;

		}
		else{
			if ((a.neg == 1) && (b.neg==1)){
				result.neg = 1;
			}
			for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
				index = MAX_INT_SIZE -counter;
				if ((index < MAX_INT_SIZE) && (index >= 0))
					sum = a.digitArray[index] + b.digitArray[index] + carry;
				else{
					// Alert the user that the index is out of bounds
					System.out.println("getIndex failed: index out of bounds");
					//return -1;
				}
				if(sum < 10){
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( (sum < 10) && (sum >= 0)){
							result.digitArray[MAX_INT_SIZE-counter] = sum;
						}
					}
					carry = 0;
				}	
				else{
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( ((sum-10) < 10) && ((sum-10) >= 0)){
							result.digitArray[MAX_INT_SIZE-counter] = sum-10;
						}
					}
					carry = 1;
				}
			}
		
			// if addition is finished and the carry is not 0, an overflow has occurred
			if(carry != 0){
				//System.out.println("addition result is incorrect: an overflow occurred");
				result.overflow = 1;
			}
		}
		return result;
	}
	
	public HugeInteger substractHugeIntegers(HugeInteger d){
		int difference = 0;
		int borrow = 0;
		int index = 0;
		HugeInteger c = this;
		HugeInteger result2 = new HugeInteger();
		int i = 0;
		boolean check;
		while( (digitArray[i] == d.digitArray[i]) && (i < MAX_INT_SIZE) ){
			i++;
		}
		if(i == MAX_INT_SIZE){
			check = false;
		}
		else{
			check = (c.digitArray[i] > d.digitArray[i]);
		}
		if(c.isGreaterThanOrEqualTo(d)){
			if ((c.neg == 1)&&(d.neg == 1)){
				for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
				index = MAX_INT_SIZE -counter;
				if ((index < MAX_INT_SIZE) && (index >= 0) && check){
					difference = c.digitArray[index] - d.digitArray[index] - borrow;
					result2.neg = 1;
				}
				else{
					difference = d.digitArray[index] - c.digitArray[index] - borrow;
					result2.neg = 0;
				}
				if(difference >= 0){
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( (difference < 10) && (difference >= 0)){
							result2.digitArray[MAX_INT_SIZE-counter] = difference;
						}
					}
					borrow = 0;
				}	
				else{
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( ((difference+10) < 10) && ((difference+10) >= 0)){
							result2.digitArray[MAX_INT_SIZE-counter] = difference + 10;
						}
					}
					borrow = 1;
				}
				}
				return result2;
			}
			
			else if ((c.neg == 0)&&(d.neg == 1)){
				int carry = 0;
				for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
					index = MAX_INT_SIZE -counter;
					if ((index < MAX_INT_SIZE) && (index >= 0))
						difference = c.digitArray[index] + d.digitArray[index] + carry;
					else{
						// Alert the user that the index is out of bounds
						System.out.println("getIndex failed: index out of bounds");
						//return -1;
					}
					if(difference < 10){
						if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
							if( (difference < 10) && (difference >= 0)){
								result2.digitArray[MAX_INT_SIZE-counter] = difference;
							}
						}
						carry = 0;
					}	
					else{
						if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
							if( ((difference-10) < 10) && ((difference-10) >= 0)){
								result2.digitArray[MAX_INT_SIZE-counter] = difference-10;
							}
						}
						carry = 1;
					}
				}
				return result2;


			}
			else if ((c.neg == 1)&&(d.neg == 0)){
				int carry = 0;
				result2.neg=1;
				for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
					index = MAX_INT_SIZE -counter;
					if ((index < MAX_INT_SIZE) && (index >= 0))
						difference = c.digitArray[index] + d.digitArray[index] + carry;
					else{
						// Alert the user that the index is out of bounds
						System.out.println("getIndex failed: index out of bounds");
						//return -1;
					}
					if(difference < 10){
						if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
							if( (difference < 10) && (difference >= 0)){
								result2.digitArray[MAX_INT_SIZE-counter] = difference;
							}
						}
						carry = 0;
					}	
					else{
						if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
							if( ((difference-10) < 10) && ((difference-10) >= 0)){
								result2.digitArray[MAX_INT_SIZE-counter] = difference-10;
							}
						}
						carry = 1;
					}
				}
				return result2;


			}
			for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
				index = MAX_INT_SIZE -counter;
				if ((index < MAX_INT_SIZE) && (index >= 0))
					difference = c.digitArray[index] - d.digitArray[index] - borrow;
				else{
					// Alert the user that the index is out of bounds
					System.out.println("getIndex failed: index out of bounds");
					//return -1;
				}
				if(difference >= 0){
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( (difference < 10) && (difference >= 0)){
							result2.digitArray[MAX_INT_SIZE-counter] = difference;
						}
					}
					borrow = 0;
				}	
				else{
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( ((difference+10) < 10) && ((difference+10) >= 0)){
							result2.digitArray[MAX_INT_SIZE-counter] = difference + 10;
						}
					}
					borrow = 1;
				}
			}
			return result2;
		}
		else{
			if ((c.neg == 0)&&(d.neg == 0)){
				result2.neg=1;
			}
			if ((c.neg == 1)&&(d.neg == 1)){
				result2.neg=1;
				for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
				index = MAX_INT_SIZE -counter;
				if ((index < MAX_INT_SIZE) && (index >= 0)){
					difference = c.digitArray[index] - d.digitArray[index] - borrow;
				}
				if(difference >= 0){
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( (difference < 10) && (difference >= 0)){
							result2.digitArray[MAX_INT_SIZE-counter] = difference;
						}
					}
					borrow = 0;
				}	
				else{
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( ((difference+10) < 10) && ((difference+10) >= 0)){
							result2.digitArray[MAX_INT_SIZE-counter] = difference + 10;
						}
					}
					borrow = 1;
				}
				}
				return result2;
			}
			
			if ((c.neg == 1)&&(d.neg == 0)){
				result2.neg = 1;
				int carry = 0;
				for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
					index = MAX_INT_SIZE -counter;
					if ((index < MAX_INT_SIZE) && (index >= 0))
						difference = c.digitArray[index] + d.digitArray[index] + carry;
					else{
						// Alert the user that the index is out of bounds
						System.out.println("getIndex failed: index out of bounds");
						//return -1;
					}
					if(difference < 10){
						if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
							if( (difference < 10) && (difference >= 0)){
								result2.digitArray[MAX_INT_SIZE-counter] = difference;
							}
						}
						carry = 0;
					}	
					else{
						if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
							if( ((difference-10) < 10) && ((difference-10) >= 0)){
								result2.digitArray[MAX_INT_SIZE-counter] = difference-10;
							}
						}
						carry = 1;
					}
				}
				return result2;
			}
			for(int counter = 1; counter <= MAX_INT_SIZE; counter++){
				index = MAX_INT_SIZE -counter;
				if ((index < MAX_INT_SIZE) && (index >= 0))
					difference = d.digitArray[index] - c.digitArray[index] - borrow;
				else{
					// Alert the user that the index is out of bounds
					System.out.println("getIndex failed: index out of bounds");
					//return -1;
				}
				if(difference >= 0){
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( (difference < 10) && (difference >= 0)){
							result2.digitArray[MAX_INT_SIZE-counter] = difference;
						}
					}
					borrow = 0;
				}	
				else{
					if ((MAX_INT_SIZE-counter < MAX_INT_SIZE) && (MAX_INT_SIZE-counter >= 0)){
						if( ((difference+10) < 10) && ((difference+10) >= 0)){
							result2.digitArray[MAX_INT_SIZE-counter] = difference + 10;
						}
					}
					borrow = 1;
				}
			}
		}
		return result2;
	}

	public boolean isEqualTo(HugeInteger argument){	
		if (this.neg != argument.neg)
			return false;
		for(int counter = 0; counter <MAX_INT_SIZE; counter++){
			if(this.digitArray[counter] != argument.digitArray[counter]){
				return false;
			}
		}
		return true;
	}
	
	public boolean isNotEqualTo(HugeInteger argument){
		return !(isEqualTo(argument));
	}
	
	public boolean isGreaterThan(HugeInteger argument){
		int counter = 0;
		boolean check;
		if ((this.neg == 1) && (argument.neg == 0))
			return false;
		else if ((this.neg == 0) && (argument.neg == 1))
			return true;
		else{
			check = (this.neg == 0) && (argument.neg == 0);
			while( (digitArray[counter] == argument.digitArray[counter]) && (counter < MAX_INT_SIZE) ){
				counter++;
			}
			if(counter == MAX_INT_SIZE){
				return false;
			}
			else{
				if (check)
					return (this.digitArray[counter] > argument.digitArray[counter]);
				else
					return !(this.digitArray[counter] > argument.digitArray[counter]);
			}
		}
	}
	
	
	
	public boolean isLessThan(HugeInteger argument){
		return (!this.isGreaterThan(argument) && !this.isEqualTo(argument));
	}

	public boolean isLessThanOrEqualTo(HugeInteger argument){
		return (this.isEqualTo(argument) || this.isLessThan(argument));
	}
	public boolean isGreaterThanOrEqualTo(HugeInteger argument){
		return (this.isEqualTo(argument) || this.isGreaterThan(argument));
	}
	
	public String outputHugeInteger(){
		// create a StringBuffer object with initial capacity equal to the digitArray capacity
		StringBuffer stringValue = new StringBuffer(MAX_INT_SIZE);
		int counter = 0;
		int check = 0;
		String tmp = "";

		while(counter<MAX_INT_SIZE){
			if((this.digitArray[counter] != 0) && (check !=1)){
				//stringValue.append(this.digitArray[counter]);
				check = 1;
			}
			if (check == 1)
				stringValue.append(this.digitArray[counter]);
			counter=counter+1;
		}
		check = 0;
		if (this.neg == 1)
			tmp = "-" + stringValue.toString();
		else if (this.neg == 0 && this.overflow == 0)
			tmp = stringValue.toString();
		else
			tmp = "0";
		return tmp;
	}
}