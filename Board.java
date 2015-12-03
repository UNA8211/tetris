
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Queue;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author UNA
 */
public class Board extends JPanel implements ActionListener {

    private int rows = 22;
    private int cols = 10;

    private int score = 0;

    private int blockSize = 45;
    private int extraSpace = blockSize;
    private int currX = 225;
    private int currY = 0;
    private Timer timer;

    ArrayList<Piece> next = new ArrayList<>();

    private Piece[][] board = new Piece[cols][rows];

    Piece piece = new Piece();
    int[][] pieceCoords = new int[4][2];

    public Board() {
        setFocusable(true);
        setPreferredSize(new Dimension((cols * blockSize) + 350, (rows * blockSize)));
        piece.newPiece();
        pieceCoords = piece.getPieceCoords();
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                board[i][j] = new Piece();
            }
        }
        for (int i = 0; i < 4; i++) {
            next.add(new Piece());
            next.get(i).newPiece();
        }
        //music();
        timer = new Timer(600, this);
        timer.start();

        addKeyListener(new KeyPress());
    }

    public void music() {
        try {
            String music = "C:\\Users\\thema_000\\Documents\\NetBeansProjects\\TetrisJava\\src\\resources\\Tetris.wav";
            AudioInputStream in = AudioSystem.getAudioInputStream(new File(music));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void start() {
        timer.start();
    }

    public void place(int currX, int nextY) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                board[(currX / blockSize) + pieceCoords[i][0]][((nextY - blockSize) / blockSize) + pieceCoords[i][1]].setIsFilled(true);
                board[(currX / blockSize) + pieceCoords[i][0]][((nextY - blockSize) / blockSize) + pieceCoords[i][1]].setTypePiece(piece.getTypePiece());
            }
        }
        checkRowFilled();
        this.currX = 225;
        piece.newPiece();
        pieceCoords = piece.getPieceCoords();
    }

    public boolean moveRight(int nextX) {
        for (int i = 0; i < 4; i++) {
            if (nextX + (pieceCoords[i][0] * blockSize) >= cols * blockSize || board[(nextX / blockSize) + (pieceCoords[i][0])][(currY / blockSize) + pieceCoords[i][1]].isIsFilled() == true) {
                return false;
            }
        }
        repaint();
        currX = nextX;
        return true;
    }

    public boolean moveLeft(int nextX) {
        for (int i = 0; i < 4; i++) {
            if (nextX + (pieceCoords[i][0] * blockSize) < 0 || board[(nextX / blockSize) + (pieceCoords[i][0])][(currY / blockSize) + pieceCoords[i][1]].isIsFilled() == true) {
                return false;
            }
        }
        repaint();
        currX = nextX;
        return true;
    }

    public boolean moveDown(int nextY) {
        for (int i = 0; i < 4; i++) {
            if (nextY >= (rows * blockSize) - (pieceCoords[i][1] * blockSize) || board[(currX / blockSize) + pieceCoords[i][0]][nextY / blockSize + (pieceCoords[i][1])].isIsFilled() == true) {
                currY = 0;
                piecePlaced();
                place(currX, nextY);
                for (int j = 0; j < 4; j++) {
                    if (board[(currX / blockSize) + pieceCoords[i][0]][(currY / blockSize) + pieceCoords[i][1]].isIsFilled() == true) {
                        JOptionPane.showMessageDialog(this, "Game over!\n Your score was: " + score);
                        System.exit(0);
                    }
                }
                repaint();
                return false;
            }
        }

        currY = nextY;
        repaint();
        return true;
    }

    public boolean rotate(Piece rotate) {
        for (int i = 0; i < 4; i++) {
            if ((currX / blockSize) - rotate.getPieceCoords()[i][0] <= 0 || (currX / blockSize) + rotate.getPieceCoords()[i][0] >= cols) {
                return false;
            } else if ((currY / blockSize) + rotate.getPieceCoords()[i][1] < 0 || (currY / blockSize) + rotate.getPieceCoords()[i][1] >= rows) {
                return false;
            } else if (board[(currX / blockSize) + rotate.getPieceCoords()[i][0]][(currY / blockSize) + rotate.getPieceCoords()[i][1]].isIsFilled() == true) {
                return false;
            }

        }
        piece = rotate;
        pieceCoords = piece.getPieceCoords();
        repaint();
        return true;
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, (cols * blockSize) + 350, (rows * blockSize));
        g.setColor(new Color(226, 226, 226));
        g.fillRect(0, 0, (cols * blockSize), rows * blockSize);
        g.fillRect((cols * blockSize) + extraSpace + 50, 0, 200, 50);
        g.fillRect((cols * blockSize) + extraSpace + 50, 75, 200, 800);
        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.drawString("SCORE:", (cols * blockSize) + extraSpace + 115, 20);
        g.drawString(Integer.toString(score), (cols * blockSize) + extraSpace + 115, 40);
        g.drawString("NEXT", (cols * blockSize) + extraSpace + 115, 110);
        for (int i = 0; i < next.size(); i++) {
            for (int j = 0; j < 4; j++) {
                g.fillRect((cols * blockSize) + (next.get(i).getPieceCoords()[i][0] * blockSize) + 145, 150 + (next.get(i).getPieceCoords()[i][1] * blockSize), blockSize, blockSize);
            }
        }
        int curr = 0;
//        for (int i = 0; i < rows + 1; i++) {
//            g.drawLine(0, curr, (cols * blockSize), curr);
//            curr += extraSpace;
//        }
//        curr = extraSpace;
//        for (int i = 0; i < cols; i++) {
//            g.drawLine(curr, 0, curr, (rows * blockSize));
//            curr += extraSpace;
//        }

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j].isIsFilled() == true) {
                    g.setColor(board[i][j].getColors()[board[i][j].getTypePiece()]);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                }
            }
        }
        g.setColor(piece.getColors()[piece.getTypePiece()]);
        for (int i = 0; i < 4; i++) {
            g.fillRect(currX + (pieceCoords[i][0] * blockSize), currY + (pieceCoords[i][1] * blockSize), blockSize, blockSize);
        }
    }

    public void piecePlaced() {
        score += 50;
    }

    public void checkRowFilled() {
        int rowsFilled = 0;
        int highestRow = 0;
        for (int i = rows - 1; i >= 0; i--) {
            int counter = 0;
            for (int j = cols - 1; j >= 0; j--) {
                if (board[j][i].isIsFilled() == false) {
                    break;
                } else {
                    counter++;
                }
            }
            if (counter == cols) {
                highestRow = i;
                for (int j = 0; j < cols; j++) {
                    board[j][i].setIsFilled(false);
                }
                rowsFilled++;
            }
        }
        if (rowsFilled != 0) {
            for (int i = cols - 1; i >= 0; i--) {
                for (int j = highestRow; j >= 0; j--) {
                    if (board[i][j].isIsFilled() == true) {
                        board[i][j + rowsFilled] = board[i][j];
                        board[i][j] = new Piece();
                    }
                }
            }
        }
        rowFilled(rowsFilled);
    }

    public void rowFilled(int numRows) {
        score += (1000 * (numRows == 4 ? 5 : numRows));
    }

    public void actionPerformed(ActionEvent e) {
        moveDown(currY + blockSize);
    }

    class KeyPress extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {

                case KeyEvent.VK_RIGHT:
                    moveRight(currX + blockSize);
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft(currX - blockSize);
                    break;
                case KeyEvent.VK_DOWN:
                    moveDown(currY + blockSize);
                    break;
                case KeyEvent.VK_UP:
                    rotate(piece.rotate());
            }
        }
    }
}
