
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author UNA
 */
public class Piece {

    /*
     Pieces in order:
     -
     O
     J
     L
     T
     Z
     S
     */
    private int[][][] pieces = {{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}},
    {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
    {{0, 0}, {1, 0}, {2, 0}, {0, 1}},
    {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
    {{0, 1}, {1, 0}, {1, 1}, {2, 1}},
    {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
    {{0, 1}, {1, 1}, {1, 0}, {2, 0}}};
    private Color[] colors = {new Color(0, 255, 255),
        new Color(255, 255, 0),
        new Color(0, 0, 255),
        new Color(255, 128, 0),
        new Color(127, 0, 255),
        new Color(255, 0, 0),
        new Color(0, 255, 0)};
    private int[][] pieceCoords = new int[4][2];
    private int typePiece;
    private boolean isFilled = false;
    private boolean isFall = false;

    public Piece() {

    }

    public void newPiece() {
        typePiece = (int) (Math.random() * 7);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                pieceCoords[i][j] = pieces[typePiece][i][j];
            }
        }
    }
    
    private void setX(int i, int val){
        pieceCoords[i][0] = val;
    }

    private int getX(int i){
        return pieceCoords[i][0];
    }
    
    private void setY(int i, int val){
        pieceCoords[i][1] = val;
    }
    
    private int getY(int i){
        return pieceCoords[i][01];
    }
    
    public Piece rotate() {
        if (typePiece == 1) {
            return this;
        }
        Piece newPiece = new Piece();
        newPiece.setTypePiece(this.getTypePiece());
        for (int i = 0; i < 4; i++) {
            newPiece.setX(i, -getY(i));
            newPiece.setY(i, getX(i));      
        }
        return newPiece;
    }

    public int[][] getPieceCoords() {
        return pieceCoords;
    }

    public boolean isIsFilled() {
        return isFilled;
    }

    public boolean isIsFall() {
        return isFall;
    }

    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public void setIsFall(boolean isFall) {
        this.isFall = isFall;
    }

    public int getTypePiece() {
        return typePiece;
    }

    public Color[] getColors() {
        return colors;
    }

    public void setTypePiece(int typePiece) {
        this.typePiece = typePiece;
    }

}
