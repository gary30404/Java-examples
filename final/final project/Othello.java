import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import javax.swing.border.*;
import javax.swing.Timer; 
import java.io.*;
import java.net.*;

public class Othello extends JFrame{
    
    private ChessBoard chBoard;
    private ChessBoardC chBoardc;
    private ChessBoardS chBoards;

    private Othello() {

        super("Othello");

        //Set Menu
        JMenuItem item1, item2;
        JMenu file = new JMenu("Help");
        item1 = new JMenuItem("Rules");
        item2 = new JMenuItem("About");
        file.add(item1);
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, 
                                            "1. Black always moves first."+'\n'
                                            +"2. If on your turn you cannot outflank and flip at least one"+'\n' 
                                            +"   opposing disc, your turn is forfeited and your opponent moves"+'\n' 
                                            +"   again. However, if a move is available to you, you may not"+'\n'                                                +"   forfeit your turn."+'\n'
                                            +"3. A disc may outflank any number of discs in one or more rows"+'\n'
                                            +"   in any number of directions at the same time - horizontally,"+'\n'
                                            +"   vertically or diagonally. "+'\n'
                                            +"4. You may not skip over your own colour disc to outflank an"+'\n'
                                            +"   opposing disc. "+'\n'
                                            +"5. Discs may only be outflanked as a direct result of a move and"+'\n'
                                            +"   must fall in the direct line of the disc placed down."+'\n'
                                            +"6. All discs outflanked in any one move must be flipped, even if"+'\n'
                                            +"   it is to the player's advantage not to flip them at all."+'\n'
                                            +"7. When it is no longer possible for either player to move, the"+'\n'
                                            +"   game is over. Discs are counted and the player with the majority"+'\n'
                                            +"   of his or her colour discs on the board is the winner."+'\n'
                                            +'\n'
                                            +"From: http://www.hannu.se/games/othello/rules.htm"+'\n'
                                            , "About", JOptionPane.INFORMATION_MESSAGE );
            }
        });
        file.add(item2);
        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Othello Game"+'\n'+"V.1.0"+'\n'+"Author: Chih-Chung Chang"+'\n'
                                                    +"All Rights Reserved.", "About", JOptionPane.INFORMATION_MESSAGE );
            }
        });
        JMenuBar menubar = new JMenuBar();
        menubar.add(file);
        this.setJMenuBar(menubar);
        //end 

        //choose mode
        
        String[] mode = {"Single", "Online"};
        String[] smode = {"2 Playsers", "Computer: black", "Computer: white"};
        String[] omode = {"Black(Server)", "White(Client)"};
        JTextField ipaddress = new JTextField();
		JTextField port = new JTextField();
		Object[] message = {
    		"IP:", ipaddress,
    		"Port:", port
		};

        try{
        	String in1 = (String)JOptionPane.showInputDialog(null,"Choose mode","Mode",JOptionPane.INFORMATION_MESSAGE,
        													 null,mode,"Single");
     		if (in1.equals("Single")){
     			String in2 = (String)JOptionPane.showInputDialog(null,"Choose single mode","Mode",JOptionPane.INFORMATION_MESSAGE,
     															 null,smode,"2 Playsers");
                add(chBoard = new ChessBoard(this));
                if (in2.equals("Computer: black")){
                    chBoard.setBlackPlayer(1);//電腦下黑
                }
                if (in2.equals("Computer: white")){
                    chBoard.setWhitePlayer(1);//電腦下白
                }
                /***********************/

                //Top Panel (bottom list)
                JPanel panelTop = new JPanel();
                panelTop.setPreferredSize(new Dimension(600, 70));
                panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
                Border blackline = BorderFactory.createLineBorder(Color.black);
                panelTop.setBorder(blackline);
                //Button 1
                Icon newIcon = new ImageIcon("new.png");
                JButton button_new = new JButton(newIcon);     
                button_new.setText("New Game");
                button_new.setVerticalTextPosition(SwingConstants.BOTTOM);
                button_new.setHorizontalTextPosition(SwingConstants.CENTER);
                button_new.setOpaque(false);
                button_new.setContentAreaFilled(false);
                button_new.setBorderPainted(false);
                button_new.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        chBoard.newGame();
                    }
                });

                panelTop.add(button_new);
                //Button 2
                Icon openIcon = new ImageIcon("open.png");
                JButton button_open = new JButton(openIcon);     
                button_open.setText("Open File");
                button_open.setVerticalTextPosition(SwingConstants.BOTTOM);
                button_open.setHorizontalTextPosition(SwingConstants.CENTER);
                button_open.setOpaque(false);
                button_open.setContentAreaFilled(false);
                button_open.setBorderPainted(false);
                button_open.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        FileChooser file = new FileChooser();
                        String input = file.getFileName();
                        if (!input.equals(null))
                            chBoard.readTxt(input);
                    }
                });
                panelTop.add(button_open);
                //Button 3
                Icon saveIcon = new ImageIcon("save.png");
                JButton button_save = new JButton(saveIcon);     
                button_save.setText("Save Game");
                button_save.setVerticalTextPosition(SwingConstants.BOTTOM);
                button_save.setHorizontalTextPosition(SwingConstants.CENTER);
                button_save.setOpaque(false);
                button_save.setContentAreaFilled(false);
                button_save.setBorderPainted(false);
                button_save.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame.setDefaultLookAndFeelDecorated(true);
                        String input = JOptionPane.showInputDialog(null, "Enter the file name", "Saving File", JOptionPane.PLAIN_MESSAGE);
                        WriteFile w = new WriteFile(input, chBoard.board, chBoard.text, chBoard.black, chBoard.white, chBoard.turn);
                    }
                });
                panelTop.add(button_save);
                //Button 4
                Icon undoIcon = new ImageIcon("undo.png");
                JButton button_undo = new JButton(undoIcon);     
                button_undo.setText("Undo Move");
                button_undo.setVerticalTextPosition(SwingConstants.BOTTOM);
                button_undo.setHorizontalTextPosition(SwingConstants.CENTER);
                button_undo.setOpaque(false);
                button_undo.setContentAreaFilled(false);
                button_undo.setBorderPainted(false);
                button_undo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(chBoard.movetimes < 1){
                            if((chBoard.turn==1)&&(chBoard.undototalb<2)){
                                chBoard.undo();
                            }
                            else if((chBoard.turn==2)&&(chBoard.undototalw<2)){
                                chBoard.undo();
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "No more undo!", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No more undo!", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                panelTop.add(button_undo);
                //
                String time = "30";
                JLabel label = new JLabel("");
                label.setFont(new Font("Arial", Font.BOLD, 20));
                label.setText(time);
                //Button 5
                Icon setIcon = new ImageIcon("setting.png");
                JButton button_set = new JButton(setIcon);     
                button_set.setText("Settings");
                button_set.setVerticalTextPosition(SwingConstants.BOTTOM);
                button_set.setHorizontalTextPosition(SwingConstants.CENTER);
                button_set.setOpaque(false);
                button_set.setContentAreaFilled(false);
                button_set.setBorderPainted(false);
                button_set.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame.setDefaultLookAndFeelDecorated(true);
                        String input = JOptionPane.showInputDialog(null, "輸入倒數時間", "設定", JOptionPane.PLAIN_MESSAGE);
                        label.setText(input);
                        chBoard.settime = Integer.parseInt(input);
                    }
                });
                panelTop.add(button_set);

                
                JLabel text = new JLabel(" Remaining Time:");
                text.setFont(new Font("Arial", Font.PLAIN, 15));
                chBoard.CountdownPresenterPanel(label);
                panelTop.add(text);
                panelTop.add(label);
                

                this.getContentPane().add(panelTop, BorderLayout.NORTH);

                //end of top
                /***********************/

                Color c = new Color(139, 69,20);
                this.getContentPane().setBackground(c); //set background
                CloseWindow close = new CloseWindow(this, true);

                addWindowListener(close);
                pack();
                setResizable(false);
                setVisible(true);

     		}
     		else{
     			int option = JOptionPane.showConfirmDialog(null, message, "Mode", JOptionPane.OK_CANCEL_OPTION);
    			if (option == JOptionPane.OK_OPTION) {
    				String ip = ipaddress.getText();
                    String pn = port.getText();
          			String in3 = (String)JOptionPane.showInputDialog(null,"Choose online part","Mode",JOptionPane.INFORMATION_MESSAGE,
     																 null,omode,"Black(Server)");
                    if(in3.equals("White(Client)")){

                        try{
                        add(chBoardc = new ChessBoardC(this,ip,pn));}
                        catch(Exception e){}
                        /***********************/

                        //Top Panel (bottom list)
                        JPanel panelTop = new JPanel();
                        panelTop.setPreferredSize(new Dimension(600, 70));
                        panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
                        Border blackline = BorderFactory.createLineBorder(Color.black);
                        panelTop.setBorder(blackline);
                        //Button 1
                        Icon newIcon = new ImageIcon("new.png");
                        JButton button_new = new JButton(newIcon);     
                        button_new.setText("New Game");
                        button_new.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_new.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_new.setOpaque(false);
                        button_new.setContentAreaFilled(false);
                        button_new.setBorderPainted(false);
                        button_new.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                chBoardc.newGame();
                            }
                        });

                        panelTop.add(button_new);
                        //Button 2
                        Icon openIcon = new ImageIcon("open.png");
                        JButton button_open = new JButton(openIcon);     
                        button_open.setText("Open File");
                        button_open.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_open.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_open.setOpaque(false);
                        button_open.setContentAreaFilled(false);
                        button_open.setBorderPainted(false);
                        panelTop.add(button_open);
                        //Button 3
                        Icon saveIcon = new ImageIcon("save.png");
                        JButton button_save = new JButton(saveIcon);     
                        button_save.setText("Save Game");
                        button_save.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_save.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_save.setOpaque(false);
                        button_save.setContentAreaFilled(false);
                        button_save.setBorderPainted(false);
                        panelTop.add(button_save);
                        //Button 4
                        Icon undoIcon = new ImageIcon("undo.png");
                        JButton button_undo = new JButton(undoIcon);     
                        button_undo.setText("Undo Move");
                        button_undo.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_undo.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_undo.setOpaque(false);
                        button_undo.setContentAreaFilled(false);
                        button_undo.setBorderPainted(false);
                        button_undo.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try{
                                    if(chBoardc.movetimes < 1){
                                        Socket socketClient = new Socket("localhost", 5678);
                                        BufferedReader brInFromServer = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                                        DataOutputStream dosOutToServer = new DataOutputStream(socketClient.getOutputStream());
                                        String strSocket = brInFromServer.readLine();
                                        if (strSocket.equals("ok")){
                                                chBoardc.undo();
                                        }
                                        else{
                                            socketClient.close();
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "No more undo!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                }catch(Exception ee){}
                            }
                        });
                        panelTop.add(button_undo);
                        //
                        String time = "30";
                        JLabel label = new JLabel("");
                        label.setFont(new Font("Arial", Font.BOLD, 20));
                        label.setText(time);
                        //Button 5
                        Icon setIcon = new ImageIcon("setting.png");
                        JButton button_set = new JButton(setIcon);     
                        button_set.setText("Settings");
                        button_set.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_set.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_set.setOpaque(false);
                        button_set.setContentAreaFilled(false);
                        button_set.setBorderPainted(false);
                        panelTop.add(button_set);

                        
                        JLabel text = new JLabel(" Remaining Time:");
                        text.setFont(new Font("Arial", Font.PLAIN, 15));
                        try{
                        chBoardc.CountdownPresenterPanel(label);}
                        catch(Exception e3){}
                        panelTop.add(text);
                        panelTop.add(label);
                        

                        this.getContentPane().add(panelTop, BorderLayout.NORTH);

                        //end of top
                        /***********************/

                       
                        Color c = new Color(139, 69,20);
                        this.getContentPane().setBackground(c); //set background
                        /////
                        Menu m;
                        MenuBar mb;
                        //add(oxBoard = new OX(this));
                        CloseWindow close = new CloseWindow(this, true);

                        addWindowListener(close);
                        pack();
                        setResizable(false);
                        setVisible(true);

                    }
                    if(in3.equals("Black(Server)")){

                        try{
                        add(chBoards = new ChessBoardS(this,ip,pn));}
                        catch(Exception e){}
                        /***********************/

                        //Top Panel (bottom list)
                        JPanel panelTop = new JPanel();
                        panelTop.setPreferredSize(new Dimension(600, 70));
                        panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
                        Border blackline = BorderFactory.createLineBorder(Color.black);
                        panelTop.setBorder(blackline);
                        //Button 1
                        Icon newIcon = new ImageIcon("new.png");
                        JButton button_new = new JButton(newIcon);     
                        button_new.setText("New Game");
                        button_new.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_new.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_new.setOpaque(false);
                        button_new.setContentAreaFilled(false);
                        button_new.setBorderPainted(false);
                        button_new.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                chBoards.newGame();
                            }
                        });

                        panelTop.add(button_new);
                        //Button 2
                        Icon openIcon = new ImageIcon("open.png");
                        JButton button_open = new JButton(openIcon);     
                        button_open.setText("Open File");
                        button_open.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_open.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_open.setOpaque(false);
                        button_open.setContentAreaFilled(false);
                        button_open.setBorderPainted(false);
                        panelTop.add(button_open);
                        //Button 3
                        Icon saveIcon = new ImageIcon("save.png");
                        JButton button_save = new JButton(saveIcon);     
                        button_save.setText("Save Game");
                        button_save.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_save.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_save.setOpaque(false);
                        button_save.setContentAreaFilled(false);
                        button_save.setBorderPainted(false);
                        panelTop.add(button_save);
                        //Button 4
                        Icon undoIcon = new ImageIcon("undo.png");
                        JButton button_undo = new JButton(undoIcon);     
                        button_undo.setText("Undo Move");
                        button_undo.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_undo.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_undo.setOpaque(false);
                        button_undo.setContentAreaFilled(false);
                        button_undo.setBorderPainted(false);
                        button_undo.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                               try{
                                    if(chBoards.movetimess < 1){
                                        Socket socketClient = new Socket("localhost", 9012);
                                        BufferedReader brInFromServer = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                                        DataOutputStream dosOutToServer = new DataOutputStream(socketClient.getOutputStream());
                                        String strSocket = brInFromServer.readLine();
                                        if (strSocket.equals("ok")){
                                                chBoards.undo();
                                        }
                                        else{
                                            socketClient.close();
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "文件損毀。", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                }catch(Exception ee){}
                            }
                        });
                        panelTop.add(button_undo);
                        //
                        String time = "30";
                        JLabel label = new JLabel("");
                        label.setFont(new Font("Arial", Font.BOLD, 20));
                        label.setText(time);
                        //Button 5
                        Icon setIcon = new ImageIcon("setting.png");
                        JButton button_set = new JButton(setIcon);     
                        button_set.setText("Settings");
                        button_set.setVerticalTextPosition(SwingConstants.BOTTOM);
                        button_set.setHorizontalTextPosition(SwingConstants.CENTER);
                        button_set.setOpaque(false);
                        button_set.setContentAreaFilled(false);
                        button_set.setBorderPainted(false);
                        panelTop.add(button_set);

                        //new CountdownPresenterPanel(label, oxBoard);
                        
                        JLabel text = new JLabel(" Remaining Time:");
                        text.setFont(new Font("Arial", Font.PLAIN, 15));
                        chBoards.CountdownPresenterPanel(label);
                        panelTop.add(text);
                        panelTop.add(label);
                        

                        this.getContentPane().add(panelTop, BorderLayout.NORTH);

                        //end of top
                        /***********************/
                        Color c = new Color(139, 69,20);
                        this.getContentPane().setBackground(c); //set background
                        CloseWindow close = new CloseWindow(this, true);

                        addWindowListener(close);
                        pack();
                        setResizable(false);
                        setVisible(true);

                        /////
                    }
          		}
     		} 
        } catch(Exception e3){
            JOptionPane.showMessageDialog(null, "Bye!", "", JOptionPane.INFORMATION_MESSAGE );
            dispose();
        }     
    }

    public static void main(String argv[]) {
        new Othello();
    }
}

