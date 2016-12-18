import java.util.*;

public class NumTreeSet{
    public static void main(String[] args) {

        //Putting Integers in sorted order
        Set<Integer> integerSet = new TreeSet<Integer>();
        Random rand = new Random();
        for (int i = 0; i<80; i++){
			int  n = rand.nextInt(101);
			integerSet.add(n);
		}
		int number = 0;
		for (int num : integerSet){
			System.out.println(num);
			number++;
		}
		System.out.println("共:"+number+"個不同的數");
    }
}
