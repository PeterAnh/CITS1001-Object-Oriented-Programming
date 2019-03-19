/**
 * Equation represents chemical equations 
 * It allows only single molecules atm, i.e. "O+H2=H2O" is fine, 
 * but "O+2H=H2O" is illegal, and would have to be written "O+H+H=H20" 
 * 
 * @author Lyndon While
 * @version 31/3/16
 */
import java.util.ArrayList;

public class Equation
{
    private ArrayList<Formula> lhs, rhs;

    // creates an Equation with the provided values
    public Equation(ArrayList<Formula> lhs, ArrayList<Formula> rhs)
    {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    // creates an Equation by parsing s
    // e.g. "O+H2=H2O" would give 
    // lhs = {Formula({Term('O',1)}), Formula({Term('H',2)})},
    // rhs = {Formula({Term('H',2), Term('O',1)})} 
    public Equation(String s)
    {
        String temp= new String(s);
        temp = temp.replaceAll("(\\s+|^\\s+|\\s+$)", "");
        for(int i=0; i< temp.length(); i++)
        {
            if(temp.charAt(i)=='='){
                this.lhs=parseSide(temp.substring(0, i));
                this.rhs=parseSide(temp.substring(i));
                break;
            }
        }
    }
    
    // returns a parsed form of one side of an equation 
    // e.g. "O+H2" would return {Formula({Term('O',1)}), Formula({Term('H',2)})}
    public static ArrayList<Formula> parseSide(String s)
    {
        ArrayList<Formula> main=new ArrayList<>();
        String temp1=new String(s);
        //delete all whitespaces
        temp1 = temp1.replaceAll("(\\s+|^\\s+|\\s+$)", "");
        int flag=0, j=0;
        for(int i=0; i<temp1.length(); i++)
        {
            j++;
            if(temp1.charAt(i)=='+'){
                flag = i;
                main.add(new Formula(temp1.substring(i+1-j, i)));
                j=0;
            }
            if(i==temp1.length()-1){
                main.add(new Formula(temp1.substring(flag+1)));
            }
        }
        return main;
    }
    
    // turns the Equation into a String 
    // e.g. lhs = {Formula({Term('O',1)}), Formula({Term('H',2)})},
    // rhs = {Formula({Term('H',2), Term('O',1)})} 
    // would return "O + H2 = H2O"
    public String display()
    {
        String s= new String();
        for(int i=0; i<this.lhs.size(); i++)
        {
            if(i==this.lhs.size()-1){
                s=s+lhs.get(i).display() + " = "; 
            }
            else
                s=s+lhs.get(i).display() + " + ";
        }
        for(int i=0; i<this.rhs.size(); i++)
        {
            if(i==this.rhs.size()-1){
                s=s+rhs.get(i).display(); 
            }
            else
                s=s+rhs.get(i).display() + " + ";    
        }
        return s;
    }
    
    // returns true iff this Equation specifies the same atom-counts on each side 
    // e.g. lhs = {Formula({Term('O',1)}), Formula({Term('H',2)})}, 
    // rhs = {Formula({Term('H',2), Term('O',1)})} 
    // would return true 
    public boolean balanced()
    {
        ArrayList<Term> temp1= new ArrayList<>();
        Formula mainLhs = new Formula(temp1);
        ArrayList<Term> temp2= new ArrayList<>();
        Formula mainRhs = new Formula(temp2);
        for(int i=0; i<lhs.size(); i++)
        {
            mainLhs.getTerms().addAll(lhs.get(i).getTerms());
        }
        for(int i=0; i<rhs.size(); i++)
        {
            mainRhs.getTerms().addAll(rhs.get(i).getTerms());
        }
        mainLhs.makeMolecular();
        mainRhs.makeMolecular();
        return mainLhs.identical(mainRhs);
    }
    
    // returns the current value of lhs 
    public ArrayList<Formula> getLHS()
    {
        return lhs;
    }
    
    // returns the current value of rhs 
    public ArrayList<Formula> getRHS()
    {
        return rhs;
    }
}
    
