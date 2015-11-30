
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author UNA
 */
public class Board extends JPanel implements ActionListener {

    private int startX = 500;
    private int rows = 22;
    private int cols = 10;

    private int score = 0;

    private int extraSpace = 45;
    private int currX = 0;
    private int currY = 0;
    private Timer timer;

    private boolean started;
    private boolean falling;

    private boolean[][] board = new boolean[cols][rows];

    public Board() {
        setFocusable(true);
        setPreferredSize(new Dimension((cols * 45) + 350, (rows * 45)));
        timer = new Timer(600, this);
        timer.start();

        addKeyListener(new KeyPress());

    }

    public void start() {
        started = true;
        falling = true;

        timer.start();
    }

    public boolean movePiece(int nextX, int nextY) {
        if (nextX < 0 || nextX > (cols * 45)) {
            repaint();
            return false;
        } else if (nextY > (rows * 45) - extraSpace) {
            currY = 0;
            repaint();
            piecePlaced();
            return false;
        }
        currX = nextX;
        currY = nextY;
        repaint();
        return true;
    }

    public void rotate() {

    }
    
    public void gravity(){
        currY += 45;
        repaint();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, (cols * 45) + 350, (rows * 45));
        g.setColor(new Color(226, 226, 226));
        g.fillRect(0, 0, (cols * 45) + extraSpace, rows * 45);
        g.fillRect((cols * 45) + extraSpace + 50, 0, 200, 50);
        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.drawString("SCORE:", (cols * 45) + extraSpace + 115, 20);
        g.drawString(Integer.toString(score), (cols * 45) + extraSpace + 115, 40);
        int curr = 0;
        for (int i = 0; i < rows + 1; i++) {
            g.drawLine(0, curr, (cols * 45) + extraSpace, curr);
            curr += extraSpace;
        }
        curr = extraSpace;
        for (int i = 0; i < cols + 1; i++) {
            g.drawLine(curr, 0, curr, (rows * 45));
            curr += extraSpace;
        }
        g.fillRect(currX, currY, 45, 45);
    }

    public void setPiece() {

    }
    
    public void piecePlaced(){
        score += 100;
    }
    
    public void rowFilled(int numRows){
        score += (1000 * (numRows == 4 ? 5: numRows));
    }

    public void actionPerformed(ActionEvent e) {
        movePiece(currX, currY + 45);
    }

    class KeyPress extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {

                case KeyEvent.VK_RIGHT:
                    movePiece(currX + 45, currY);
                    break;
                case KeyEvent.VK_LEFT:
                    movePiece(currX - 45, currY);
                    break;
                case KeyEvent.VK_DOWN:
                    movePiece(currX, currY + 45);
                    break;
            }
        }
    }
}
