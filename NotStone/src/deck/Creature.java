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

import heroes.*;

public class Creature extends Card{
    public enum deathEffects{
        None, 
        None1, 
        Martydom,
        Replacement
    }
    protected int health = 0;
    protected int attack = 0;
    protected deathEffects death = deathEffects.None;
    
    // Tells you what the death Effects r
    private String deathToString()
    {
        String test = "";
        if (this.death == deathEffects.None || this.death == deathEffects.None1)
        {
            test = "None";
        }
        else if (this.death == deathEffects.Martydom)
        {
            test = "Kill a random enemy creature";
        }
        else if (this.death == deathEffects.Replacement)
        {
            test = "Replace with a card from the hand";
        }
        return test;
    }
    
    // Empty Constructor
    public Creature()
    {
        
    }
    
    public Creature(String _name, int _resource, int _health, int _attack, int _death)
    {
        super(_name, _resource);
        this.health = _health;
        this.attack = _attack;
        this.death = deathEffects.values()[_death];
    }
    
    public int getHealth()
    {
        return this.health;
    }
    
    public void setHealth(int _i)
    {
        this.health = _i;
    }
    
    public void setAttack(int _i)
    {
        this.attack = _i;
    }
    
    public boolean checkHealth(int attack)
    {
        if (this.health - attack < 1)
        {
            return false;
        }
        return true;
    }
    
    public int getAttack()
    {
        return this.attack;
    }
    
    public int getDeathNumber()
    {
        return this.death.ordinal();
    }
    
    public void updateHealth()
    {
        if (this instanceof Hero)
        {
            
        }
    }
    
    @Override
    public String toString()
    {
        String test = "* Creature *\n" + 
                "Name: " + super.name + "\n" +
                "Resource: " + super.resource + "\n" +
                "HP: " + this.health + "\n" +
                "Attack: " + this.attack + "\n" + 
                "Death: " + this.deathToString();
        return test;
    }
    
}
