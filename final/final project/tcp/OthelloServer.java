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

public class OthelloServer extends JFrame{
    
    private OXS oxBoards;
    private MenuItem black, white;

    private OthelloServer() {

        super("OthelloServer");
        try{
        add(oxBoards = new OXS(this));}
        catch(Exception e){}
        /***********************/
        //Set Menu
        JMenuItem item1, item2;
        JMenu file = new JMenu("Game");
        item1 = new JMenuItem("Open");
        item2 = new JMenuItem("Save");
        file.add(item1);
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Todo
            }
        });
        file.add(item2);
        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        JMenu f2 = new JMenu("Help");
        JMenuBar menubar = new JMenuBar();
        menubar.add(file);
        menubar.add(f2);
        this.setJMenuBar(menubar);
        //end 

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
                oxBoards.newGame();
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
                //JFrame.setDefaultLookAndFeelDecorated(true);
                //String input = JOptionPane.showInputDialog(null, "請輸入檔名", "開啟舊檔", JOptionPane.PLAIN_MESSAGE);
                FileChooser file = new FileChooser();
                String input = file.getFileName();
                if (!input.equals(null))
                    oxBoards.readTxt(input);
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
                String input = JOptionPane.showInputDialog(null, "請輸入檔名", "另存新檔", JOptionPane.PLAIN_MESSAGE);
                WriteFile w = new WriteFile(input, oxBoards.board, oxBoards.text, oxBoards.black, oxBoards.white, oxBoards.turn);
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
               try{
                    if(oxBoards.movetimess < 1){
                        Socket socketClient = new Socket("localhost", 9012);
                        BufferedReader brInFromServer = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                        DataOutputStream dosOutToServer = new DataOutputStream(socketClient.getOutputStream());
                        String strSocket = brInFromServer.readLine();
                        if (strSocket.equals("ok")){
                                oxBoards.undo();
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
        button_set.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame.setDefaultLookAndFeelDecorated(true);
                String input = JOptionPane.showInputDialog(null, "輸入倒數時間", "設定", JOptionPane.PLAIN_MESSAGE);
                label.setText(input);
                //label.setText(input);
                oxBoards.currentNumber = Integer.parseInt(input);
                //oxBoard.countdown();
            }
        });
        panelTop.add(button_set);

        //new CountdownPresenterPanel(label, oxBoard);
        
        JLabel text = new JLabel(" Remaining Time:");
        text.setFont(new Font("Arial", Font.PLAIN, 15));
        oxBoards.CountdownPresenterPanel(label);
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

    public static void main(String argv[]) {
        new OthelloServer();
    }
    // implements the ActionListener interface
    
    //public void actionPerformed(ActionEvent e) {

        //oxBoard.newGame();
        /*
        String command = e.getActionCommand();
        if (command.equals("關於本遊戲")) {
            new ErrorDialog(this,"程式設計黑白棋(蘋果花)範例.\n作者:俞旭昇於暨南大學資管系");
        } else if (command.equals("新遊戲")) {
            oxBoard.newGame();
        } else if (command.equals("ˇ電腦下黑方")) {
            oxBoard.setBlackPlayer(0);
            black.setLabel("電腦下黑方");
        } else if (command.equals("電腦下黑方")) {
            oxBoard.setBlackPlayer(1);
            black.setLabel("ˇ電腦下黑方");
        } else if (command.equals("ˇ電腦下白方")) {
            oxBoard.setWhitePlayer(0);
            white.setLabel("電腦下白方");
        } else if (command.equals("電腦下白方")) {
            oxBoard.setWhitePlayer(1);
            white.setLabel("ˇ電腦下白方");
        }*/
    //}
}

class OXS extends Component implements MouseListener, MouseMotionListener, Runnable {
    public int[] board; // 盤面狀況,表達有邊框的10*10盤面
    public int turn, diskdiff; // 現在哪方可下, 與敵方的子數差異
    public String[] text;
    public int black, white;
    public int movetimess;
    private OXS parent; // 由哪一個盤面變化而來
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

    ServerO s = new ServerO();
    UndoServerS us = new UndoServerS();

