import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
public class FakeMenu{
    public static void main(String[] args) {
        new FakeMenu();
    }
     
    public FakeMenu(){
        JFrame frame = new JFrame("FakeMenu");
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(6, 4));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        JMenuItem item1, item2, item3, item4, item5, item6, item7;
        JMenu file = new JMenu("File");
        item1 = new JMenuItem("New");
        item2 = new JMenuItem("Open");
        item3 = new JMenuItem("Save");
        file.add(item1);
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("File->New");
            }
        });
        file.add(item2);
        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("File->Open");
            }
        });
        file.add(item3);
        item3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("File->Save");
            }
        });
        JMenu edit = new JMenu("Edit");
        item4 = new JMenuItem("Copy");
        item5 = new JMenuItem("Cut");
        item6 = new JMenuItem("Paste");
        edit.add(item4);
        item4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Edit->Copy");
            }
        });
        edit.add(item5);
        item5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Edit->Cut");
            }
        });
        edit.add(item6);
        item6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Edit->Paste");
            }
        });
    	JMenu help = new JMenu("Help");
        item7 = new JMenuItem("About");
        help.add(item7);
        item7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Help->About");
            }
        });

        JMenuBar menubar = new JMenuBar();
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);
        frame.setJMenuBar(menubar);

        frame.setVisible(true);
         
    }
}
