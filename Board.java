
<<<<<<< HEAD
import java.awt.Dimension;
=======
>>>>>>> origin/master
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
<<<<<<< HEAD
import javax.swing.Timer;
=======
>>>>>>> origin/master
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UNA
 */
public class Board extends JPanel implements ActionListener{
<<<<<<< HEAD
    private int startX = 500;
    private int rows = 22;
    private int cols = 10;
    private int extraSpace = 45;
    private int currX = 0;
    private int currY = 0;
    private Timer timer;
    private boolean started;
    private boolean falling;
    private boolean[][] board = new boolean[cols][rows];
    
    public Board(){
        setFocusable(true);
        setPreferredSize(new Dimension((cols * 45), (rows * 45)));
        timer = new Timer(400, this);
        timer.start();
        
        addKeyListener(new KeyPress());
        
    }
    
    public void start(){
        started = true; 
        falling = true;
        
        timer.start();
    }

    public boolean movePiece(int nextX, int nextY){
        if(nextX < 0 || nextX > (cols * 45) - extraSpace){
            return false;
        }
        else if(nextY > (rows * 45) - extraSpace){
            return false;
        }
        currX = nextX;
        currY = nextY;
        repaint();
        return true;
    }
    public void rotate(){
        
    }
    
    public void paint(Graphics g){
        g.clearRect(0, 0, (cols * 45), (rows * 45));
        int curr = 0;
        for(int i = 0; i <= rows + 1; i++){
            g.drawLine(0, curr, (cols * 45), curr);
            curr += extraSpace;
        }
        curr = extraSpace;
        for(int i = 0; i <= cols + 1; i++){
            g.drawLine(curr, 0, curr, (rows * 45));
            curr += extraSpace;
        }
        g.fillRect(currX, currY, 45, 45);   
    }
    
    public void clear(){
        
    }
    
    public void setPiece(){
        
    }

    public void actionPerformed(ActionEvent e) {

=======
    
    private boolean[][] board = new boolean[20][10];
    Piece piece = new Piece();
    public Board(){
        draw();
    }
    
    public void move(int posX, int posY){
        
    }
    
    public void draw(){
        Graphics g = null;
        g.fillRect(40, 60, 40, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> origin/master
    }
    
    class KeyPress extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            switch(key){
<<<<<<< HEAD
                case KeyEvent.VK_RIGHT:
                    movePiece(currX + 45,currY);
                    break;
                case KeyEvent.VK_LEFT:
                    movePiece(currX - 45,currY);
                    break;
                case KeyEvent.VK_DOWN:
                    movePiece(currX,currY + 45);
                    break;
=======
                case KeyEvent.VK_LEFT:
                    move(0,0);
>>>>>>> origin/master
            }
        }
    }
    
}
