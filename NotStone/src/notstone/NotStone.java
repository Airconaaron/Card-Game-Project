/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notstone;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import deck.*;
import heroes.*;
import game.*;

import java.util.Collections;

import javax.swing.SwingUtilities;

/**
 *
 * @author Aaron Pang
 */
public class NotStone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String file = "";
        
	NotStoneGUI test = new NotStoneGUI();
        file = NotStoneGUI.exitClose();
        int hero_val = test.heroChooser();

        
        List<Card> playerDeck = new ArrayList<Card>();
        List<Card> otherDeck = new ArrayList<Card>();
        try
        {
        ArrayList<Card> deck = CSVReader.ReadFile(file);   
        //System.out.println(deck.get(0).getResource());
        
        playerDeck = deck.subList(0,30);
        otherDeck = deck.subList(31,60);
        }
        catch (InvalidDeckException e)
        {
            e.printStackTrace();
            System.out.println("Your Deck is Invalid");
            // Have it throw out a JDialogue
            test.errorMessage();
            System.exit(-1);
        }
        
        catch (java.lang.ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println("Your Deck is Invalid");
            // Have it throw out a JDialogue
            test.errorMessage();
            System.exit(-1);
        }

        // Shuffle the two decks
        Collections.shuffle(playerDeck);
        Collections.shuffle(otherDeck);
        
        // Set Hero Names and Stuff and Special
        Hero player = new Hero(hero_val, new ArrayList<Card>(playerDeck));
        Hero enemy = new Hero((hero_val + 1)%2, new ArrayList<Card>(otherDeck));
        //test.updateCard(test.getEnemyDeckButton(3), otherDeck.get(1));
        
        
        Game game = new Game(test, player, enemy);
        
        // Changes the respective special buttons
        test.updateSpecialButtons(hero_val);
        test.addGame(game);
        
        // If the player chose the Peasant he is given a sad 2 attack compensation
        if (hero_val == 1)
        {
            test.getGame().getPlayer().setAttack(2);
        }
        
        test.getGame().play();

            
    }
    
}
