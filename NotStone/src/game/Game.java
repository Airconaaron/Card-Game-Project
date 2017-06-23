/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import deck.*;
import heroes.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import notstone.*;

import java.net.*;
import java.io.*;

/**
 *
 * @author user
 */
public class Game {
    protected Hero player;
    protected Hero enemy;
    protected NotStoneGUI frame;
    protected AI ai;
    
    public String history;
    
    int turns = 1;
    
    public Game()
    {
    }

    
    public Game(NotStoneGUI _frame, Hero _player, Hero _enemy)
    {
        this.frame = _frame;
        this.player = _player;
        this.enemy = _enemy;
    }
    
    public NotStoneGUI getFrame()
    {
        return this.frame;
    }
    
    public void play()
    {
        // Draw 3 cards
        for (int i = 0; i < 3; i++) {
            this.addToHand(player);
            // SOmething about concurrent access
        }
        
        AI ai = new AI();
        
        // Add them to each other in some hedonistic fantasy
        this.addAI(ai);
        ai.addGame(this);
        
        for (int j = 0; j < 3; j++) {
            this.addToHand(enemy);
        }
        
        System.out.println(this.getEnemy().getHand().get(1).getResource());
        
    }
    
    public void addAI(AI _ai)
    {
        this.ai = _ai;
    }
    
    public AI getAI()
    {
        return this.ai;
    }
    
    public Hero getPlayer()
    {
        return this.player;
    }
    
    public Hero getEnemy()
    {
        return this.enemy;
    }
    
    public void addToTurn()
    {
        this.turns += 1;
    }
    
    public int getTurn()
    {
        return this.turns;
    }
    
    // Draw Card method
    public void addToHand(Hero _person)
    {
        if (_person.getDeck().isEmpty())
        {
            System.out.println("Deck is empty");
            System.out.println("getPlayer().health is " + this.getPlayer().getHealth());
            System.out.println("getEnemy().health is " + this.getEnemy().getHealth());
            // Ran out of Cards Game should end
            if (this.getPlayer().getHealth() > this.getEnemy().getHealth())
            {
                // THE PLAYER WON
                JOptionPane.showMessageDialog(this.frame, "Game Over!");
            }
            else if (this.getPlayer().getHealth() == this.getEnemy().getHealth())
            {
                // TIE
                JOptionPane.showMessageDialog(this.frame, "Game Over!");
            }
            else
            {
                // THE PLAYER LOST
            }
        }
        
        Card newCard = _person.getDeck().get(0);
        // Remove this card
        _person.getDeck().remove(newCard);
        
        // Add to the List of the Hand
        _person.getHand().add(newCard);
        if (_person.equals(this.getPlayer()))
        {
            this.frame.updateCard(this.frame.firstEmpty(this.frame.getPlayerHand()), newCard);
        }
        else
        {
            System.out.println(this.getEnemy().getHand().get(3).getName());
        }
        
    }
    
    public static URL imageMatch(Card _card)
    {
        URL returner = null;
        
        String name = _card.getName();
        
        String stuff[] = name.split(" the ");
        
        //String s = NotStone.class.getResource("/Creatures");
        
        if (_card instanceof Creature)
        {
            returner = NotStone.class.getResource("/Creatures/" + stuff[0] + ".jpg");
        }
        else if (_card instanceof Spell)
        {
            returner = NotStone.class.getResource("/Spells/" + stuff[0] + ".jpg");
        }
      
        return returner;

    }
    
}
