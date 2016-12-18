import java.io.*;
import java.util.*;

public class DataChooser{

	public static void main(String[] args) {
		
		//String name;
		//Scanner scanner = new Scanner(System.in);
		//System.out.println("Enter the name of file: ");
		//name = scanner.nextLine();
		ReadnWrite("data.txt");
	}

	private static void ReadnWrite(String name) {

		BufferedReader reader = null;
		BufferedReader reader2 = null;
		BufferedWriter writer = null;
		int length = 0;
		try {
			File file = new File("newData.txt");
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
			reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
			String str = null;
			
			while ((str = reader.readLine()) != null) {
				length++;
			}
			int[] arr = new int[length];
			for(int i = 0;i<length;i++){
				str = reader2.readLine();
				String[] str_array = str.split("");	
				if(str_array[40].equals(" ")){
					String tmp = str_array[41]+str_array[42]+str_array[43]+str_array[44];
					arr[i] = Integer.valueOf(tmp);
				}
				else {
					String tmp = str_array[40]+str_array[41]+str_array[42]+str_array[43]+str_array[44];
					arr[i] = Integer.valueOf(tmp);
				}
			}
			for (int i = 0; i < length-1;i++){
				for (int j = i+1;j<length;j++){
					if(arr[i]>arr[j]){
						int buffer=arr[i];
						arr[i]=arr[j];
						arr[j]=buffer;
					}
				}
			}
			for (int i = 0;i<length;i++){
				writer.append(String.valueOf(arr[i]));
				writer.newLine();
			}
			writer.flush();
			System.out.println("smallest: "+ arr[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}