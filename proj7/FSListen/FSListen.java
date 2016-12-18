import java.io.*;
import java.util.*;

public class FSListen {
	public static void main(String[] args) {
		
		String name;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the name of directory: ");
		name = scanner.nextLine();
		File f = new File(name);
		if(!f.exists()) {
			System.out.println("The directory isn't exist!");
			return;
		}
		if(f.isDirectory()){
			File fw = new File("result.txt");
			try{
				PrintStream out = new PrintStream(fw);
				out.println(name+": ");
				dir(f,1);
			} catch(FileNotFoundException e){
				System.out.println("FileNotFoundException");
			}
			
		}
		else{
			System.out.println("This is not an directory!");
		}
	}

	private static void dir(File son,int m) {

		BufferedWriter fw = null;
		try {
			File file = new File("result.txt");
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),"UTF-8"));
			File s;
			String[] strs = son.list();

			for(int i=0;i < strs.length; i++){

				for(int j=0;j<m;j++){
					fw.append("    ");
					//System.out.print("    ");
				}
				if(m==0){
					fw.append("    ");
					System.out.print("    ");
				}
				s = new File(son+"/"+strs[i]);
				if(s.isDirectory()){
					fw.append(strs[i]+": ");
					fw.newLine();
					fw.flush();
					//System.out.println(strs[i]+": ");
				
					String[] list = s.list();
					if ( list.length != 0){
						m+=1;
						dir(s,m);
					}
					m=m-1;
				}
				else{
					fw.append(strs[i]);
					fw.newLine();
					fw.flush();
					//System.out.println(strs[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}
}

