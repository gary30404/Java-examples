import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ColorWord extends JFrame
{
    private JPanel topPanel;
    private JTextPane tPane;

    private JPanel loc1Panel;
    private JButton greenButton, orangeButton;


    public ColorWord()
    {
        setLayout(new GridLayout(2, 2));
        setSize(300,250);

        topPanel = new JPanel();        
        loc1Panel = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
       
        tPane = new JTextPane();                
        
        tPane.setMargin(new Insets(5, 5, 5, 5));
        topPanel.add(tPane);
        appendToPane(tPane, "My Name is Too Good.\n", Color.RED);
        getContentPane().add(topPanel);

        getContentPane().add(loc1Panel, BorderLayout.SOUTH);
        greenButton = new JButton("Blue");
        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tPane.setText(""); 
                appendToPane(tPane, "My Name is Too Good.\n", Color.BLUE);
            }
        });
        loc1Panel.add(greenButton, BorderLayout.WEST);
        greenButton.setBackground(Color.green);;
        orangeButton = new JButton("Red");
        orangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tPane.setText(""); 
                appendToPane(tPane, "My Name is Too Good.\n", Color.RED);
            }
        });        
        loc1Panel.add(orangeButton, BorderLayout.EAST);
        orangeButton.setBackground(Color.orange);

        pack();
        setVisible(true);   
    }

    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    public static void main(String[] args){
        new ColorWord ();
    }
}