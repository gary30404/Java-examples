public class ChaoticSum{
	public static void main(String[] args){
		Sum su = new Sum(1);
		for(int i = 0;i<20;i++){
			su.sum = su.sum + 1;
			try{Thread.sleep(1);} catch(Exception e){}
		}
		Thread1 t1 = new Thread1(su);
		t1.start();
		try{t1.join();;} catch(Exception e2){}
		System.out.println(su.sum);
	}
}

class Sum{
	public int sum;
	public Sum(int s){
		sum = s;
	}
}

class Thread1 extends Thread{
	Sum su2;
	public Thread1(Sum s){
		su2 = s;
	}
	public void run(){
		int t;
		for(int i = 0;i<20;i++){
			t = su2.sum;
			t = t + 1;
			try{this.sleep(1);} catch(Exception e){}
			su2.sum = t;
		}
	}
}