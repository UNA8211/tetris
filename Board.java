
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
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
    }
    
    class KeyPress extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    move(0,0);
            }
        }
    }
    
}
