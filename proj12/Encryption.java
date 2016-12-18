import java.util.*;

public class Encryption {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("a", "v");
        map.put("b", "e");
        map.put("c", "k");
        map.put("d", "n");
        map.put("e", "o");
        map.put("f", "h");
        map.put("g", "z");
        map.put("h", "f");
        map.put("i", " ");
        map.put("j", "i");
        map.put("k", "l");
        map.put("l", "j");
        map.put("m", "x");
        map.put("n", "d");
        map.put("o", "m");
        map.put("p", "y");
        map.put("q", "g");
        map.put("r", "b");
        map.put("s", "r");
        map.put("t", "c");
        map.put("u", "s");
        map.put("v", "w");
        map.put("w", "q");
        map.put("x", "u");
        map.put("y", "q");
        map.put("z", "t");
        map.put(" ", "a");
        String str;
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入一字串：");
        str = scanner.nextLine();
        String[] array = str.split("");
        System.out.println("輸出：");
        for(int i = 0; i<array.length;i++){
            for (String key : map.keySet()) {
                if(key.equalsIgnoreCase(array[i])){
                    System.out.print(map.get(key));
                }
            }
        }
        System.out.println("");
    }
}