    public OXS(JFrame p) throws Exception{

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
        new Thread(s).start();
        new Thread(us).start();
        }catch(Exception e){}

        
    }
    // 複製p的狀態
    public OXS(OXS p) {
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
    // 棋力強弱關鍵的求值函數
    private void eval() {
        val = diskdiff;
    }
    private void alphaBeta(int level) {
        if (legals == null) {
            findLegals();
        }
        for (int i=0; i<legals.length; i++) {
            OXS tmp = new OXS(this);
            tmp.addMove(legals[i]);
            if (level<1) {
                tmp.eval();
            } else {
                tmp.alphaBeta(level-1);
            }
            // alphaBeta cut
            if (val < -tmp.val) {
                val = -tmp.val;
                for (OXS p = parent; p != null;) {
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
            OXS tmp = new OXS(this);
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
                    JOptionPane.showMessageDialog(null, "文件損毀。", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                s = reader.readLine();
                String[] str2 = s.split(",");
                if(str2.length == text.length){
                    for (int i = 0;i<text.length;i++){
                        text[i] = str2[i];
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "文件損毀。", "ERROR", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "非Othello檔案。", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "找不到檔案。", "ERROR", JOptionPane.ERROR_MESSAGE);
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
        if (!(o instanceof OXS)) return false;
        OXS t = (OXS) o;
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
class ErrorDialog extends JDialog {
    public ErrorDialog(JFrame parent, String all[]) {
        this(parent, all, null);
    }
    public ErrorDialog(JFrame parent, String all[], String msg) {
        super(parent,"",true);
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<all.length; i++) {
            sb.append(all[i]);
            sb.append('\n');
        }
        if (msg!=null) {
            sb.append(msg);
        }
        setup(parent, sb.toString());
    }
    public ErrorDialog(JFrame parent, String message) {
        super(parent,"",true);
        setup(parent, message);
    }
    private void setup(JFrame parent, String message) {
        this.getContentPane().setLayout(new GridBagLayout());
        int row=0, col=0, i, width=0;
        Font font = new Font("Serif", Font.PLAIN, 16);
        char c=' ';
        for (i=0; i<message.length(); i++) {
            c = message.charAt(i);
            if (c=='\n') {
               row++;
               if (width>col) {
                   col = width;
               }
               width=0;
            } else if (c=='\t') {
                width += 7-width%7;
            } else {
                if (c>0x00FF) {
                    width+=2;
                } else {
                    width++;
                }
            }
        }
        if (c!='\n') {
           row++;
           if (width>col) {
               col = width;
           }
        }
        col++;
        // 希望視窗出來不要太大或太小
        row = (row>24) ? 24 : row;
        if (row<5) {
            row=5;
        }
        if (col<20) {
            col = 20;
        }
        TextArea tx = new TextArea(message,row,col);
        tx.setEditable(false);
        tx.setFont(font);
        AddConstraint.addConstraint(this.getContentPane(), tx, 0, 0, 1, 1,
            GridBagConstraints.BOTH,
            GridBagConstraints.NORTHWEST,
            1,1,0,0,0,0);
        Button b = new Button("確定");
        b.setFont(font);
        AddConstraint.addConstraint(this.getContentPane(), b, 0, 1, 1, 1,
            GridBagConstraints.HORIZONTAL,
            GridBagConstraints.CENTER,
            1,0,0,0,0,0);
        CloseWindow cw = new CloseWindow(this);
        this.addWindowListener(cw);
        b.addActionListener(cw);
        pack();
        setVisible(true);
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
            JOptionPane.showMessageDialog(null, "存檔成功!", "存檔成功", JOptionPane.INFORMATION_MESSAGE );
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

class ServerO implements Runnable {
    private int srow, scol;
    private int rrow, rcol;
    private int pos = 0;
    private String strLocal, strSocket;
    private boolean syn;
    private int time;

    public ServerO(){
        this.srow = 100;
        this.scol = 100;
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
            ServerSocket ssocketWelcome = new ServerSocket(1234);

            String input = JOptionPane.showInputDialog(null, "請輸入思考時間", "設定", JOptionPane.PLAIN_MESSAGE);
            time = Integer.parseInt(input);
            //判斷輸入是否正確

            Socket socketServer = ssocketWelcome.accept();
            System.out.println("建立連線");//
            BufferedReader brInFromClient = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            DataOutputStream dosOutToClient = new DataOutputStream(socketServer.getOutputStream());
            //Client一開始會先傳來
            strSocket = brInFromClient.readLine();    
            //通知Client時間
            Thread.sleep(1000);
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

                Thread.sleep(1200);
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