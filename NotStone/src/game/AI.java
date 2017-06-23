/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import deck.*;
import heroes.*;
import javax.swing.JOptionPane;

import java.lang.Math;

/**
 *
 * @author Aaron Pang
 */
public class AI {
    Game game;
    
    public AI()
    {
        
    }
    
    public void addGame(Game _game)
    {
        this.game = _game;
    }
    
    public void runTurn ()
    {
        int count = -1;
        int turn = game.getTurn();
        int random = 4;
        
        // Have it pick one card randomly and make sure it can place it
        for (int i = 0; i < this.game.getEnemy().getHand().size(); i++) {
            System.out.println("theres a hand");
            if (this.game.getEnemy().checkResource(this.game.getEnemy().getHand().get(i)))
            {
                // If there is a card that can be played just play it
                this.game.getFrame().updateCard(this.game.getFrame().firstEmpty(this.game.getFrame().getEnemyDeckButtonBoard()), this.game.getEnemy().getHand().get(i));
                this.game.history += "Opponent used" + this.game.getEnemy().getHand().get(i).getName();
            }
        }

        

        // Have it attack with all possible options on the hero
        for (int i = 0; i < this.game.getFrame().getEnemyDeckButtonBoard().length; i++) {
            if (!(this.game.getFrame().getEnemyDeckButton(i).getCard().isEmpty()))
            {
                this.game.getFrame().attack(this.game.getFrame().getEnemyDeckButton(i).getCard(), this.game.getPlayer(), this.game.getEnemy());
            }
        }
    }
    
}
    

        
       