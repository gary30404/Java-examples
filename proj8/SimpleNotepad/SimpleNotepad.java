import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;
 
public class SimpleNotepad{
    public static void main(String[] args) {
        new SimpleNotepad();
    }
     
    public SimpleNotepad(){
        JFrame frame = new JFrame("SimpleNotepad");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setText("test....");

        JMenuItem item1, item2;
        JMenu file = new JMenu("File");
        item1 = new JMenuItem("Open");
        item2 = new JMenuItem("Save");
        file.add(item1);
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                JFrame.setDefaultLookAndFeelDecorated(true);
 			   	JDialog.setDefaultLookAndFeelDecorated(true);
    		
			    String input = JOptionPane.showInputDialog("請輸入檔名(.txt)");
			    JOptionPane.showMessageDialog(frame,"您輸入的是 : " + input);

                BufferedReader reader = null;
                try{
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
                    String s = "";
                    while ((s = reader.readLine()) != null) {
                        textArea.setText(s);
                        //System.out.println(s);
                    }
                }catch (IOException e2) {}
            }
        });
        file.add(item2);
        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BufferedWriter writer = null;
                try{
                    File f = new File("result.txt");
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false)));
                    String[] str_array =  textArea.getText().split("\\n");
                    for (int i = 0;i<str_array.length;i++){
                        //System.out.println(str_array[i]);
                        writer.append(str_array[i]);
                        writer.newLine();
                    }
                    writer.flush();
                    JOptionPane.showMessageDialog(frame,"存檔成功！");
                }catch (IOException e2) {}
            }
        });
        
        JMenuBar menubar = new JMenuBar();
        menubar.add(file);
        frame.setJMenuBar(menubar);

        frame.add(textArea);
        frame.setVisible(true);
         
    }
}
