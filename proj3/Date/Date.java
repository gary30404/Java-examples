import java.util.*;

public class Date{

	private int year, mon, day;
	//private int year, mon, day;

	public Date(String m, String d, String y){
		year = Integer.valueOf(y);
		mon = Integer.valueOf(m);
		day = Integer.valueOf(d);
		if (check())
			print(m, d, y);
	}

	public Date(String m, int d, int y){
		year = y;
		switch(m){
			case "January":
				mon = 1;
				break;
			case "February":
				mon = 2;
				break;
			case "March":
				mon = 3;
				break;
			case "April":
				mon = 4;
				break;
			case "May":
				mon = 5;
				break;
			case "June":
				mon = 6;
				break;
			case "July":
				mon = 7;
				break;
			case "August":
				mon = 8;
				break;
			case "September":
				mon = 9;
				break;
			case "October":
				mon = 10;
				break;
			case "November":
				mon = 11;
				break;
			case "December":
				mon = 12;
				break;
			default:
				System.out.println("格式錯誤");
		}

		day = d;
		if (check())
			print(y, m, d);
	}

	public Date(int y, int m, int d){
		year = y;
		mon = m;
		day = d;
		if (check())
			print(y, m, d);
	}

	void print(String m, String d, String y){
		System.out.println(m+"/"+d+"/"+y);
	}

	void print(int y, String m, int d){
		System.out.println(m+" "+d+", "+y);
	}

	void print(int y, int m, int d){
		System.out.println(y+" 年"+m+" 月"+d+" 日");
	}

	private boolean check(){
		if (mon>12){
			System.out.println("格式錯誤");
			return false;
		}
		if ((mon == 2)||(mon == 4)||(mon == 6)||(mon == 9)||(mon == 11)){
			if (mon == 2){
				if (year%400==0 || ((year%4==0)&&(year%100!=0))){
					if (day >= 30){
						System.out.println("格式錯誤");
						return false;
					}
				}
				else{
					if (day >= 29){
						System.out.println("格式錯誤");
						return false;
					}
				}
			}
			else {
				if (day >= 31){
					System.out.println("格式錯誤");
					return false;
				}
			}
		}
		else
			if (day >= 32) {
				System.out.println("格式錯誤");
				return false;
			}

		return true;
	}

