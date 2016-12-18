import java.io.*;
import java.util.*;

public class CrazyPrinter{
	public static void main(String[] args){
		UsingThread t1 = new UsingThread();
		t1.start();
		UsingRunnable thread = new UsingRunnable();
		Thread t2 = new Thread(thread);
		t2.setPriority(Thread.MAX_PRIORITY-2);
		t2.start();
	}
}

class UsingThread extends Thread{
	public void run(){
		BufferedWriter writer = null;
        try{
            //File f = new File("write.txt");
            File f = new File("write8.txt");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
            for (int i = 1;i<1001;i++){
                writer.append("UsingThread: "+i);
                writer.newLine();
                writer.flush();
            }
        }catch (IOException e) {}
	}
}

class UsingRunnable implements Runnable{
	public void run(){
		BufferedWriter writer = null;
        try{
            //File f = new File("write.txt");
            File f = new File("write8.txt");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
            for (int i = 1;i<1001;i++){
                writer.append("UsingRunnable: "+i);
                writer.newLine();
                writer.flush();
            }
        }catch (IOException e2) {}
	}
}