/**
 * This class models one square on the 2048 board.
 * A square can be empty, denoted by 0; 
 * or it can hold a tile with a number in {2,4,8,16,32,64,...}. 
 * 
 * @author Lyndon While
 * @version 1.0
 * Student ID: 21749914 and 21749683
 */

public class Square
{
    private int x; // the value of the tile sitting on the square, or 0 for empty

    // create a square with the value x, or empty if x is not legal 
    public Square(int x)
    {  
            if (x < 0)             this.x = 0;
       else if (x == 1)            this.x = 0;
       else if ((x & (x -1)) != 0) this.x = 0;
       else                        this.x = x;
    }

    // return the current value of the square 
    public int getSquare()
    {
        return x;
    }
    
    // return true iff the square is empty
    public boolean isEmpty()
    {
        return x == 0;
    }
    
    // put a tile of value x on the square, if x is legal; 
    // otherwise throw an Exception with a suitable error message 
    public void setSquare(int x) throws Exception
    {
           if (x == 1)             throw new IllegalArgumentException (x + " is not a legal value (cannot be 1)");
      else if (x < 0)              throw new IllegalArgumentException (x + " is not a legal value (negative value)");
      else if ((x & (x - 1)) != 0) throw new IllegalArgumentException (x + " is not a legal value (not a power of two)");
      else                         this.x = x;                        
    }
}