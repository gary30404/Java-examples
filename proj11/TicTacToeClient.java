import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class TicTacToeClient {

    private JFrame frame = new JFrame("Tic Tac Toe");
    private JLabel messageLabel = new JLabel("");
    private ImageIcon icon;
    private ImageIcon opponentIcon;

    private Square[] board = new Square[9];
    private Square currentSquare;

    private static int PORT;
    private static String serverAddress;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public TicTacToeClient() throws Exception {

        // Setup networking
        /*
        JTextField ip = new JTextField();
        JTextField po = new JTextField();
        Object[] message = {
            "IP address:", ip,
            "Port:", po
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Connect to Server", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            serverAddress = ip.getText();
            PORT = Integer.valueOf(po.getText());
        } else {
            System.out.println("Connection Failed!");
            socket.close();
        }*/

        socket = new Socket("localhost", 8901);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Layout GUI
        messageLabel.setBackground(Color.lightGray);
        frame.getContentPane().add(messageLabel, "South");

        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.black);
        boardPanel.setLayout(new GridLayout(3, 3, 2, 2));
        for (int i = 0; i < board.length; i++) {
            final int j = i;
            board[i] = new Square();
            board[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    currentSquare = board[j];
                    out.println("MOVE " + j);}
            });
            boardPanel.add(board[i]);
        }
        frame.getContentPane().add(boardPanel, "Center");
    }

    public boolean play() throws Exception {
        String response;
        int res;
        try {
            response = in.readLine();
            if (response.startsWith("WELCOME")) {
                char mark = response.charAt(8);
                icon = new ImageIcon(mark == 'X' ? "x.gif" : "o.gif");
                opponentIcon  = new ImageIcon(mark == 'X' ? "o.gif" : "x.gif");
                frame.setTitle("Tic Tac Toe - Player " + mark);
            }
            while (true) {
                response = in.readLine();
                if (response.startsWith("VALID_MOVE")) {
                    messageLabel.setText("Valid move, please wait");
                    currentSquare.setIcon(icon);
                    currentSquare.repaint();
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    int loc = Integer.parseInt(response.substring(15));
                    board[loc].setIcon(opponentIcon);
                    board[loc].repaint();
                    messageLabel.setText("Opponent moved, your turn");
                } else if (response.startsWith("VICTORY")) {
                    res = JOptionPane.showConfirmDialog(frame, "You Win! Play again?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
                    frame.dispose();
                    return res == JOptionPane.YES_OPTION;
                } else if (response.startsWith("DEFEAT")) {
                    res = JOptionPane.showConfirmDialog(frame, "You Lose! Play again?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
                    frame.dispose();
                    return res == JOptionPane.YES_OPTION;
                } else if (response.startsWith("TIE")) {
                    res = JOptionPane.showConfirmDialog(frame, "Tie! Play again?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
                    frame.dispose();
                    return res == JOptionPane.YES_OPTION;
                } else if (response.startsWith("MESSAGE")) {
                    messageLabel.setText(response.substring(8));
                }
            }
        }
        finally {
            socket.close();
        }
    }

    static class Square extends JPanel {
        JLabel label = new JLabel((Icon)null);

        public Square() {
            setBackground(Color.white);
            add(label);
        }

        public void setIcon(Icon icon) {
            label.setIcon(icon);
        }
    }

    public static void main(String[] args) throws Exception {
        boolean check;
        while (true) {
            TicTacToeClient client = new TicTacToeClient();
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.setSize(240, 160);
            client.frame.setVisible(true);
            client.frame.setResizable(false);
            check = client.play();
            if (!check) {
                break;
            }
        }
    }
}