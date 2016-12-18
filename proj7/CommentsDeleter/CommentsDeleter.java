import java.io.*;
import java.util.*;

public class CommentsDeleter{
	public static void main(String[] args) {
		
		//String name;
		//Scanner scanner = new Scanner(System.in);
		//System.out.println("Enter the name of file: ");
		//name = scanner.nextLine();
		ReadnWrite("Lab7.java");
		File file = new File("Lab7.java");
		File file2 = new File("Lab7_2.java");
		file2.renameTo(file);
		//f.moveFile("Lab7.java", "Lab7_2.java");
		//f.delete();
	}

	private static void ReadnWrite(String name) {

		BufferedReader reader = null;
		//BufferedReader reader2 = null;
		BufferedWriter writer = null;
		BufferedWriter writer_ori = null;
		try {
			File file = new File("NewLab7.java");
			File file_ori = new File("Lab7_2.java");
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			writer_ori = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_ori, true)));
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
			//reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
			String str = null;
			int length = 0;
			int length1 = 0, length2 = 0;
			boolean check = false;
			while ((str = reader.readLine()) != null) {
				int start = 0, end = 0;
				//str = reader.readLine();
				String[] str_array = str.split("");
				for (int i = 0;i<str_array.length-1;i++){
					if(!check){
						if(str_array[i].equals("/") && str_array[i+1].equals("*")){
							start = i;
							check = true;
							length1 = length;
						}
						if(str_array[i].equals("/") && str_array[i+1].equals("/")){
							start = i;
							String tmp = "";
							for (int k = start;k<str_array.length;k++){
								tmp = tmp + str_array[k];
							}
							writer.append(tmp);
							writer.newLine();
							writer.flush();
							//
							tmp = "";
							for(int k = 0;k<start;k++){
								tmp = tmp + str_array[k];
							}
							writer_ori.append(tmp);
							writer_ori.newLine();
							writer_ori.flush();
							//System.out.println(tmp);
							break;//next line
						}
					}
					if(check){
						if(str_array[i].equals("*") && str_array[i+1].equals("/")){
							length2 = length;
							end = i+1;
							if(length1 == length2){
								System.out.println("same line end*/"+length);
								check = false;
							}
							else{
								String tmp = "";
								for (int k = 0;k<=end;k++){
									tmp = tmp + str_array[k];
								}
								writer.append(tmp);
								writer.newLine();
								writer.flush();
								//System.out.println(tmp);
								check = false;
								break;
							}
						}
						if(i == str_array.length-2){
							String tmp = "";
							for (int k = 0;k<str_array.length;k++){
								tmp = tmp + str_array[k];
							}
							writer.append(tmp);
							writer.newLine();
							writer.flush();
							//System.out.println(tmp);
						}
					}
					if(!check && i==str_array.length-2){
						String tmp = "";
							for (int k = 0;k<str_array.length;k++){
								tmp = tmp + str_array[k];
							}
							writer_ori.append(tmp);
							writer_ori.newLine();
							writer_ori.flush();
					}
				}
				length++;
			}
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