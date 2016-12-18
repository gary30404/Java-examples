import java.util.*;
import java.lang.*;

public class ParallelSum{
	public static void main(String [] args){

	Random r = new Random();
	int[] a, b;
	a = new int[300];
	b = new int[3];	
	for (int i=0;i<300;i++){
		a[i] = r.nextInt(101);
		System.out.println(a[i]);
	}
	//Check
	int check = 0;
	for (int i=0;i<300;i++){
		check = check + a[i];
	}
	System.out.println("check= "+check);
	//MyThread thread 
	MyThread sum = new MyThread(0,100,a, b, 0);
	new Thread(sum, "t1").start();
	MyThread sum2 = new MyThread(100,200,a, b, 1);
	new Thread(sum2, "t2").start();
	MyThread sum3 = new MyThread(200,300,a, b, 2);
	new Thread(sum3, "t3").start();
	try{Thread.sleep(1000);}catch(Exception e){}
	System.out.println(b[0]+b[1]+b[2]);
   }
}


class MyThread implements Runnable{

	private int startIdx, nThreads, res;
	private int[] array, array2;

	public MyThread(int s, int n, int[] m, int[] c, int index){
    	this.startIdx = s;
    	this.nThreads = n;
    	this.array = m;
    	this.array2 = c;
    	this.res = index;
 	}

 	public void run(){
   		int t = 0;
     	for(int i = this.startIdx; i < this.nThreads; i++){
        	t = t + array[i];
      	}
      	array2[res] = t;
      	System.out.println(array2[res]);
   }

}