import java.util.*;
import java.lang.*;

public class MatrixCalculator{

	public MatrixCalculator(){
	}

	public static void main(String[] args) throws Exception{

		Random randomno = new Random();
		float[][] A, B, C, D;
		A = new float[1000][1000];
		B = new float[1000][1000];
		C = new float[1000][1000];
		for (int i=0;i<1000;i++){
			for (int j =0;j<1000;j++){
				A[i][j] = randomno.nextFloat();
			}
		}

		//Single Thread 
		System.out.println("Single Thread Start:");
		long startTime_single = System.currentTimeMillis();
		for (int i=0;i<1000;i++){
			for (int j =0;j<1000;j++){
				B[i][j]+=(A[i][j]*A[j][i]);
			}
		}
		long endTime_single = System.currentTimeMillis();
  		long totTime_single = endTime_single - startTime_single;
  		System.out.println("Single Thread Using Time: " + totTime_single);

  		//Multi Threads 
  		System.out.println("Multi Threads Start:" );
		long startTime_multi = System.currentTimeMillis();

		MyThread sum = new MyThread(0,250,A, C);
		Thread t1 = new Thread(sum);
		MyThread sum2 = new MyThread(250,500,A, C);
		Thread t2 = new Thread(sum2);
		MyThread sum3 = new MyThread(500,750,A, C);
		Thread t3 = new Thread(sum3);
		MyThread sum4 = new MyThread(750,1000,A, C);
		Thread t4 = new Thread(sum4);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
   		
		long endTime_multi = System.currentTimeMillis();
  		long totTime_multi  = endTime_multi  - startTime_multi ;
  		System.out.println("Multi Thread Usings Time: " + totTime_multi);
		
		//try{Thread.sleep(1000);}catch(Exception e){}
  		//verify
		for (int i=0;i<1000;i++){
			for (int j =0;j<1000;j++){
				//System.out.println(B[i][j]+" ");
				if(Math.abs(B[i][j] - C[i][j]) > 0.001){
					System.out.println("Something wrong!");
					break;
				}
			}
		}
	}
}

class MyThread implements Runnable{
	private float[][] array1, array2;
	private int start, end;
	public MyThread(int s, int e, float[][] A, float[][] C){
		this.array1 = A;
		this.array2 = C;
		this.start = s;
		this.end = e;
	}
	public void run(){
		//System.out.println("Thread1 start.");
		for (int i=start;i<end;i++){
			for (int j =0;j<1000;j++){
				array2[i][j] += array1[i][j]*array1[j][i];
			}
		}
	}
}