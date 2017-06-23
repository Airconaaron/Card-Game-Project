/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deck;

/**
 *
 * @author Aaron Pang
 */
public class Spell extends Card{
    public enum SpellType
    {
        AttackCreature, 
        AttackHero,
        Heal,
        None
    }
    
    protected int power = 0;
    protected SpellType spellType = SpellType.None;
    
    private String spellTypeString()
    {
        String test = "";
        if (this.spellType == SpellType.AttackCreature)
        {
            test = "Attack Creatures";
        }
        else if (this.spellType == SpellType.AttackHero)
        {
            test = "Attack Hero";
        }
        else if (this.spellType == SpellType.Heal)
        {
            test = "Heal Creatures";
        }
        else
        {
         System.out.println("Something went wrong");   
        }
        return test;
    }
    
    public int getSpellType()
    {
        return this.spellType.ordinal();
    }
    
    public Spell()
    {
        
    }
    
    public Spell(String _name, int _resource, int _spellType)
    {
        super(_name, _resource);
        
        if (_spellType == 2)
        {
            this.spellType = SpellType.values()[_spellType];
            this.power =  (int)Math.floor(_resource * 2.5);
        }
        else
        {
            this.spellType = SpellType.values()[_spellType];
            this.power = (int)Math.floor(_resource * 1.5);
        }
    }
    
    public int getPower()
    {
        return this.power;
    }
    
    @Override
    public String toString()
    {
        return ("*** Spell *** \n" +
                "Name: " + super.name + "\n" +
                "Resource: " + super.resource + "\n" +
                "Type: " + this.spellTypeString() + "\n" +
                "Power: " + this.power);
        
    }
       
}
