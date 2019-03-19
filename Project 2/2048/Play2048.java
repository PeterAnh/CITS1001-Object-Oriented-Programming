/**
 * This class models the game 2048.
 * 
 * @author Lyndon While
 * @version 1.0
 * Student ID: 21749914 and 21749683
 */

import java.awt.*;
import java.awt.event.*;

public class Play2048 
{
    private static final int WINDOW_SIZE  = 500;
    private static final int SQUARE_SIZE  = 100;
    private static final int BORDER_WIDTH = (WINDOW_SIZE - GameState.noOfSquares * SQUARE_SIZE) / 2;

    private Color   GRID_COLOR  = Color.BLACK;
    private Color[] TEXT_COLORS = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW};
    private Color   BACK_COLOR  = Color.WHITE;

    private GameState gameState;
    private SimpleCanvas sc;

    // set up a game from the file file 
    public Play2048(String file) 
    {
        // TODO
        sc = new SimpleCanvas ("2048", WINDOW_SIZE, WINDOW_SIZE, GRID_COLOR);
        try {
            gameState = new GameState(file);
        }catch(Exception e){}
        String s = "";
        
        Square[][] firstBoard = gameState.getBoard();
        for(int i = 0; i < gameState.noOfSquares; i++){
            for(int j = 0; j < gameState.noOfSquares; j++){
              sc.drawRectangle(i*SQUARE_SIZE+10, j*SQUARE_SIZE+10, (i+1)*SQUARE_SIZE, (j+1)*SQUARE_SIZE, Color.GREEN);  
            }
         }
         
        for(int i = 0; i < gameState.noOfSquares; i++){
            for(int j = 0; j < gameState.noOfSquares; j++){
                s = "" + firstBoard[i][j].getSquare();
                sc.drawString(s, i*SQUARE_SIZE + SQUARE_SIZE/2, j*SQUARE_SIZE + SQUARE_SIZE/2, Color.BLACK);
            }
        }
   }

    // set up a game using the probability p 
    public Play2048(double p) 
    {
        // TODO
        sc = new SimpleCanvas ("2048", WINDOW_SIZE, WINDOW_SIZE, GRID_COLOR);
        try {
            gameState = new GameState(p);
        }catch(Exception e){}
        String s = "";
        
        Square[][] firstBoard = gameState.getBoard();
        for(int i = 0; i < gameState.noOfSquares; i++){
            for(int j = 0; j < gameState.noOfSquares; j++){
              sc.drawRectangle(i*SQUARE_SIZE+10, j*SQUARE_SIZE+10, (i+1)*SQUARE_SIZE, (j+1)*SQUARE_SIZE, Color.GREEN);  
            }
         }
         
        for(int i = 0; i < gameState.noOfSquares; i++){
            for(int j = 0; j < gameState.noOfSquares; j++){
                s = "" + firstBoard[i][j].getSquare();
                sc.drawString(s, i*SQUARE_SIZE + SQUARE_SIZE/2, j*SQUARE_SIZE + SQUARE_SIZE/2, Color.BLACK);
            }
        }
    }

    // set up a game using the probability 0.3 
    public Play2048() 
    {
        // TODO
         sc = new SimpleCanvas ("2048", WINDOW_SIZE, WINDOW_SIZE, GRID_COLOR);
         double p = 0.3;
        try {
            gameState = new GameState(p);
        }catch(Exception e){}
        String s = "";
        
        Square[][] firstBoard = gameState.getBoard();
        for(int i = 0; i < gameState.noOfSquares; i++){
            for(int j = 0; j < gameState.noOfSquares; j++){
              sc.drawRectangle(i*SQUARE_SIZE+10, j*SQUARE_SIZE+10, (i+1)*SQUARE_SIZE, (j+1)*SQUARE_SIZE, Color.GREEN);  
            }
         }
         
        for(int i = 0; i < gameState.noOfSquares; i++){
            for(int j = 0; j < gameState.noOfSquares; j++){
                s = s + firstBoard[i][j].getSquare();
                sc.drawString(s, i*SQUARE_SIZE + SQUARE_SIZE/2, j*SQUARE_SIZE + SQUARE_SIZE/2, Color.YELLOW);
            }
        }
    }
    
    // get the computer to play a game of 2048 
    public void play2048()
    {
        // TODO
       while(!gameState.gameOver()){
         double a = Math.random();
         if(a <= 0.5) {
             gameState.left();
             sc.wait(200);
         }
         if(a <= 0.5) {
             gameState.up();
             sc.wait(200);
         }
         if(a <= 0.5) {
             gameState.down();
             sc.wait(200);
         }
         if(a <= 0.5) {
             gameState.right();
             sc.wait(200);
         }
       
    }
   
  }
}
