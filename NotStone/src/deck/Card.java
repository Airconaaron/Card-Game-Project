/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deck;

/**
 *
 * @author user
 */
public class Card {
    // Default Values
    protected String name = "";
    protected int resource = 0;
    
    // Empty Constructor
    public Card()
    {
        
    }
    
    public Card(String _name, int _resource)
    {
        this.name = _name;
        this.resource = _resource;
    }
    
    public int getResource()
    {
        return this.resource;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public boolean isEmpty()
    {
        return this.getName().isEmpty();
    }

    
}