	public int dateDistance(Date d){

		int date = 0;

		if (this.year >= d.year){
			if (this.mon > d.mon){
				//calculus different between days
				if (this.day >= d.day){
					date = this.day - d.day;
				}
				else {
					this.mon = this.mon - 1;
					if ((this.mon == 4)||(this.mon == 6)||(this.mon == 9)||(this.mon == 11)){
						date = this.day + 30 - d.day;

					}
					else if (this.mon == 2){
						if (year%400==0 || ((year%4==0)&&(year%100!=0))){
							date = this.day + 29 - d.day;
						}
						else{
							date = this.day + 28 - d.day;
						}
					}
					else {
						date = this.day + 31 - d.day;
					}
				}
				//calculus different between mon
				if (this.mon == d.mon){
					return date;
				}
				else {
					while (this.mon > d.mon){
						this.mon = this.mon - 1;
						if ((this.mon == 4)||(this.mon == 6)||(this.mon == 9)||(this.mon == 11)){
							date = date + 30;
						}
						else if (this.mon == 2){
							if (this.year%400==0 || ((this.year%4==0)&&(this.year%100!=0))){
								date = date + 29;
							}
							else{
								date = date + 28;
							}
						}
						else {
							date = date + 31;
						}
					}
				}
			}
			else if (this.mon == d.mon){
				if (this.year == d.year){
					if (this.day >= d.day)
						date = date + (this.day - d.day);
					else
						date = date + (d.day - this.day);
				}
				else
					date = date + (this.day - d.day);
			}
			//e.g. 2016/04/30; 2015/09/28
			else {
				int max_mon = 12;
				int max_day = 31;
				if (this.year - d.year == 1){
					date = max_day - d.day;
					while (max_mon > d.mon){
						max_mon = max_mon - 1;
						if ((max_mon == 4)||(max_mon == 6)||(max_mon == 9)||(max_mon == 11)){
							date = date + 30;
						}
						else if (max_mon == 2){
							if (year%400==0 || ((year%4==0)&&(year%100!=0))){
								date = date + 29;
							}
							else{
								date = date + 28;
							}
						}
						else {
							date = date + 31;
						}
					}
					while (this.mon > 1){
						max_mon = max_mon - 1;
						if ((max_mon == 4)||(max_mon == 6)||(max_mon == 9)||(max_mon == 11)){
							date = date + 30;
						}
						else if (max_mon == 2){
							if (year%400==0 || ((year%4==0)&&(year%100!=0))){
								date = date + 29;
							}
							else{
								date = date + 28;
							}
						}
						else {
							date = date + 31;
						}
					}
					date = date + this.day;
					return date;
				}
				if (this.year == d.year){
					int tmp_m, tmp_d;
					tmp_m = this.mon;
					tmp_d = this.day;
					this.mon = d.mon;
					this.day = d.day;
					d.mon = tmp_m;
					d.day = tmp_d;
					if (this.day >= d.day){
						date = this.day - d.day;
					}
					else {
						this.mon = this.mon - 1;
						if ((this.mon == 4)||(this.mon == 6)||(this.mon == 9)||(this.mon == 11)){
							date = this.day + 30 - d.day;
						}
						else if (this.mon == 2){
							if (year%400==0 || ((year%4==0)&&(year%100!=0))){
								date = this.day + 29 - d.day;
							}
							else{
								date = this.day + 28 - d.day;
							}
						}
						else {
							date = this.day + 31 - d.day;
						}
					}
					//calculus different between mon
					if (this.mon == d.mon){
						return date;
					}	
					else {
						while (this.mon > d.mon){
							this.mon = this.mon - 1;
							if ((this.mon == 4)||(this.mon == 6)||(this.mon == 9)||(this.mon == 11)){
								date = date + 30;
							}
							else if (this.mon == 2){
								if (year%400==0 || ((year%4==0)&&(year%100!=0))){
									date = date + 29;
								}
								else{
									date = date + 28;
								}
							}
							else {
								date = date + 31;
							}		
						}
					}
					//return date;
				}
			}
			while (this.year > d.year){
				if (this.year%400==0 || ((this.year%4==0)&&(this.year%100!=0))){
					date = date + 366;
				}
				else{
					date = date + 365;
				}
				this.year = this.year -1;
			}
			return date;
		}
		else {
			int tmp_y , tmp_m, tmp_d;
			tmp_y = this.year;
			tmp_m = this.mon;
			tmp_d = this.day;
			this.year = d.year;
			this.mon = d.mon;
			this.day = d.day;
			d.year = tmp_y;
			d.mon = tmp_m;
			d.day = tmp_d;
			if (this.mon >= d.mon){
				//calculus different between days
				if (this.day >= d.day){
					date = this.day - d.day;
				}
				else {
					this.mon = this.mon - 1;
					if ((this.mon == 4)||(this.mon == 6)||(this.mon == 9)||(this.mon == 11)){
						date = this.day + 30 - d.day;

					}
					else if (this.mon == 2){
						if (year%400==0 || ((year%4==0)&&(year%100!=0))){
							date = this.day + 29 - d.day;
						}
						else{
							date = this.day + 28 - d.day;
						}
					}
					else {
						date = this.day + 31 - d.day;
					}
				}
				//calculus different between mon
				if (this.mon == d.mon){
					return date;
				}
				else {
					while (this.mon > d.mon){
						this.mon = this.mon - 1;
						if ((this.mon == 4)||(this.mon == 6)||(this.mon == 9)||(this.mon == 11)){
							date = date + 30;
						}
						else if (this.mon == 2){
							if (year%400==0 || ((year%4==0)&&(year%100!=0))){
								date = date + 29;
							}
							else{
								date = date + 28;
							}
						}
						else {
							date = date + 31;
						}
					}
				}
				return date;
			}
			//e.g. 2016/04/30; 2015/09/28
			else {
				int max_mon = 12;
				int max_day = 31;
				if (this.year - d.year == 1){
					date = max_day - d.day;
					while (max_mon > d.mon){
						max_mon = max_mon - 1;
						if ((max_mon == 4)||(max_mon == 6)||(max_mon == 9)||(max_mon == 11)){
							date = date + 30;
						}
						else if (max_mon == 2){
							if (year%400==0 || ((year%4==0)&&(year%100!=0))){
								date = date + 29;
							}
							else{
								date = date + 28;
							}
						}
						else {
							date = date + 31;
						}
					}
					while (this.mon > 1){
						max_mon = max_mon - 1;
						if ((max_mon == 4)||(max_mon == 6)||(max_mon == 9)||(max_mon == 11)){
							date = date + 30;
						}
						else if (max_mon == 2){
							if (year%400==0 || ((year%4==0)&&(year%100!=0))){
								date = date + 29;
							}
							else{
								date = date + 28;
							}
						}
						else {
							date = date + 31;
						}
					}
					date = date + this.day;
					return date;
				}
			}
		}
		return date;
	}	
}