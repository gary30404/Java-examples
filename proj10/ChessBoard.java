import java.awt.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.*;

public class ChessBoard extends JFrame {
  
    private Board cBoard;

    public ChessBoard(){
        setTitle("ChessBoard");
        cBoard = new Board();
      
        Container contentPane=getContentPane();
        contentPane.add(cBoard);
        cBoard.setOpaque(true);
    
        add(cBoard);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,1200);
        pack();
      
    }
  
    public static void main(String[] args){
        ChessBoard f = new ChessBoard();//主框架
        f.setVisible(true);//顯示主框架
      
    }
}

class Board extends JPanel implements MouseListener {
    public static final int MARGIN = 0;//边距
    public static final int GRID_SPAN = 40;//网格间距
    public static final int ROWS = 8;//棋盘行数
    public static final int COLS = 8;//棋盘列数
   
    Point[] chessList=new Point[(ROWS+1)*(COLS+1)];//初始每个数组元素为null
    boolean isBlack=true;//黑棋先
    boolean gameOver=false;//游戏是否结束
    int chessCount;//當前棋盘棋子的个数
    int xIndex,yIndex;//當前刚下棋子的索引
   
    Image img;
    Image shadows;
    Color colortemp;
    public Board(){
        Color c = new Color(139, 69,19);
        setBackground(c);//设置背景色为橘黄色
        img=Toolkit.getDefaultToolkit().getImage("");
        shadows=Toolkit.getDefaultToolkit().getImage("");
        addMouseListener(this);
        addMouseMotionListener(new MouseMotionListener(){
            
            public void mouseDragged(MouseEvent e){   
            }
            public void mouseMoved(MouseEvent e){
                int x1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
                //将鼠标点击的坐标位置转成网格索引
                int y1=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
                //游戏已经结束不能下；落在棋盘外不能下；x，y位置已经有棋子存在，不能下
                if(x1<0||x1>ROWS||y1<0||y1>COLS||gameOver||findChess(x1,y1))
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                else 
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    } 
   

//绘制
    public void paintComponent(Graphics g){
     
        super.paintComponent(g);//畫棋盘
     
        int imgWidth= img.getWidth(this);
        int imgHeight=img.getHeight(this);
        int FWidth=getWidth();
        int FHeight=getHeight();
        int x=(FWidth-imgWidth)/2;
        int y=(FHeight-imgHeight)/2;
        g.drawImage(img, x, y, null);
    
       
        for(int i=0;i<=ROWS;i++){//画横线
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);
        }
        for(int i=0;i<=COLS;i++){//画竖线
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);
           
        }
       
        //畫棋子
        for(int i=0;i<chessCount;i++){
            //网格交叉点x，y坐标
            int xPos=chessList[i].getX()*GRID_SPAN+MARGIN+MARGIN/2-GRID_SPAN/2;
            int yPos=chessList[i].getY()*GRID_SPAN+MARGIN+MARGIN/2-GRID_SPAN/2;
            g.setColor(chessList[i].getColor());//设置颜色

            colortemp=chessList[i].getColor();
            if(colortemp==Color.black){
                RadialGradientPaint paint = new RadialGradientPaint(xPos, yPos, 20, new float[]{0f, 1f}, new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
            }
            else if(colortemp==Color.white){
                RadialGradientPaint paint = new RadialGradientPaint(xPos, yPos, 70, new float[]{0f, 1f}, new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
            }
         
            Ellipse2D e = new Ellipse2D.Float(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, 34, 35);
            ((Graphics2D) g).fill(e);
        }
    }
   
    public void mousePressed(MouseEvent e){//鼠标在组件上按下时调用

        if(gameOver) return;
       
        String colorName=isBlack?"黑棋":"白棋";
       
        //滑鼠座標轉成網格
        xIndex=(e.getX()/GRID_SPAN)+1;
        yIndex=(e.getY()/GRID_SPAN)+1;
        
        if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS)
            return;
       
        //如果x，y位置已經有棋子存在，不能下
        if(findChess(xIndex,yIndex))return;
       
        Point ch=new Point(xIndex,yIndex,isBlack?Color.black:Color.white);
        
        chessList[chessCount++]=ch;
        System.out.println("chessCount:"+chessCount);
        if(chessCount == 64){
            repaint();
            gameOver = true;
            JOptionPane.showMessageDialog(null,"遊戲結束！");
        }
        else{
            repaint();//通知系统重新绘制
            isBlack=!isBlack;
        }   
    }
    //覆盖mouseListener的方法
    public void mouseClicked(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }    
    public void mouseReleased(MouseEvent e){
    }
    //在棋子数组中查找是否有索引为x，y的棋子存在
    private boolean findChess(int x,int y){
        for(Point c:chessList){
            if(c!=null&&c.getX()==x&&c.getY()==y)
                return true;
        }
        return false;
    }
   
    private Point getChess(int xIndex,int yIndex,Color color){
        for(Point p:chessList){
            if(p!=null&&p.getX()==xIndex&&p.getY()==yIndex&&p.getColor()==color)
                return p;
        }
        return null;
    }
   
    //矩形Dimension
    public Dimension getPreferredSize(){
        return new Dimension(MARGIN*2+GRID_SPAN*COLS,MARGIN*2+GRID_SPAN*ROWS);
    }         
}

class Point {
    private int x;//棋盘中的x索引
    private int y;//棋盘中的y索引
    private Color color;
    public static final int DIAMETER=35;
  
    public Point(int x,int y,Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    } 
  
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Color getColor(){
        return color;
    }
}