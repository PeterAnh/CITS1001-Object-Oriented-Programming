    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Term represents terms in a chemical formula 
 * Only elements denoted by a single letter are supported 
 * 
 * Examples: N, H2, C20, H147 
 * 
 * @author Lyndon While
 * @version 31/3/16
 */
public class Term
{
    private char element;
    private int  atoms;

    // creates a Term with the provided values
    public Term(char element, int atoms)
    {
        this.element = element;
        this.atoms = atoms;
    }

    // creates a Term by parsing s 
    // e.g. "H20" would give element = 'H', atoms = 20 
    public Term(String s)
    {
        this.element = s.charAt(0);
        if(s.length() == 1){
            this.atoms=1;
        }
        else
            this.atoms=Integer.parseInt(s.substring(1));
    }
    // override the equals() method
    public boolean equals(Object o) {
        if (!(o instanceof Term)) {
        return false;
    }
        Term other = (Term) o;
        return this.getElement()== other.getElement() && this.getAtoms()==other.getAtoms();
    }
    
    // turns the Term into a String 
    // e.g. element = 'C', atoms = 4 would give "C4"
    public String display()
    {
        if(this.atoms==1)
            return "" + this.element;
        else 
            return "" + this.element + this.atoms;
    }
    
    // returns the current value of element 
    public char getElement()
    {
        return element;
    }

    // returns the current value of atoms 
    public int getAtoms()
    {
        return atoms;
    }
}