class ChessBoard extends Component implements MouseListener, MouseMotionListener, Runnable {
    public int[] board; // 盤面狀況,表達有邊框的10*10盤面
    public int turn, diskdiff; // 現在哪方可下, 與敵方的子數差異
    public String[] text;
    public int black, white;
    public int settime = 30;
    public int movetimes;	//undo times
    public int undototalb;
    public int undototalw;
    private ChessBoard parent; // 由哪一個盤面變化而來
    private double val = -1000000; // 估計此盤面的優勢狀況
    private int hashval; // for hashtable
    private int[] legals; // 儲存此盤面可以下的著手
    private int row, col;
    private int step = 0;
    private boolean readfile = false;
    private int[][] record;
    private String[][] record_text;
    private int currentNumber;
    private boolean move;
    public static final int EMPTY = 0x00; // 空格
    public static final int BLACK = 0x01; // 黑子
    public static final int WHITE = 0x02; // 白子
    public static final int STONE = 0x03; // 上面兩個 or
    public static final int BOUND = 0x04; // 邊界
    public static final int ADEMP = 0x08; // 是否鄰接子的空點
    private static final Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR); // 箭頭游標
    private static final Cursor hintCursor = new Cursor(Cursor.HAND_CURSOR); // 手形游標
    private static final Cursor thinkCursor = new Cursor(Cursor.WAIT_CURSOR); // 漏斗游標
    private static Dimension mySize = new Dimension(440,400); // 固定畫面的大小
    private static JFrame top; // 包含此元件的最上層Frame
    private static Thread thinking; // 計算中的Thread
    private static final byte[] directions = {1,-1,10,-10,9,-9,11,-11}; // 一維陣列下的8個方向
    private static final int HASHSIZE = 63999979; // 小於64M的最大質數
    private static final int MARGIN = 60; //邊界長度
    private static final int GRID_SPAN = 40; //格子大小
    public static int whoPlayBlack, whoPlayWhite;
    public static final int HUMAN = 0, COMPUTER = 1;
    private static int newboard[] = { // 遊戲開始的最初畫面
        BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,ADEMP,ADEMP,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,WHITE,BLACK,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,BLACK,WHITE,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,ADEMP,ADEMP,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND};
    private static String[] xindex = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private static String[] yindex = {"1", "2", "3", "4", "5", "6", "7", "8"};

    public ChessBoard(JFrame p) {

        addMouseListener(this);
        addMouseMotionListener(this);
        top = p;
        board = new int[100];
        record = new int[61][100];
        record_text = new String[61][5];
        System.arraycopy(newboard, 0, board, 0, 100);
        turn = BLACK;
        legals = new int[] {34,43,56,65};
        text = new String[] {null, null, null, null, null};
        move = false;
        currentNumber = settime;
        movetimes = 0;
        undototalb = 0;
    	undototalw = 0;
        
    }
    // 複製p的狀態
    public ChessBoard(ChessBoard p) {
        board = new int[100];
        System.arraycopy(p.board, 0, board, 0, 100);
        turn = p.turn;
        diskdiff = p.diskdiff;
        val = -1000000;
    }
    public void setBlackPlayer(int who) {
        if (whoPlayBlack == who) return;
        if (whoPlayBlack == 0 && thinking == null && (hasLegal(turn) || hasLegal(turn^STONE))) {
            (thinking = new Thread(this)).start();
        }
        whoPlayBlack = who;
    }
    public void setWhitePlayer(int who) {
        if (whoPlayWhite == who) return;
        if (whoPlayWhite == 0 && thinking == null && (hasLegal(turn) || hasLegal(turn^STONE))) {
            (thinking = new Thread(this)).start();
        }
        whoPlayWhite = who;
    }

    // 檢查pos是否合法
    boolean isLegal(int pos) {
        return isLegal(turn, pos);
    }
    // 檢查side這個顏色,能否下在pos
    boolean isLegal(int side, int pos) {
        int opp = side^STONE;
        for (int i = 0, scan; i < 8; i++) {
            scan = pos+directions[i];
            if (board[scan] == opp) {
                    for (scan+=directions[i]; board[scan] == opp; scan+=directions[i]);
                    if ((board[scan] & side) != 0) { // 可夾住對方
                        return true;
                }
            }
        }
        return false;
    }
    // 檢查side是否有合法的著手可下
    boolean hasLegal(int side) {
        for (int i=11; i < 89; i++) {
            if ((board[i]==ADEMP) && isLegal(side, i)) {
                return true;
            }
        }
        return false;
    }
    // 下在pos,並改變盤面結構. 若pos為0, 表示此著手為pass
    boolean addMove(int pos) {

        int opp = turn^STONE;
        if (pos != 0) { // 0 表示pass
            int legal = diskdiff;
            for (int i = 0, scan; i < 8; i++) {
                scan = pos+directions[i];
                if (board[scan] == opp) { // 此方向緊鄰著敵方的子
                    // 跳過連續的敵方子
                    for (scan += directions[i]; board[scan] == opp; scan+=directions[i]);
                    if (board[scan] == turn) { // 可夾住對方
                        // 將所有敵方子變成我方子
                        for (int c = pos+directions[i]; c!=scan ;board[c]=turn, c+=directions[i], diskdiff+=2);
                    }
                }
            }
            if (diskdiff==legal) { // 如果都沒有吃到
                return false;
            }
            diskdiff++;
            board[pos] = turn;
            for (int i = 0; i < 8; i++) { // 設定此點旁的空點為ADEMP
                if (board[pos+directions[i]] == EMPTY) {
                    board[pos+directions[i]] = ADEMP;
                }
            }
        }

        turn = opp; // 換對方下了        
        diskdiff = -diskdiff;
        hashval=(hashval*64+(pos-11))%HASHSIZE;
        return true;
    }
    // Thread的進入點
    public void run() {
        setCursor(thinkCursor);
        for (;;) { // 當敵方需pass時,我方一直下
            if (turn==BLACK && whoPlayBlack == HUMAN) { // 先檢查是否改由人下
                break;
            }
            if (turn==WHITE && whoPlayWhite == HUMAN) { // 先檢查是否改由人下
                break;
            }
            addMove(best());
            repaint(); // ask winder manager to call paint() in another thread
            if (turn==BLACK && whoPlayBlack==HUMAN && hasLegal(turn)) { // 人可以下了
                break;
            }
            if (turn==WHITE && whoPlayWhite==HUMAN && hasLegal(turn)) { // 人可以下了
                break;
            }
            if (!hasLegal(turn) && !hasLegal(turn^STONE)) { // 對手和自己都不能下了
                //new ErrorDialog(top, "Game Over");
                if (black > white){
                    JOptionPane.showMessageDialog(null, "Black Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                }
                else if (black == white) {
                    JOptionPane.showMessageDialog(null, "Tie!", "Tie", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    JOptionPane.showMessageDialog(null, "White Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                }
                break;
            }
            if (!hasLegal(turn)) {
                addMove(0);
            }
        }
        setCursor(normalCursor);
        thinking = null;
    }
    // The following 2 methods implement the MouseMotionListener interface
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {

        if (thinking != null) return;
        row = (e.getY()-MARGIN)/GRID_SPAN;
        col = (e.getX()-MARGIN)/GRID_SPAN;
        if (row >= 8 || col >= 8) {
            setCursor(normalCursor);
            return; // 超過邊界
        }
        int pos = row*10 + col + 11;
        if (board[pos]==ADEMP && isLegal(turn, pos)) {
            setCursor(hintCursor);
        } else {
            setCursor(normalCursor);
        }
    }
    // The following 5 methods implement the MouseListener interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {

        //改鼠標位置
        row = (e.getY()-MARGIN)/GRID_SPAN;
        col = (e.getX()-MARGIN)/GRID_SPAN;

        if (row >= 8 || col >= 8) return; // 超過邊界
        if (thinking != null) return; // 電腦思考中
        int pos = row*10+col+11;
        if (board[pos] == ADEMP && addMove(pos)) { // 此位置可以下
            
            move = true;
            if(movetimes>0){
            	movetimes--;
            }
            repaint();

            
            if (hasLegal(turn)) {
                if ((turn==WHITE && whoPlayWhite==COMPUTER) || (turn==BLACK && whoPlayBlack==COMPUTER)) { // let computer play
                    (thinking = new Thread(this)).start();
                }
            } else {
                if (!hasLegal(turn^STONE)) { // 雙方都不能下
                    if (black > white){
                        JOptionPane.showMessageDialog(null, "Black Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                    }
                    else if (black == white) {
                        JOptionPane.showMessageDialog(null, "Tie!", "Tie", JOptionPane.INFORMATION_MESSAGE );
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "White Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                    }
                    return;
                }
                addMove(0); // 對方不能下,force pass
            }
        }

    }
    // 棋力強弱關鍵的求值函數
    private void eval() {
        val = diskdiff;
    }
    private void alphaBeta(int level) {
        if (legals == null) {
            findLegals();
        }
        for (int i=0; i<legals.length; i++) {
            ChessBoard tmp = new ChessBoard(this);
            tmp.addMove(legals[i]);
            if (level<1) {
                tmp.eval();
            } else {
                tmp.alphaBeta(level-1);
            }
            // alphaBeta cut
            if (val < -tmp.val) {
                val = -tmp.val;
                for (ChessBoard p = parent; p != null;) {
                    if (val >= -p.val) { // 對手不會選擇這條路的
                        return;
                    }
                    // 往上跳兩層
                    p = p.parent;
                    if (p != null) p = p.parent;
                }
            }
        }
    }
    private void findLegals() {
        int count = 0;
        int[] tmp = new int[60];
        for (int i=11; i<89; i++) {
            if (board[i]==ADEMP && isLegal(turn, i)) {
                tmp[count++] = i;
            }
        }
        legals = new int[count];
        System.arraycopy(tmp, 0, legals, 0, count);
    }
    private int best() {
        int bestMove = 0;
        findLegals();
        val = -100000000;
        for (int i=0; i<legals.length; i++) {
            ChessBoard tmp = new ChessBoard(this);
            tmp.addMove(legals[i]);
            tmp.alphaBeta(3);
            if (-tmp.val > val) {
                bestMove = legals[i];
                val = -tmp.val;
            }
        }
        return bestMove;
    }

    // override paint() defined in Component
    public void paint(Graphics g) {

        //int black, white;
        black = white = 0;
        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++){
            int mul = 35+(i+1)*GRID_SPAN;
            g.drawString(xindex[i], mul, 55);
        }
        for (int i = 0; i < 8; i++){
            int mul = 43+(i+1)*GRID_SPAN;
            g.drawString(yindex[i], 45, mul);
        }
        for (int i = 0; i <= 8; i++) {  // draw grids
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+320, MARGIN+i*GRID_SPAN);
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+320);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int pos = i*10 + j + 11;
                if ((board[pos] & BLACK) != 0) {
                    g.setColor(Color.BLACK);
                    g.fillOval(MARGIN+j*GRID_SPAN, MARGIN+i*GRID_SPAN, GRID_SPAN, GRID_SPAN);
                    black++;
                } else if ((board[pos] & WHITE) != 0) {
                    g.setColor(Color.WHITE);
                    g.fillOval(MARGIN+j*GRID_SPAN, MARGIN+i*GRID_SPAN, GRID_SPAN, GRID_SPAN);
                    white++;
                }
                if (board[pos]==ADEMP && isLegal(turn, pos)) { //顯示當且將要落子方可以落子的位置
                    g.setColor(Color.RED);
                    g.fillOval(MARGIN-5+GRID_SPAN/2+j*GRID_SPAN, MARGIN-5+GRID_SPAN/2+i*GRID_SPAN, 10, 10);
                } 
            }
        }
        //顯示數量
        g.setColor(new Color(240,240,240));
        g.fillRect(440, 0, 159, 500);
        g.setColor(Color.BLACK);
        g.fillOval(450, 20, GRID_SPAN-2, GRID_SPAN-2);
        g.setColor(Color.WHITE);
        g.fillOval(450, 65, GRID_SPAN-2, GRID_SPAN-2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30)); 
        g.drawString(""+black, 520, 47);
        g.drawString(""+white, 520, 94);
        g.setColor(Color.WHITE);
        g.fillRect(450, 120, 139, 250);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14)); 
        g.drawString("Last Four Steps:", 452, 135);

        
        if((step != 0) && (!readfile)){
            if(turn == 1)
                text[text.length-1] = "White: " + xindex[col]+yindex[row];
            else
                text[text.length-1] = "Black: " + xindex[col]+yindex[row];
        }
        readfile = false;
        for (int i = 0;i<text.length;i++){
            if(text[i] != null){
                if (i == 4)
                    g.drawString(text[i]+" (NOW)", 452, 160+i*20);
                if (i == 3)
                    g.drawString(text[i]+" (Last 1st)", 452, 160+i*20);
                if (i == 2)
                    g.drawString(text[i]+" (Last 2nd)", 452, 160+i*20);
                if (i == 1)
                    g.drawString(text[i]+" (Last 3rd)", 452, 160+i*20);
                if (i == 0)
                    g.drawString(text[i]+" (Last 4th)", 452, 160+i*20);
            }
        }

        for (int i = 0;i<text.length;i++){
            record_text[step][i] = text[i];
        }

        for (int i = 0;i<board.length;i++){
            record[step][i] = board[i];
        }

        String tmp = text[3];
        text[3] = text[4];
        String tmp1 = text[2];
        text[2] = tmp;
        String tmp2 = text[1];
        text[1] = tmp1;
        text[0] = tmp2;

        step++;

    }
    public void newGame() {
        System.arraycopy(newboard, 0, board, 0, 100);
        turn = BLACK;
        hashval = diskdiff = 0;
        text = new String[] {null, null, null, null, null};
        step = 0;
        movetimes = 0;
        undototalb = 0;
    	undototalw = 0;
        currentNumber = settime;

        if (thinking != null) {
            try {
                thinking.join();
            } catch(Exception epp) {}
        }
        if (whoPlayBlack == COMPUTER) {
            (thinking = new Thread(this)).start();
        }
        repaint();
    }

    public void readTxt(String filename){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String s = reader.readLine();
            if (s.equals("###LogFileofOthella###")){
                s = reader.readLine();
                s = reader.readLine();
                String[] str = s.split("");
                if(str.length == board.length){
                    for (int i = 0;i<str.length;i++){
                        board[i] = Integer.parseInt(str[i]);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "The file may be damaged", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                s = reader.readLine();
                String[] str2 = s.split(",");
                if(str2.length == text.length){
                    for (int i = 0;i<text.length;i++){
                        text[i] = str2[i];
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "The file may be damaged", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                s = reader.readLine();
                black = Integer.parseInt(s);
                s = reader.readLine();
                white = Integer.parseInt(s);
                s = reader.readLine();
                turn = Integer.parseInt(s);
                readfile = true;
                repaint();
            }
            else{
                JOptionPane.showMessageDialog(null, "Does not support the file type", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't find the file", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void undo() {
        int last = step - 2;
        if (last >= 0){
            int[] lastb = new int[100];
            String[] lastt = new String[5];
            for (int i = 0;i<board.length;i++){
                lastb[i] = record[last][i];
            }
            for (int i = 0;i<text.length;i++){
                lastt[i] = record_text[last][i];
            }
            System.arraycopy(lastb, 0, board, 0, 100);
            System.arraycopy(lastt, 0, text, 0, 5);
            step = last;
            readfile = true;
            if (turn == BLACK){
            	undototalb++;
            }
            else{
            	undototalw++;
            }
            int opp = turn^STONE;
            turn = opp;
            movetimes++;
            repaint();
        }
        else{
            System.out.println("no move");
        }

    }

    public void CountdownPresenterPanel(JLabel textLabel) {
        Timer timer = new Timer(1000, new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                if((currentNumber > 0)&&(!move)) {
                    currentNumber--;
                    textLabel.setText(String.valueOf(currentNumber));
                }
                else if ((currentNumber > 0)&&(move)){
                	currentNumber = settime;
                	move = false;
                	textLabel.setText(String.valueOf(currentNumber));
                } 
                else {
                    System.out.println("time out");
                    if (addMove(0)){
                        readfile = true;
                    	repaint();
                    }
                    currentNumber = settime;
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    // override getPreferredSize defined in java.lang.Component,
    // so that the Component has proper size on screen
    public Dimension getPreferredSize() {
        return mySize;
    }
    // override hashCode() in java.lang.Object
    public int hashCode() {
        return hashval;
    }
    public boolean equals(Object o) {
        if (!(o instanceof ChessBoard)) return false;
        ChessBoard t = (ChessBoard) o;
        for (int i=11; i<89; i++) {
            if (board[i] != t.board[i]) return false;
        }
        return true;
    }
}

// WindowAdapter implements the WindowLister interface
// We extends WindowAdapter to reduce the line numer of code
class CloseWindow extends WindowAdapter implements ActionListener {
    private Window target;
    private boolean exit;
    public CloseWindow(Window target, boolean exit) {
        this.target = target;
        this.exit = exit;
    }
    public CloseWindow(Window target) {
        this.target = target;
    }
    public void windowClosing(WindowEvent e) {
        target.dispose();
        if (exit) System.exit(0);
    }
    public void actionPerformed(ActionEvent e) {
        target.dispose();
        if (exit) System.exit(0);
    }
}

class AddConstraint {
    public static void addConstraint(Container container, Component component,
          int grid_x, int grid_y, int grid_width, int grid_height,
          int fill, int anchor, double weight_x, double weight_y,
          int top, int left, int bottom, int right) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = grid_x; c.gridy = grid_y;
        c.gridwidth = grid_width; c.gridheight = grid_height;
        c.fill = fill; c.anchor = anchor;
        c.weightx = weight_x; c.weighty = weight_y;
        c.insets = new Insets(top,left,bottom,right);
        ((GridBagLayout)container.getLayout()).setConstraints(component,c);
        container.add(component);
    }
}

class WriteFile{
    
    private String filename;
    private int[] board_array;
    private String[] status;
    private int bn, wn, turn;
    public WriteFile(String name, int[] board_a, String[] status, int bn, int wn, int turn){
        this.filename = name;
        this.board_array = board_a;
        this.status = status;
        this.bn = bn;
        this.wn = wn;
        this.turn = turn;
        writerTxt();
    }
    private void writerTxt(){
        BufferedWriter writer = null;
        try{
            File f = new File(filename);
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false))); //overwrite
            writer.append("###LogFileofOthella###"); // to tell if this is only for Othella
            writer.newLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date now = new Date();
            String sdfDate = sdf.format(now);
            writer.append(sdfDate);
            writer.newLine();
            for (int i = 0;i<board_array.length;i++){
                writer.append(String.valueOf(board_array[i]));
            }
            writer.newLine();
            for (int i = 0;i<status.length;i++){
                if (i == status.length-1){
                    writer.append(status[i]);
                }
                else{
                    writer.append(status[i]+",");
                }
            }
            writer.newLine();
            writer.append(String.valueOf(bn));
            writer.newLine();
            writer.append(String.valueOf(wn));
            writer.newLine();
            writer.append(String.valueOf(turn));
            writer.flush();
            JOptionPane.showMessageDialog(null, "Successfully!", "Successfully Saved", JOptionPane.INFORMATION_MESSAGE );
            //System.out.println("存檔成功");
        }catch (IOException e) {}
    }
}

class FileChooser extends JPanel{
    JFileChooser fc;

    public FileChooser() {
        //Create a file chooser
        fc = new JFileChooser(System.getProperty("user.dir")); //current dir
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }

    public String getFileName() {

        int returnVal = fc.showOpenDialog(FileChooser.this);
 
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            return file.getName();
        } else {
            return null;
        }
    }
}

class ChessBoardC extends Component implements MouseListener, MouseMotionListener, Runnable {
    public int[] board; // 盤面狀況,表達有邊框的10*10盤面
    public int turn, diskdiff; // 現在哪方可下, 與敵方的子數差異
    public String[] text;
    public int black, white;
    public int movetimes;   
    private ChessBoardC parent; // 由哪一個盤面變化而來
    private double val = -1000000; // 估計此盤面的優勢狀況
    private int hashval; // for hashtable
    private int[] legals; // 儲此盤可以下的著手
    private int row, col;
    private int step = 0;
    private boolean readfile = false;
    private int[][] record;
    private String[][] record_text;
    private int postmp = 1000; //for socket
    public static final int EMPTY = 0x00; // 空格
    public static final int BLACK = 0x01; // 黑子
    public static final int WHITE = 0x02; // 白子
    public static final int STONE = 0x03; // 上面兩個 or
    public static final int BOUND = 0x04; // 邊界
    public static final int ADEMP = 0x08; // 是否鄰接子的空點
    private static final Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR); // 箭頭游標
    private static final Cursor hintCursor = new Cursor(Cursor.HAND_CURSOR); // 手形游標
    private static final Cursor thinkCursor = new Cursor(Cursor.WAIT_CURSOR); // 漏斗游標
    private static Dimension mySize = new Dimension(440,400); // 固定畫面的大小
    private static JFrame top; // 包含此元件的最上層Frame
    private static Thread thinking; // 計算中的Thread
    private static final byte[] directions = {1,-1,10,-10,9,-9,11,-11}; // 一維陣列下的8個方向
    private static final int HASHSIZE = 63999979; // 小於64M的最大質數
    private static final int MARGIN = 60; //邊界長度
    private static final int GRID_SPAN = 40; //格子大小
    public static int whoPlayBlack, whoPlayWhite;
    public static final int HUMAN = 0, COMPUTER = 1;
    private static int newboard[] = { // 遊戲開始的最初畫面
        BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,ADEMP,ADEMP,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,WHITE,BLACK,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,BLACK,WHITE,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,ADEMP,ADEMP,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND};
    private static String[] xindex = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private static String[] yindex = {"1", "2", "3", "4", "5", "6", "7", "8"};

    public int currentNumber;
    private boolean move;

    ClientO c;
    UndoServerC uc = new UndoServerC();


    public ChessBoardC(JFrame p, String ip, String pn){

        addMouseListener(this);
        addMouseMotionListener(this);
        top = p;
        board = new int[100];
        record = new int[60][100];
        record_text = new String[60][5];
        System.arraycopy(newboard, 0, board, 0, 100);
        turn = BLACK;
        legals = new int[] {34,43,56,65};
        text = new String[] {null, null, null, null, null};
        move = false;
        currentNumber = 30;
        movetimes = 0;

        try{
        c = new ClientO(ip, pn);
        new Thread(c).start();
        new Thread(uc).start();
        }catch(Exception e){}
        
    }
    // 複製p的狀態
    public ChessBoardC(ChessBoardC p) {
        board = new int[100];
        System.arraycopy(p.board, 0, board, 0, 100);
        turn = p.turn;
        diskdiff = p.diskdiff;
        val = -1000000;
    }
    public void setBlackPlayer(int who) {
        if (whoPlayBlack == who) return;
        if (whoPlayBlack == 0 && thinking == null && (hasLegal(turn) || hasLegal(turn^STONE))) {
            (thinking = new Thread(this)).start();
        }
        whoPlayBlack = who;
    }
    public void setWhitePlayer(int who) {
        if (whoPlayWhite == who) return;
        if (whoPlayWhite == 0 && thinking == null && (hasLegal(turn) || hasLegal(turn^STONE))) {
            (thinking = new Thread(this)).start();
        }
        whoPlayWhite = who;
    }

    // 檢查pos是否合法
    boolean isLegal(int pos) {
        return isLegal(turn, pos);
    }
    // 檢查side這個顏色,能否下在pos
    boolean isLegal(int side, int pos) {
        int opp = side^STONE;
        for (int i = 0, scan; i < 8; i++) {
            scan = pos+directions[i];
            if (board[scan] == opp) {
                    for (scan+=directions[i]; board[scan] == opp; scan+=directions[i]);
                    if ((board[scan] & side) != 0) { // 可夾住對方
                        return true;
                }
            }
        }
        return false;
    }
    // 檢查side是否有合法的著手可下
    boolean hasLegal(int side) {
        for (int i=11; i < 89; i++) {
            if ((board[i]==ADEMP) && isLegal(side, i)) {
                return true;
            }
        }
        return false;
    }
    // 下在pos,並改變盤面結構. 若pos為0, 表示此著手為pass
    boolean addMove(int pos) {

        int opp = turn^STONE;
        if (pos != 0) { // 0 表示pass
            int legal = diskdiff;
            for (int i = 0, scan; i < 8; i++) {
                scan = pos+directions[i];
                if (board[scan] == opp) { // 此方向緊鄰著敵方的子
                    // 跳過連續的敵方子
                    for (scan += directions[i]; board[scan] == opp; scan+=directions[i]);
                    if (board[scan] == turn) { // 可夾住對方
                        // 將所有敵方子變成我方子
                        for (int c = pos+directions[i]; c!=scan ;board[c]=turn, c+=directions[i], diskdiff+=2);
                    }
                }
            }
            if (diskdiff==legal) { // 如果都沒有吃到
                return false;
            }
            diskdiff++;
            board[pos] = turn;
            for (int i = 0; i < 8; i++) { // 設定此點旁的空點為ADEMP
                if (board[pos+directions[i]] == EMPTY) {
                    board[pos+directions[i]] = ADEMP;
                }
            }
        }

        turn = opp; // 換對方下了        
        diskdiff = -diskdiff;
        hashval=(hashval*64+(pos-11))%HASHSIZE;
        return true;
    }
    // Thread的進入點
    public void run() {
        setCursor(thinkCursor);
        for (;;) { // 當敵方需pass時,我方一直下
            if (turn==BLACK && whoPlayBlack == HUMAN) { // 先檢查是否改由人下
                break;
            }
            if (turn==WHITE && whoPlayWhite == HUMAN) { // 先檢查是否改由人下
                break;
            }
            addMove(best());
            repaint(); // ask winder manager to call paint() in another thread
            if (turn==BLACK && whoPlayBlack==HUMAN && hasLegal(turn)) { // 人可以下了
                break;
            }
            if (turn==WHITE && whoPlayWhite==HUMAN && hasLegal(turn)) { // 人可以下了
                break;
            }
            if (!hasLegal(turn) && !hasLegal(turn^STONE)) { // 對手和自己都不能下了
                if (black > white){
                    JOptionPane.showMessageDialog(null, "Black Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                }
                else if (black == white) {
                    JOptionPane.showMessageDialog(null, "Tie!", "Tie", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    JOptionPane.showMessageDialog(null, "White Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                }
                break;
            }
            if (!hasLegal(turn)) {
                addMove(0);
            }
        }
        setCursor(normalCursor);
        thinking = null;
    }
    // The following 2 methods implement the MouseMotionListener interface
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {

        if (thinking != null) return;
        row = (e.getY()-MARGIN)/GRID_SPAN;
        col = (e.getX()-MARGIN)/GRID_SPAN;
        if (row >= 8 || col >= 8) {
            setCursor(normalCursor);
            return; // 超過邊界
        }
        int pos = row*10 + col + 11;
        if (board[pos]==ADEMP && isLegal(turn, pos)) {
            setCursor(hintCursor);
        } else {
            setCursor(normalCursor);
        }
    }
    // The following 5 methods implement the MouseListener interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {

        //改鼠標位置
        row = (e.getY()-MARGIN)/GRID_SPAN;
        col = (e.getX()-MARGIN)/GRID_SPAN;

        if (row >= 8 || col >= 8) return; // 超過邊界
        if (thinking != null) return; // 電腦思考中
        int pos = row*10+col+11;
        //
        if (turn == WHITE){
            if (board[pos] == ADEMP && addMove(pos)) { // 此位置可以下
                
                move = true;
                if(movetimes>0){
                    movetimes--;
                }
                repaint();

                
                if (hasLegal(turn)) {
                    if ((turn==WHITE && whoPlayWhite==COMPUTER) || (turn==BLACK && whoPlayBlack==COMPUTER)) { // let computer play
                        (thinking = new Thread(this)).start();
                    }
                } else {
                    if (!hasLegal(turn^STONE)) { // 雙方都不能下
                        if (black > white){
                            JOptionPane.showMessageDialog(null, "Black Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                        }
                        else if (black == white) {
                            JOptionPane.showMessageDialog(null, "Tie!", "Tie", JOptionPane.INFORMATION_MESSAGE );
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "White Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                        }
                        return;
                    }
                    addMove(0); // 對方不能下,force pass
                }
            }
        

        try{
        c.setvalue(row, col);
        }
        catch(Exception ee){}
        }

    }
    // 棋力強弱關鍵的求值函數
    private void eval() {
        val = diskdiff;
    }
    private void alphaBeta(int level) {
        if (legals == null) {
            findLegals();
        }
        for (int i=0; i<legals.length; i++) {
            ChessBoardC tmp = new ChessBoardC(this);
            tmp.addMove(legals[i]);
            if (level<1) {
                tmp.eval();
            } else {
                tmp.alphaBeta(level-1);
            }
            // alphaBeta cut
            if (val < -tmp.val) {
                val = -tmp.val;
                for (ChessBoardC p = parent; p != null;) {
                    if (val >= -p.val) { // 對手不會選擇這條路的
                        return;
                    }
                    // 往上跳兩層
                    p = p.parent;
                    if (p != null) p = p.parent;
                }
            }
        }
    }
    private void findLegals() {
        int count = 0;
        int[] tmp = new int[60];
        for (int i=11; i<89; i++) {
            if (board[i]==ADEMP && isLegal(turn, i)) {
                tmp[count++] = i;
            }
        }
        legals = new int[count];
        System.arraycopy(tmp, 0, legals, 0, count);
    }
    private int best() {
        int bestMove = 0;
        findLegals();
        val = -100000000;
        for (int i=0; i<legals.length; i++) {
            ChessBoardC tmp = new ChessBoardC(this);
            tmp.addMove(legals[i]);
            tmp.alphaBeta(3);
            if (-tmp.val > val) {
                bestMove = legals[i];
                val = -tmp.val;
            }
        }
        return bestMove;
    }

    // override paint() defined in Component
    public void paint(Graphics g) {

        //int black, white;
        black = white = 0;
        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++){
            int mul = 35+(i+1)*GRID_SPAN;
            g.drawString(xindex[i], mul, 55);
        }
        for (int i = 0; i < 8; i++){
            int mul = 43+(i+1)*GRID_SPAN;
            g.drawString(yindex[i], 45, mul);
        }
        for (int i = 0; i <= 8; i++) {  // draw grids
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+320, MARGIN+i*GRID_SPAN);
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+320);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int pos = i*10 + j + 11;
                if ((board[pos] & BLACK) != 0) {
                    g.setColor(Color.BLACK);
                    g.fillOval(MARGIN+j*GRID_SPAN, MARGIN+i*GRID_SPAN, GRID_SPAN, GRID_SPAN);
                    black++;
                } else if ((board[pos] & WHITE) != 0) {
                    g.setColor(Color.WHITE);
                    g.fillOval(MARGIN+j*GRID_SPAN, MARGIN+i*GRID_SPAN, GRID_SPAN, GRID_SPAN);
                    white++;
                }
                if (board[pos]==ADEMP && isLegal(turn, pos)) { //顯示當且將要落子方可以落子的位置
                    g.setColor(Color.RED);
                    g.fillOval(MARGIN-5+GRID_SPAN/2+j*GRID_SPAN, MARGIN-5+GRID_SPAN/2+i*GRID_SPAN, 10, 10);
                } 
            }
        }
        //顯示數量
        g.setColor(new Color(240,240,240));
        g.fillRect(440, 0, 159, 500);
        g.setColor(Color.BLACK);
        g.fillOval(450, 20, GRID_SPAN-2, GRID_SPAN-2);
        g.setColor(Color.WHITE);
        g.fillOval(450, 65, GRID_SPAN-2, GRID_SPAN-2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30)); 
        g.drawString(""+black, 520, 47);
        g.drawString(""+white, 520, 94);
        //g.setColor(Color.WHITE);
        //g.fillRect(450, 120, 139, 250);
        //g.setColor(Color.BLACK);
        //g.setFont(new Font("Arial", Font.BOLD, 14)); 
        //g.drawString("Last Four Steps:", 452, 135);

        
        if((step != 0) && (!readfile)){
            if(turn == 1)
                text[text.length-1] = "White: " + xindex[col]+yindex[row];
            else
                text[text.length-1] = "Black: " + xindex[col]+yindex[row];
        }
        readfile = false;
        /*
        for (int i = 0;i<text.length;i++){
            if(text[i] != null){
                if (i == 4)
                    g.drawString(text[i]+" (NOW)", 452, 160+i*20);
                if (i == 3)
                    g.drawString(text[i]+" (Last 1st)", 452, 160+i*20);
                if (i == 2)
                    g.drawString(text[i]+" (Last 2nd)", 452, 160+i*20);
                if (i == 1)
                    g.drawString(text[i]+" (Last 3rd)", 452, 160+i*20);
                if (i == 0)
                    g.drawString(text[i]+" (Last 4th)", 452, 160+i*20);
            }
        }*/

        for (int i = 0;i<text.length;i++){
            record_text[step][i] = text[i];
        }

        for (int i = 0;i<board.length;i++){
            record[step][i] = board[i];
        }

        String tmp = text[3];
        text[3] = text[4];
        String tmp1 = text[2];
        text[2] = tmp;
        String tmp2 = text[1];
        text[1] = tmp1;
        text[0] = tmp2;

        step++;

    }
    public void newGame() {
        System.arraycopy(newboard, 0, board, 0, 100);
        turn = BLACK;
        hashval = diskdiff = 0;
        text = new String[] {null, null, null, null, null};
        step = 0;
        movetimes = 0;
        currentNumber = c.gettime();

        if (thinking != null) {
            try {
                thinking.join();
            } catch(Exception epp) {}
        }
        if (whoPlayBlack == COMPUTER) {
            (thinking = new Thread(this)).start();
        }
        repaint();
    }

    public void undo() {
        int last = step - 2;
        if (last >= 0){
            int[] lastb = new int[100];
            String[] lastt = new String[5];
            for (int i = 0;i<board.length;i++){
                lastb[i] = record[last][i];
            }
            for (int i = 0;i<text.length;i++){
                lastt[i] = record_text[last][i];
            }
            System.arraycopy(lastb, 0, board, 0, 100);
            System.arraycopy(lastt, 0, text, 0, 5);
            step = last;
            readfile = true;
            int opp = turn^STONE;
            turn = opp;
            movetimes++;
            repaint();
        }
        else{
            System.out.println("no move");
        }

    }

    public void CountdownPresenterPanel(JLabel textLabel) {
        Timer timer = new Timer(1000, new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                int mytime = c.gettime();
                //
                if(c.getsyn()){
                    int pos = c.getvalue();
                    if (pos != postmp){
                        System.out.println(pos);
                        if(pos != 0){
                            if (board[pos] == ADEMP && addMove(pos)){
                                readfile = true;
                                repaint();
                                currentNumber = mytime;
                                textLabel.setText(String.valueOf(currentNumber));
                            }
                            postmp = pos;
                        }
                    }
                    //
                    if (currentNumber <= 0){
                        System.out.println("hi"+currentNumber);
                        if (addMove(0)){
                            readfile = true;
                            repaint();
                        }
                        currentNumber = mytime;
                        textLabel.setText(String.valueOf(currentNumber));
                    }
                    else{
                        currentNumber--;
                        textLabel.setText(String.valueOf(currentNumber));
                    }
                    if (uc.getundo()){
                        undo();
                    }
                }
                else{
                    currentNumber = c.gettime();
                    mytime = c.gettime();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    // override getPreferredSize defined in java.lang.Component,
    // so that the Component has proper size on screen
    public Dimension getPreferredSize() {
        return mySize;
    }
    // override hashCode() in java.lang.Object
    public int hashCode() {
        return hashval;
    }
    public boolean equals(Object o) {
        if (!(o instanceof ChessBoardC)) return false;
        ChessBoardC t = (ChessBoardC) o;
        for (int i=11; i<89; i++) {
            if (board[i] != t.board[i]) return false;
        }
        return true;
    }
}

class ClientO implements Runnable {
    private int rrow, rcol;
    private int srow, scol;
    private int pos = 0;
    private String strLocal, strSocket;
    private boolean syn;
    private int time;
    private String ipadd;
    private int pnn;

    public ClientO(String ip, String pn){
        this.srow = 100;
        this.scol = 100;
        this.ipadd = ip;
        this.pnn = Integer.parseInt(pn);
        syn = false;
    }
    
    public void setvalue(int r, int c){
        this.srow = r;
        this.scol = c;
    }

    public void resetvalue(int r, int c){
        this.rrow = r;
        this.rcol = c;
    }

    public void run(){
        try{
            Socket socketClient = new Socket(ipadd, pnn);
            BufferedReader brInFromServer = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            DataOutputStream dosOutToServer = new DataOutputStream(socketClient.getOutputStream());
            //先傳給Server
            String sf = "hi";
            BufferedReader brInFromUser = new BufferedReader(new StringReader(sf));
            strLocal = brInFromUser.readLine();
            dosOutToServer.writeBytes(strLocal+'\n');
            //收到時間確認
            strSocket = brInFromServer.readLine();
            int opt = JOptionPane.showConfirmDialog(null,"對方設置思考時間為"+strSocket,"確認",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.INFORMATION_MESSAGE);
            //回覆ok
            if (opt == JOptionPane.YES_OPTION) {
                sf = "ok";
                brInFromUser = new BufferedReader(new StringReader(sf));
                strLocal = brInFromUser.readLine();
                dosOutToServer.writeBytes(strLocal+'\n');
                //set time
                time = Integer.parseInt(strSocket);
                Thread.sleep(1000);
                syn = true;

            }
            //回覆不ok
            else {
                sf = "ok";
                brInFromUser = new BufferedReader(new StringReader(sf));
                strLocal = brInFromUser.readLine();
                dosOutToServer.writeBytes(strLocal+'\n');
            } 
            //收到開始比賽
            //Thread.sleep(1000);
            strSocket = brInFromServer.readLine();
            System.out.println("Server: "+strSocket);
            //JOptionPane.showMessageDialog(null,"Game Start!", "Start", JOptionPane.INFORMATION_MESSAGE );//
            if(syn){
                while(true){
                    //傳data
                    String str = String.valueOf(srow)+" "+String.valueOf(scol);
                    //System.out.println(str);
                    brInFromUser = new BufferedReader(new StringReader(str));
                    strLocal = brInFromUser.readLine();
                    dosOutToServer.writeBytes(strLocal+'\n');

            
                    //讀data
                    strSocket = brInFromServer.readLine();
                    //System.out.println("Server: "+strSocket);
                    String[] st = strSocket.split(" ");
                    rrow = Integer.parseInt(st[0]);
                    rcol = Integer.parseInt(st[1]);
                    pos = rrow*10 + rcol + 11;
                }
            }
            else{
                socketClient.close();
            }
        }catch(Exception e){}
    }
    public int getvalue(){
        if (pos > 1000)
            return 0;
        else
            return pos;
    }
    public boolean getsyn(){
        return syn;
    }
    public int gettime(){
        return time;
    }
}

class UndoServerC implements Runnable{
    private String strLocal, strSocket;
    private boolean undo;
    public void run(){
        try{
            ServerSocket ssocketServer = new ServerSocket(9012);
            while(true){
                Socket undoServer = ssocketServer.accept();
                DataOutputStream toClient = new DataOutputStream(undoServer.getOutputStream());
                int opt = JOptionPane.showConfirmDialog(null,"Opponent ask for undo","Undo",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.INFORMATION_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    //sent ok
                    toClient.writeBytes("ok"+'\n');
                    undo = true;
                }
                else {
                    //sent no
                    toClient.writeBytes("no"+'\n');
                }
                //System.out.println("undo:建立連線");
                //Client先傳來
                //BufferedReader fromClient = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
                
            }
            //undoServer.close();

        }catch(Exception e){}
    }
    public boolean getundo(){
        boolean re = undo;
        undo = false;
        return re;
    }
}

class ChessBoardS extends Component implements MouseListener, MouseMotionListener, Runnable {
    public int[] board; // 盤面狀況,表達有邊框的10*10盤面
    public int turn, diskdiff; // 現在哪方可下, 與敵方的子數差異
    public String[] text;
    public int black, white;
    public int movetimess;
    private ChessBoardS parent; // 由哪一個盤面變化而來
    private double val = -1000000; // 估計此盤面的優勢狀況
    private int hashval; // for hashtable
    private int[] legals; // 儲存此盤面可以下的著手
    private int row, col;
    private int step = 0;
    private boolean readfile = false;
    private int[][] record;
    private String[][] record_text;
    private int postmp = 1000; //for socket
    public static final int EMPTY = 0x00; // 空格
    public static final int BLACK = 0x01; // 黑子
    public static final int WHITE = 0x02; // 白子
    public static final int STONE = 0x03; // 上面兩個 or
    public static final int BOUND = 0x04; // 邊界
    public static final int ADEMP = 0x08; // 是否鄰接子的空點
    private static final Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR); // 箭頭游標
    private static final Cursor hintCursor = new Cursor(Cursor.HAND_CURSOR); // 手形游標
    private static final Cursor thinkCursor = new Cursor(Cursor.WAIT_CURSOR); // 漏斗游標
    private static Dimension mySize = new Dimension(440,400); // 固定畫面的大小
    private static JFrame top; // 包含此元件的最上層Frame
    private static Thread thinking; // 計算中的Thread
    private static final byte[] directions = {1,-1,10,-10,9,-9,11,-11}; // 一維陣列下的8個方向
    private static final int HASHSIZE = 63999979; // 小於64M的最大質數
    private static final int MARGIN = 60; //邊界長度
    private static final int GRID_SPAN = 40; //格子大小
    public static int whoPlayBlack, whoPlayWhite;
    public static final int HUMAN = 0, COMPUTER = 1;
    private static int newboard[] = { // 遊戲開始的最初畫面
        BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,ADEMP,ADEMP,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,WHITE,BLACK,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,BLACK,WHITE,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,ADEMP,ADEMP,ADEMP,ADEMP,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,BOUND,
        BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND,BOUND};
    private static String[] xindex = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private static String[] yindex = {"1", "2", "3", "4", "5", "6", "7", "8"};

    public int currentNumber;
    private boolean move;

    ServerO s;
    UndoServerS us = new UndoServerS();

    public ChessBoardS(JFrame p, String ip, String pn){

        addMouseListener(this);
        addMouseMotionListener(this);
        top = p;
        board = new int[100];
        record = new int[60][100];
        record_text = new String[60][5];
        System.arraycopy(newboard, 0, board, 0, 100);
        turn = BLACK;
        legals = new int[] {34,43,56,65};
        text = new String[] {null, null, null, null, null};
        move = false;
        currentNumber = 30;
        movetimess = 0;

        try{
        s = new ServerO(ip, pn, this);
        new Thread(s).start();
        new Thread(us).start();
        }catch(Exception e){}

        
    }
    // 複製p的狀態
    public ChessBoardS(ChessBoardS p) {
        board = new int[100];
        System.arraycopy(p.board, 0, board, 0, 100);
        turn = p.turn;
        diskdiff = p.diskdiff;
        val = -1000000;
    }
    public void setBlackPlayer(int who) {
        if (whoPlayBlack == who) return;
        if (whoPlayBlack == 0 && thinking == null && (hasLegal(turn) || hasLegal(turn^STONE))) {
            (thinking = new Thread(this)).start();
        }
        whoPlayBlack = who;
    }
    public void setWhitePlayer(int who) {
        if (whoPlayWhite == who) return;
        if (whoPlayWhite == 0 && thinking == null && (hasLegal(turn) || hasLegal(turn^STONE))) {
            (thinking = new Thread(this)).start();
        }
        whoPlayWhite = who;
    }

    // 檢查pos是否合法
    boolean isLegal(int pos) {
        return isLegal(turn, pos);
    }
    // 檢查side這個顏色,能否下在pos
    boolean isLegal(int side, int pos) {
        int opp = side^STONE;
        for (int i = 0, scan; i < 8; i++) {
            scan = pos+directions[i];
            if (board[scan] == opp) {
                    for (scan+=directions[i]; board[scan] == opp; scan+=directions[i]);
                    if ((board[scan] & side) != 0) { // 可夾住對方
                        return true;
                }
            }
        }
        return false;
    }
    // 檢查side是否有合法的著手可下
    boolean hasLegal(int side) {
        for (int i=11; i < 89; i++) {
            if ((board[i]==ADEMP) && isLegal(side, i)) {
                return true;
            }
        }
        return false;
    }
    // 下在pos,並改變盤面結構. 若pos為0, 表示此著手為pass
    boolean addMove(int pos) {

        int opp = turn^STONE;
        if (pos != 0) { // 0 表示pass
            int legal = diskdiff;
            for (int i = 0, scan; i < 8; i++) {
                scan = pos+directions[i];
                if (board[scan] == opp) { // 此方向緊鄰著敵方的子
                    // 跳過連續的敵方子
                    for (scan += directions[i]; board[scan] == opp; scan+=directions[i]);
                    if (board[scan] == turn) { // 可夾住對方
                        // 將所有敵方子變成我方子
                        for (int c = pos+directions[i]; c!=scan ;board[c]=turn, c+=directions[i], diskdiff+=2);
                    }
                }
            }
            if (diskdiff==legal) { // 如果都沒有吃到
                return false;
            }
            diskdiff++;
            board[pos] = turn;
            for (int i = 0; i < 8; i++) { // 設定此點旁的空點為ADEMP
                if (board[pos+directions[i]] == EMPTY) {
                    board[pos+directions[i]] = ADEMP;
                }
            }
        }

        turn = opp; // 換對方下了        
        diskdiff = -diskdiff;
        hashval=(hashval*64+(pos-11))%HASHSIZE;
        return true;
    }
    // Thread的進入點
    public void run() {
        setCursor(thinkCursor);
        for (;;) { // 當敵方需pass時,我方一直下
            if (turn==BLACK && whoPlayBlack == HUMAN) { // 先檢查是否改由人下
                break;
            }
            if (turn==WHITE && whoPlayWhite == HUMAN) { // 先檢查是否改由人下
                break;
            }
            addMove(best());
            repaint(); // ask winder manager to call paint() in another thread
            if (turn==BLACK && whoPlayBlack==HUMAN && hasLegal(turn)) { // 人可以下了
                break;
            }
            if (turn==WHITE && whoPlayWhite==HUMAN && hasLegal(turn)) { // 人可以下了
                break;
            }
            if (!hasLegal(turn) && !hasLegal(turn^STONE)) { // 對手和自己都不能下了
                if (black > white){
                        JOptionPane.showMessageDialog(null, "Black Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );                    }
                else if (black == white) {
                    JOptionPane.showMessageDialog(null, "Tie!", "Tie", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    JOptionPane.showMessageDialog(null, "White Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                }
                break;
            }
            if (!hasLegal(turn)) {
                addMove(0);
            }
        }
        setCursor(normalCursor);
        thinking = null;
    }
    // The following 2 methods implement the MouseMotionListener interface
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {

        if (thinking != null) return;
        row = (e.getY()-MARGIN)/GRID_SPAN;
        col = (e.getX()-MARGIN)/GRID_SPAN;
        if (row >= 8 || col >= 8) {
            setCursor(normalCursor);
            return; // 超過邊界
        }
        int pos = row*10 + col + 11;
        if (board[pos]==ADEMP && isLegal(turn, pos)) {
            setCursor(hintCursor);
        } else {
            setCursor(normalCursor);
        }
    }
    // The following 5 methods implement the MouseListener interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {

        //改鼠標位置
        row = (e.getY()-MARGIN)/GRID_SPAN;
        col = (e.getX()-MARGIN)/GRID_SPAN;

        if (row >= 8 || col >= 8) return; // 超過邊界
        if (thinking != null) return; // 電腦思考中
        int pos = row*10+col+11;
        if(turn == BLACK){
            if (board[pos] == ADEMP && addMove(pos)) { // 此位置可以下
                
                move = true;
                if(movetimess>0){
                    movetimess--;
                }
                repaint();

                
                if (hasLegal(turn)) {
                    if ((turn==WHITE && whoPlayWhite==COMPUTER) || (turn==BLACK && whoPlayBlack==COMPUTER)) { // let computer play
                        (thinking = new Thread(this)).start();
                    }
                } else {
                    if (!hasLegal(turn^STONE)) { // 雙方都不能下
                        if (black > white){
                            JOptionPane.showMessageDialog(null, "Black Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                        }
                        else if (black == white) {
                            JOptionPane.showMessageDialog(null, "Tie!", "Tie", JOptionPane.INFORMATION_MESSAGE );
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "White Win! Congragulation!", "Winner", JOptionPane.INFORMATION_MESSAGE );
                        }
                        return;
                    }
                    addMove(0); // 對方不能下,force pass
                }
            }
        
        
        try{
        s.setvalue(row, col);
        }
        catch(Exception ee){}
        }


    }
    // 棋力強弱關鍵的求值函數
    private void eval() {
        val = diskdiff;
    }
    private void alphaBeta(int level) {
        if (legals == null) {
            findLegals();
        }
        for (int i=0; i<legals.length; i++) {
            ChessBoardS tmp = new ChessBoardS(this);
            tmp.addMove(legals[i]);
            if (level<1) {
                tmp.eval();
            } else {
                tmp.alphaBeta(level-1);
            }
            // alphaBeta cut
            if (val < -tmp.val) {
                val = -tmp.val;
                for (ChessBoardS p = parent; p != null;) {
                    if (val >= -p.val) { // 對手不會選擇這條路的
                        return;
                    }
                    // 往上跳兩層
                    p = p.parent;
                    if (p != null) p = p.parent;
                }
            }
        }
    }
    private void findLegals() {
        int count = 0;
        int[] tmp = new int[60];
        for (int i=11; i<89; i++) {
            if (board[i]==ADEMP && isLegal(turn, i)) {
                tmp[count++] = i;
            }
        }
        legals = new int[count];
        System.arraycopy(tmp, 0, legals, 0, count);
    }
    private int best() {
        int bestMove = 0;
        findLegals();
        val = -100000000;
        for (int i=0; i<legals.length; i++) {
            ChessBoardS tmp = new ChessBoardS(this);
            tmp.addMove(legals[i]);
            tmp.alphaBeta(3);
            if (-tmp.val > val) {
                bestMove = legals[i];
                val = -tmp.val;
            }
        }
        return bestMove;
    }

    // override paint() defined in Component
    public void paint(Graphics g) {

        //int black, white;
        black = white = 0;
        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++){
            int mul = 35+(i+1)*GRID_SPAN;
            g.drawString(xindex[i], mul, 55);
        }
        for (int i = 0; i < 8; i++){
            int mul = 43+(i+1)*GRID_SPAN;
            g.drawString(yindex[i], 45, mul);
        }
        for (int i = 0; i <= 8; i++) {  // draw grids
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+320, MARGIN+i*GRID_SPAN);
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+320);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int pos = i*10 + j + 11;
                if ((board[pos] & BLACK) != 0) {
                    g.setColor(Color.BLACK);
                    g.fillOval(MARGIN+j*GRID_SPAN, MARGIN+i*GRID_SPAN, GRID_SPAN, GRID_SPAN);
                    black++;
                } else if ((board[pos] & WHITE) != 0) {
                    g.setColor(Color.WHITE);
                    g.fillOval(MARGIN+j*GRID_SPAN, MARGIN+i*GRID_SPAN, GRID_SPAN, GRID_SPAN);
                    white++;
                }
                if (board[pos]==ADEMP && isLegal(turn, pos)) { //顯示當且將要落子方可以落子的位置
                    g.setColor(Color.RED);
                    g.fillOval(MARGIN-5+GRID_SPAN/2+j*GRID_SPAN, MARGIN-5+GRID_SPAN/2+i*GRID_SPAN, 10, 10);
                } 
            }
        }
        //顯示數量
        g.setColor(new Color(240,240,240));
        g.fillRect(440, 0, 159, 500);
        g.setColor(Color.BLACK);
        g.fillOval(450, 20, GRID_SPAN-2, GRID_SPAN-2);
        g.setColor(Color.WHITE);
        g.fillOval(450, 65, GRID_SPAN-2, GRID_SPAN-2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30)); 
        g.drawString(""+black, 520, 47);
        g.drawString(""+white, 520, 94);
        //g.setColor(Color.WHITE);
        //g.fillRect(450, 120, 139, 250);
        //g.setColor(Color.BLACK);
        //g.setFont(new Font("Arial", Font.BOLD, 14)); 
        //g.drawString("Last Four Steps:", 452, 135);

        
        if((step != 0) && (!readfile)){
            if(turn == 1)
                text[text.length-1] = "White: " + xindex[col]+yindex[row];
            else
                text[text.length-1] = "Black: " + xindex[col]+yindex[row];
        }
        readfile = false;
        /*
        for (int i = 0;i<text.length;i++){
            if(text[i] != null){
                if (i == 4)
                    g.drawString(text[i]+" (NOW)", 452, 160+i*20);
                if (i == 3)
                    g.drawString(text[i]+" (Last 1st)", 452, 160+i*20);
                if (i == 2)
                    g.drawString(text[i]+" (Last 2nd)", 452, 160+i*20);
                if (i == 1)
                    g.drawString(text[i]+" (Last 3rd)", 452, 160+i*20);
                if (i == 0)
                    g.drawString(text[i]+" (Last 4th)", 452, 160+i*20);
            }
        }*/

        for (int i = 0;i<text.length;i++){
            record_text[step][i] = text[i];
        }

        for (int i = 0;i<board.length;i++){
            record[step][i] = board[i];
        }

        String tmp = text[3];
        text[3] = text[4];
        String tmp1 = text[2];
        text[2] = tmp;
        String tmp2 = text[1];
        text[1] = tmp1;
        text[0] = tmp2;

        System.out.println(movetimess);
        step++;

    }
    public void newGame() {
        System.arraycopy(newboard, 0, board, 0, 100);
        turn = BLACK;
        hashval = diskdiff = 0;
        text = new String[] {null, null, null, null, null};
        step = 0;
        movetimess = 0;
        currentNumber = s.gettime();

        if (thinking != null) {
            try {
                thinking.join();
            } catch(Exception epp) {}
        }
        if (whoPlayBlack == COMPUTER) {
            (thinking = new Thread(this)).start();
        }
        repaint();
    }

    public void undo() {
        int last = step - 2;
        if (last >= 0){
            int[] lastb = new int[100];
            String[] lastt = new String[5];
            for (int i = 0;i<board.length;i++){
                lastb[i] = record[last][i];
            }
            for (int i = 0;i<text.length;i++){
                lastt[i] = record_text[last][i];
            }
            System.arraycopy(lastb, 0, board, 0, 100);
            System.arraycopy(lastt, 0, text, 0, 5);
            step = last;
            readfile = true;
            int opp = turn^STONE;
            turn = opp;
            movetimess++;
            repaint();
        }
        else{
            System.out.println("no move");
        }

    }

    public void CountdownPresenterPanel(JLabel textLabel) {
        Timer timer = new Timer(1000, new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                int mytime = s.gettime();
                //
                if(s.getsyn()){
                    int pos = s.getvalue();
                    if (pos != postmp){
                        System.out.println(pos);
                        if(pos != 0){
                            if (board[pos] == ADEMP && addMove(pos)){
                                readfile = true;
                                repaint();
                                currentNumber = mytime;
                                textLabel.setText(String.valueOf(currentNumber));
                            }
                            //pos = 0;
                            postmp = pos;
                        }
                    }
                    //
                    if (currentNumber <= 0){
                        if (addMove(0)){
                            readfile = true;
                            repaint();
                        }
                        currentNumber = mytime;
                        textLabel.setText(String.valueOf(currentNumber));
                    }
                    else{
                        currentNumber--;
                        textLabel.setText(String.valueOf(currentNumber));
                    }
                    if (us.getundo()){
                        undo();
                    }
                }
                else{
                    currentNumber = s.gettime();
                    mytime = s.gettime();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    // override getPreferredSize defined in java.lang.Component,
    // so that the Component has proper size on screen
    public Dimension getPreferredSize() {
        return mySize;
    }
    // override hashCode() in java.lang.Object
    public int hashCode() {
        return hashval;
    }
    public boolean equals(Object o) {
        if (!(o instanceof ChessBoardS)) return false;
        ChessBoardS t = (ChessBoardS) o;
        for (int i=11; i<89; i++) {
            if (board[i] != t.board[i]) return false;
        }
        return true;
    }
}

class ServerO implements Runnable {
    private int srow, scol;
    private int rrow, rcol;
    private int pos = 0;
    private String strLocal, strSocket;
    private boolean syn;
    private int time;
    private String ipadd;
    private int pnn;
    private ChessBoardS cs;

    public ServerO(String ip, String pn, ChessBoardS os){
        this.srow = 100;
        this.scol = 100;
        this.ipadd = ip;
        this.pnn = Integer.parseInt(pn);
        this.cs = os;
        syn = false;
    }
    
    public void setvalue(int r, int c){
        this.srow = r;
        this.scol = c;
    }

    public void resetvalue(int r, int c){
        this.rrow = r;
        this.rcol = c;
    }

    public void run(){
        try{
            ServerSocket ssocketWelcome = new ServerSocket(pnn);

            String input = JOptionPane.showInputDialog(null, "請輸入思考時間", "設定", JOptionPane.PLAIN_MESSAGE);
            time = Integer.parseInt(input);
            //判斷輸入是否正確

            Socket socketServer = ssocketWelcome.accept();
            //System.out.println("建立連線");//
            JOptionPane.showMessageDialog(null,"Successfully Connected!", "Successful", JOptionPane.INFORMATION_MESSAGE );//
            BufferedReader brInFromClient = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            DataOutputStream dosOutToClient = new DataOutputStream(socketServer.getOutputStream());
            //Client一開始會先傳來
            strSocket = brInFromClient.readLine();    
            //通知Client時間
            //Thread.sleep(1000);
            String stime = String.valueOf(time);
            BufferedReader brInFromUser = new BufferedReader(new StringReader(stime));
            strLocal = brInFromUser.readLine();
            dosOutToClient.writeBytes(strLocal+'\n');
            //等待Client答覆後比賽開始
            strSocket = brInFromClient.readLine();
            //ok
            if (strSocket.equals("ok")){
                //回覆開始比賽
                brInFromUser = new BufferedReader(new StringReader("start"));
                strLocal = brInFromUser.readLine();
                dosOutToClient.writeBytes(strLocal+'\n');

                //Thread.sleep(1200);
                //JOptionPane.showMessageDialog(null,"Game Start!"+'\n'+"You first!", "Start", JOptionPane.INFORMATION_MESSAGE );//
                syn = true;
                System.out.println("傳輸開始");
                while(true){
                              
                    //讀data            
                    strSocket = brInFromClient.readLine();
                    //System.out.println("Client: "+strSocket);
                    String[] st = strSocket.split(" ");
                    rrow = Integer.parseInt(st[0]);
                    rcol = Integer.parseInt(st[1]);
                    pos = rrow*10 + rcol + 11;

                    //傳data
                    String s = String.valueOf(srow)+" "+String.valueOf(scol);
                    brInFromUser = new BufferedReader(new StringReader(s));
                    strLocal = brInFromUser.readLine();
                    dosOutToClient.writeBytes(strLocal+'\n');
                    //System.out.println(strLocal);
                }
            }//不ok
            else{
                socketServer.close();
            }
            
        }catch(Exception e){}
    }

    public int getvalue(){
        if (pos > 1000)
            return 0;
        else
            return pos;
    }
    public boolean getsyn(){
        return syn;
    }
    public int gettime(){
        return time;
    }
}

class UndoServerS implements Runnable{
    private String strLocal, strSocket;
    private boolean undo;
    public void run(){
        try{
            ServerSocket ssocketServer = new ServerSocket(5678);
            while(true){
                Socket undoServer = ssocketServer.accept();
                DataOutputStream toClient = new DataOutputStream(undoServer.getOutputStream());
                int opt = JOptionPane.showConfirmDialog(null,"Opponent ask for undo","Undo",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.INFORMATION_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    //sent ok
                    toClient.writeBytes("ok"+'\n');
                    undo = true;
                }
                else {
                    //sent no
                    toClient.writeBytes("no"+'\n');
                }
                //System.out.println("undo:建立連線");
                //Client先傳來
                //BufferedReader fromClient = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
                
            }
            //undoServer.close();

        }catch(Exception e){}
    }
    public boolean getundo(){
        boolean re = undo;
        undo = false;
        return re;
    }
}
