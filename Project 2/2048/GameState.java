/**
 * This class models the 2048 game board.
 * 
 * @author Lyndon While
 * @version 1.0
 * Student ID: 21749914 and 21749683
 */

public class GameState
{
    public static final int noOfSquares = 4; // the extent of the board in both directions
    
    private Square[][] board; // the current state of the board 
    private int score;        // the current score
    
    // initialise the board and the score directly from the arguments 
    // intended principally for testing 
    public GameState(Square[][] board, int score) 
    {
        this.score = score;
        this.board = board;
    }

    // initialise the board from the file file 
    // you may assume that the file has four lines, each with four integers separated by a space 
    // throw an exception if an empty board is generated 
    public GameState(String file) throws Exception 
    {
        //TODO
        int count = 0;
        board = new Square[noOfSquares][noOfSquares];
        for (int i = 0; i < noOfSquares; i++){
            for(int j = 0; j < noOfSquares; j++){
                board[i][j] = new Square(0);
            }
        }
        FileIO fio = new FileIO(file);
        
        for(int i = 0; i < noOfSquares; i++){
            String line = fio.lines.get(i);
            String[] numbers = line.split(" ");
        
          for(int j = 0; j < noOfSquares; j++){
                board[i][j].setSquare(Integer.parseInt(numbers[j]));
          }
        }
          for(int i = 0; i < noOfSquares; i++) {
            for(int j = 0; j < noOfSquares; j++){
                if(board[i][j].isEmpty() == true) count++;
            }
          }
        
        if(count == noOfSquares*noOfSquares) throw new IllegalArgumentException("Board is empty");
       
    }
    
    // initialise the board randomly; each square should be set to 2 with probability p, and to empty otherwise
    // throw an exception if an empty board is generated 
    public GameState(double p) throws Exception 
    {
        // TODO
        double a = 0.0;
        int count = 0;
        board = new Square[noOfSquares][noOfSquares];
        for(int i = 0; i < noOfSquares; i++){
            for(int j = 0; j < noOfSquares; j++){
                board[i][j] = new Square (0);
            }
        }
        
        if(p == 0) throw new IllegalArgumentException ("0 probability, cannot produce board");
      
        for (int i = 0; i < noOfSquares; i++){
            for(int j = 0; j < noOfSquares; j++){
                a = Math.random();
                if (a <= p) 
                {
                    try{
                      board[i][j].setSquare(2);
                    }catch (Exception e){}
                }
                else       
                {          
                    try{
                      board[i][j].setSquare(0);
                    }catch (Exception e){}
                }
            }
         }
         for(int i = 0; i < noOfSquares; i++){
           for(int j = 0; j < noOfSquares; j++){
               if(board[i][j].isEmpty() == true) count++;
            }
         }
        if (count == noOfSquares * noOfSquares) throw new IllegalArgumentException ("Board is empty");
    }
    
    // return the current state of the board
    public Square[][] getBoard() 
    {
        return board;
    }
    
    // return the current score
    public int getScore()
    {
        return score;
    }
    
    // make a forward move in row r; return true iff something moved
    public boolean forward(Square[] row)
    {
        boolean movement = false;
               
        for(int i = 0; i < noOfSquares; i++) {
          for (int j = 0; j < noOfSquares; j++){
              if(row[j].isEmpty()){
                  if(j < noOfSquares - 1 && !row[j+1].isEmpty()){
                      try{
                      row[j].setSquare(row[j+1].getSquare());
                      row[j+1].setSquare(0);
                    } catch (Exception e){}
                    movement = true; 
                }
              }
              
          }

        }
        
       
         for(int j = 0; j < noOfSquares; j++){
            if(j < noOfSquares - 1 && !row[j].isEmpty() && row[j].getSquare() == row [j+1].getSquare()){
                   try{
                       row[j].setSquare(row[j].getSquare() + row[j+1].getSquare());
                       row[j+1].setSquare(0);
                   }catch(Exception e){}
                   movement = true;
               }
         }
   
        
         for(int i = 0; i < noOfSquares; i++) {
          for (int j = 0; j < noOfSquares; j++){
              if(row[j].isEmpty()){
                  if(j < noOfSquares - 1 && !row[j+1].isEmpty()){
                      try{
                      row[j].setSquare(row[j+1].getSquare());
                      row[j+1].setSquare(0);
                    } catch (Exception e){}
                    movement = true; 
                }
              }
              
          }

        }
        if(row[0].getSquare() == 0) movement = false;
        return movement;
    }
        
    // make a Left move
    public void left()
    {
        // TODO
        boolean isMoved = false;
       
         for(int i = 0; i < noOfSquares; i++){
             if(forward(board[i]))isMoved = true;
         }
         
         if(isMoved)
         {
              for(int j = noOfSquares - 1; 0 <= j ; j--)
              {
                  if (board[j][noOfSquares-1].isEmpty())
                  {
                     try{ 
                      board[j][noOfSquares-1].setSquare(2);
                      break;
                     }catch(Exception e){}
                  }
              }
               score = score +1;  
         }else score = score -1;
        
    }
        
    
    
    
    // make a Right move
    public void right()
    {
        // TODO
        anticlockwise();
        anticlockwise();
        left();
        clockwise();
        clockwise();
    }
    
    // make an Up move
    public void up()
    {
        // TODO
        anticlockwise();
        left();
        clockwise();
    }
    
    // make a Down move
    public void down()
    {
        // TODO
        clockwise();
        left();
        anticlockwise();
    }
    
    // rotate the board clockwise 90 degrees
    public void clockwise()
    {
        // TODO
        Square[][] newBoard = new Square[noOfSquares][noOfSquares];
        for(int i = 0; i < noOfSquares; i++){
            for(int j = 0; j < noOfSquares; j++){
                newBoard [i][j] = board[noOfSquares-1-j][i];
            }
        }
        Square [][] swap = board;
        board = newBoard;
        newBoard = swap;
    }
    
    // rotate the board anti-clockwise 90 degrees
    public void anticlockwise()
    {
        // TODO
        
        Square[][] newBoard = new Square[noOfSquares][noOfSquares];
        for(int i = 0; i < noOfSquares; i++){
            for(int j = 0; j < noOfSquares; j++){
                newBoard [i][j] = board[j][noOfSquares-1-i];
            }
        }
        Square [][] swap = board;
        board = newBoard;
        newBoard = swap;
    }
    
    // return true iff the game is over, i.e. no legal moves are possible
    public boolean gameOver()
    {
        boolean gameIsDone = false;
        // TODO
        for(int i = 0; i < noOfSquares; i++){
            for(int j = 0; j < noOfSquares; j++){
                if(board[i][j].isEmpty()){
                    gameIsDone = true;
                    break;
                }
            }
        }
        
       for(int i = 0; i < noOfSquares; i++){
           for(int j = 0; j < noOfSquares - 1; j++){
               if(board[i][j].getSquare() == board[i][j+1].getSquare()){
                   gameIsDone = true;
                   break;
                }
            }
        }
        
       for (int j = 0; j < noOfSquares; j++){
           for(int i = 0; i < noOfSquares - 1; i++){
               if(board[i][j].getSquare() == board[i+1][j].getSquare()){
                   gameIsDone = true;
                   break;
                }
            }
       }
       
       if(gameIsDone){
           return false;
        }else {return true;}
    }
      
}
