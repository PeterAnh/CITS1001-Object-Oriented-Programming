/**
 * Formula represents chemical formulae 
 * e.g. Hexane: 
        Structural formula CH3CH2CH2CH2CH2CH3 
        Molecular  formula C6H14 
 * 
 * In a molecular formula, elements are listed only once, and they are listed alphabetically 
 * 
 * @author Lyndon While
 * @version 31/3/16
 */
import java.util.ArrayList;
import java.util.Collections;

public class Formula
{
    private ArrayList<Term> terms;

    // creates a Formula with the provided value
    public Formula(ArrayList<Term> terms)   
    {
        this.terms = terms;
    }

    // creates a Formula by parsing s 
    // e.g. "CH2O" would give terms = {Term('C',1),Term('H',2),Term('O',1)}
    public Formula(String s)
    {
        this.terms=new ArrayList<>();
        String temp1=new String(s);
        String temp2=new String(s);
            do{
                temp1=temp2.substring(lastElement(temp2));
                this.terms.add(new Term(temp1));
                temp2=temp2.substring(0, lastElement(temp2));
                
            }while(temp2.length()!=0);   
        //Arrange the ArrayList
        for(int i=0; i<(this.terms.size()/2); i++)
        {
            Collections.swap(terms, i, this.terms.size()-i-1);
        }
    }
    
    // returns the index of the rightmost upper-case letter in s, or -1 if none found 
    // e.g. "COHOHH2" would give 5 
    public static int lastElement(String s)
    {
        for(int i = s.length()-1; i>=0; i--)
        {
            if(Character.isUpperCase(s.charAt(i)))
                return i;
        }
        return -1;
    }
    
    // turns the Formula into a String
    // e.g. terms = {Term('C', 1),Term('O',2)} would give "CO2"
    public String display()
    {
        String[] s1 = new String[terms.size()];
        String s2= new String();
        for(int i=0; i<terms.size(); i++){
            s1[i]=terms.get(i).display();
            s2+=s1[i];
        }
        
        return s2;
    }
    
    // returns the current value of terms 
    public ArrayList<Term> getTerms()
    {
        return terms;
    }
    
    // changes terms into molecular form 
    // e.g. terms = {Term('H',3),Term('C',1),Term('H',3),Term('C',1)} would become {Term('C',2),Term('H',6)}
    public void makeMolecular()
    {
        ArrayList<Term> temp = new ArrayList<>();
        int size= this.terms.size();
        for(int i=0; i<size; i++)
        {
            temp.add(i, this.nextElement());
            terms.remove(this.nextElement());
        }
        int atoms_=0, i=0;
        for(i=0; i<size; i++){
            atoms_+=temp.get(i).getAtoms();
            if(i+1==size){
                this.terms.add(new Term(temp.get(i).getElement(), atoms_));
                break;
            }
            if(temp.get(i).getElement()!= temp.get(i+1).getElement()){
                this.terms.add(new Term(temp.get(i).getElement(), atoms_));
                atoms_=0;
            }
        } 
    }
    
    // returns the next element in terms, alphabetically 
    // e.g. terms = {Term('H',4),Term('C',2),Term('H',4),Term('C',1)} would return Term('C',2)
    public Term nextElement()
    {
        Term min= new Term(this.terms.get(0).getElement(), this.terms.get(0).getAtoms());
        for(int i=1; i<this.terms.size(); i++)
        {
            if(this.terms.get(i).getElement()<min.getElement())
                min=this.terms.get(i);
        }
        return min;
    }
    
    // returns true iff f is identical to this Formula 
    // e.g. terms = {Term('C',2),Term('H',6)} and f = {Term('C',2),Term('H',6)} would return true 
    // but  terms = {Term('C',2),Term('H',6)} and f = {Term('H',6),Term('C',2)} would return false
    public boolean identical(Formula f)
    {
        int i = this.terms.size();
        if(f.getTerms().size() != i)
            return false;
        for(i=0; i<this.terms.size(); i++){
            if(!terms.get(i).display().equals(f.getTerms().get(i).display()))
                return false;
        }
        return true;
    }
    
    // returns true iff f is an isomer of this Formula 
    // e.g. terms = {Term('C',2),Term('H',6)} and f = {Term('C',1),Term('H',3),Term('C',1),Term('H',3)} 
    // would return true 
    public boolean isomer(Formula f)
    {
        this.makeMolecular();
        f.makeMolecular();
        return this.terms.equals(f.terms);
    }
}