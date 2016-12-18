import java.util.*;

public class NumArrayList{
	public static void main(String[] args){
		ArrayList<Integer> numarrayList = new ArrayList<Integer>();
		Random rand = new Random();
		for (int i = 0; i<20; i++){
			int  n = rand.nextInt(1001);
			System.out.println(n);
			numarrayList.add(n);
		}
		System.out.println("--------");
		//Iterator 介面
		Iterator<Integer> sListIterator = numarrayList.iterator();  
		while(sListIterator.hasNext()){  
    		int e = sListIterator.next();  
    		if(e < 500){  
    			sListIterator.remove();  
    		}
    		else {
    			System.out.println(e);  	
    		}  
		} 
	}
} 