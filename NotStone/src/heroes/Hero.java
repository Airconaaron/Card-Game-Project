/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heroes;

import deck.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */

// Perhaps the most Ratchety thing i've done. A hero has many similar properties to a creature, hence I grouped them together. This allows you to choose similar thigns to attack later
public class Hero extends Creature{
    /* If the hero has special 0 its PCMR. 1 is Console Pesant*/
    private int Special = -1;
    
    private int resource = 0;
    
    private ArrayList<Card> deck;
    // Should be empty
    private ArrayList<Card> hand;
    
    private static String[] Names= {"PCMR", "Console Peasant"};
    
    public Hero()
    {
    }
    public Hero(int _value, ArrayList<Card> _deck)
    {
        this.Special = _value;
        this.deck = _deck;
        this.hand = new ArrayList<Card>();
        super.health = 30;
        super.name = Names[_value];
        super.attack = 0;
        this.resource = 1;
    }
    
    public int getResource()
    {
        return this.resource;
    }
    
    public int getHealth()
    {
        return super.health;
    }
    
    public void setHealth(int _int)
    {
        super.health = _int;
    }
    
    public int getSpecial()
    {
        return this.Special;
    }
    
    public List<Card> getHand()
    {
        return this.hand;
    }
    
    public List<Card> getDeck()
    {
        return this.deck;
    }
    
    public boolean checkResource(Card _card)
    {
        int resource = _card.getResource();
        int totalResource = this.getResource();
        
        if (totalResource - resource < 0)
        {
            return false;
        }
        return true;
    }
    
    public void setResource(int _i)
    {
     this.resource = _i;   
    }
    
    public void setAttack(int _i)
    {
        super.setAttack(_i);
    }
    
    @Override
    public String toString()
    {
        return ("Hero \n" + "Name: " + super.name + "\n" +
                "HP: " + this.health + "\n");
    }
}
