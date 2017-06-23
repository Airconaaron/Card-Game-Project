/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notstone;

import java.math.*;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;


import java.io.*;
import java.io.IOException;
import java.lang.Object;

import deck.*;
import game.*;
import heroes.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;


/**
 *
 * @author Aaron Pang
 */
public class NotStoneGUI extends JFrame{
    private CardButton[] buttonEnemyBoard = new CardButton[7];
    private CardButton[] buttonPlayerBoard = new CardButton[7];
    
    private CardButton[] playerHand = new CardButton[10];
    
    private JButton endTurn;
    private JButton specialAbility1;
    private JButton specialAbility2;
    
    private JLabel playerHealth;
    private JLabel enemyHealth;
    
    private JLabel playerResource;
    private JLabel enemyResource;
    
    private JLabel heroIcon;
    private JLabel enemyIcon;
    
    private JLabel turnNumber;
    
    private JPanel board;
    private JPanel center;
    private JPanel playerSpace;
    private JPanel enemySpace;
    
 
    
    private JPanel playerDeck;
    
    private Game game;
    
    private void createContent()
    {
        // Buttons for the Board
        for (int i = 0; i < 7; i++) {
                    buttonEnemyBoard[i] = new CardButton("Enemy Deck Slot " +(i + 1), new Card());
                    final int j = i;
                    buttonEnemyBoard[j].addActionListener(e -> this.checkOut(buttonEnemyBoard[j]));
                    this.board.add(buttonEnemyBoard[i]);
                    
                }
                
        for (int i = 0; i < 7; i++) {
                    buttonPlayerBoard[i] = new CardButton("Player Deck Slot " + (i + 1), new Card());
                    final int j = i;
                    buttonPlayerBoard[j].addActionListener(e -> this.getGUIAttack(buttonPlayerBoard[j]));
                    this.board.add(buttonPlayerBoard[i]);
        }
        
                // Dummy Icons for the Player Hand
        for (int i = 0; i < 10; i++) {
            playerHand[i] = new CardButton("Player Hand " + (i + 1), new Card());
            final int j = i;
            playerHand[j].addActionListener(e -> this.placeDeck(playerHand[j]));
            this.playerDeck.add(playerHand[i]);
        }
        
        this.playerSpace.add(this.playerDeck, BorderLayout.PAGE_START);
        
        //Resizes the images
        //String s = this.class.getResource("/test.jpg");
        //System.out.print(NotStone.class.getResource("/Hero_Icon_Default.png"));
        ImageIcon DEFAULT = new ImageIcon(new ImageIcon(NotStone.class.getResource("/Hero_Icon_Default.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        
        //Since the player hasn't chosen a hero yet make them blank cards first
        this.heroIcon = new JLabel(DEFAULT, JLabel.CENTER);
        this.heroIcon.setLayout(new BorderLayout());
        
        // Hence Health and Resource are just JLabels with methods that can update them
        this.playerHealth = new JLabel("HP: 30/30", JLabel.CENTER);
        this.heroIcon.add(this.playerHealth, BorderLayout.SOUTH);
        
        this.playerResource = new JLabel("Resource: 1/10", JLabel.CENTER);
        this.heroIcon.add(this.playerResource, BorderLayout.NORTH);
        
        this.enemyIcon = new JLabel(DEFAULT, JLabel.CENTER);
        this.enemyIcon.setLayout(new BorderLayout());
        
        this.enemyResource = new JLabel("Resource: 1/10", JLabel.CENTER);
        this.enemyIcon.add(this.enemyResource, BorderLayout.NORTH);
     
        this.enemyHealth = new JLabel("HP: 30/30", JLabel.CENTER);
        this.enemyIcon.add(this.enemyHealth, BorderLayout.SOUTH);
        
        this.specialAbility1 = new JButton("Special Power");
        this.specialAbility1.addActionListener(e -> this.playerSpecialButton());
        this.heroIcon.add(this.specialAbility1, BorderLayout.EAST);
      
        // Second button for enemy just to make things symmetrical
        this.specialAbility2 = new JButton("Special Power");
        this.specialAbility2.addActionListener(e -> this.enemySpecialButton());
        this.enemyIcon.add(this.specialAbility2, BorderLayout.EAST);
        
        this.playerSpace.add(this.heroIcon, BorderLayout.CENTER);
        
        this.enemySpace.add(this.enemyIcon);

        
        this.endTurn = new JButton("End Turn");
        this.endTurn.addActionListener(e -> this.endTurnFunction());
        
        this.turnNumber = new JLabel("Turn Number: 1");
        
        JPanel eastCore = new JPanel(new BorderLayout());
        
        eastCore.add(endTurn, BorderLayout.CENTER);
        eastCore.add(turnNumber, BorderLayout.NORTH);
        this.board.setPreferredSize(new Dimension(1000,400));
        this.center.add(this.board);
        this.center.add(eastCore, BorderLayout.EAST);
        
        
        
                super.getContentPane().add(this.enemySpace, BorderLayout.PAGE_START);
                super.getContentPane().add(this.center, BorderLayout.CENTER);
                super.getContentPane().add(this.playerSpace, BorderLayout.PAGE_END);
                
		super.pack();
    }
    
    public void showFrame()
    {
        super.setVisible(true);
    }
    
    public void enemySpecialButton()
    {
        JOptionPane.showMessageDialog(this, "What are you doing?");
    }
    
    // Method invoked by the endTurn button
    public void endTurnFunction()
    {
        this.game.addToTurn();
        int turns = this.game.getTurn();
        System.out.println(turns);
        
        // Change all the board cards to be able to attack
        for (int i = 0; i < this.buttonPlayerBoard.length; i++) {
            if (!(this.buttonPlayerBoard[i].isEmpty()))
            {
                this.buttonPlayerBoard[i].changeAttacked(true);
            }
        }
        
        // Update Resources
        if (turns <= 10)
        {
        this.game.getPlayer().setResource(turns);
        this.updatePlayerResource(turns);
        }
        else
        {
            this.game.getPlayer().setResource(10);
            this.updatePlayerResource(10);
        }
        // Make the AI Play here

        this.game.getAI().runTurn();
        this.updateTurn();
        
        // draw a card
        try
        {
        this.game.addToHand(this.game.getPlayer());
        this.game.addToHand(this.game.getEnemy()); 
        }
        catch (java.util.ConcurrentModificationException e)
        {
            
        }
        this.game.history += "TURN" + this.game.getTurn();
        this.game.history += "Player HP :" + this.game.getPlayer().getHealth() + "Opponent HP: " + this.game.getEnemy();
        
    }
    
    // Method invoked by the special buttons
    public void playerSpecialButton()
    {
        if (this.game.getPlayer().getSpecial() == 0)
        {
            // If he is PCMR
            //NotStone.class.getResource("/Hero_Icon_Default.png")
            ImageIcon PCMR = new ImageIcon(new ImageIcon(NotStone.class.getResource("/Hero_Icon_PCMR.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
            
            String startOption[] = {"Yes", "No"};
		int value = JOptionPane.showOptionDialog(null, "Do you want to heal yourself at a cost of 2 resources?", "Special Ability", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, PCMR, startOption, startOption[0]);
                
                if (value == JOptionPane.YES_OPTION)
                {
                    if (this.game.getPlayer().checkResource(new Card("Player action", 2)))
                    {
                        // Healing spell
                        
                        this.heal(this.game.getPlayer(), 2);
                        // Update Resources 
                        int final_resource = this.game.getPlayer().getResource() - 2;
                            this.game.getPlayer().setResource(final_resource);      
                            this.updatePlayerResource(final_resource);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Not enough resources to Heal!");
                    }
                    
                }
                    
            
            
        }
        else
        {
            ImageIcon Peasant = new ImageIcon(new ImageIcon(NotStone.class.getResource("/Hero_Icon_Pesant.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
            
                String startOption[] = {"Yes", "No"};
		int value = JOptionPane.showOptionDialog(null, "Do you want to damage your opponent at a cost of 2 resources?", "Special Ability", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, Peasant, startOption, startOption[0]);
                
                if (value == JOptionPane.YES_OPTION)
                {
                    if (this.game.getPlayer().checkResource(new Card("Player action", 2)))
                    {
                        // attack spells n shit
                        this.attack(this.game.getPlayer(), this.game.getEnemy(), this.game.getPlayer());
                        
                        // Update Resources 
                        int final_resource = this.game.getPlayer().getResource() - 2;
                            this.game.getPlayer().setResource(final_resource);      
                            this.updatePlayerResource(final_resource);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Not enough resources to attack!");
                    }
                    
                }
        }
    }
    
    public CardButton getPlayerHandButton(int _int)
    {
        return this.playerHand[_int];
    }
    
    public CardButton getPlayerDeckButton(int _int)
    {
        return this.buttonPlayerBoard[_int];
    }
    
    public CardButton getEnemyDeckButton(int _int)
    {
        return this.buttonEnemyBoard[_int];
    }
    
    public CardButton[] getEnemyDeckButtonBoard()
    {
        return this.buttonEnemyBoard;
    }
    
    public CardButton firstEmpty(CardButton[] _bList)
    {
        CardButton empty = new CardButton();
        for (int i = 0; i < _bList.length; i++) {
            if (_bList[i].isEmpty())
            {
                return _bList[i];
            }
        }
        return empty;
    }
    
    public CardButton[] getPlayerHand()
    {
        return this.playerHand;
    }
    
    public CardButton[] getPlayerDeck()
    {
        return this.buttonPlayerBoard;
    }
    
    public CardButton[] getEnemyDeck()
    {
        return this.buttonEnemyBoard;
    }
    
    // After deciding which hero to use this method will update the respective special buttons
    public void updateSpecialButtons(int _heroVal)
    {
        if (_heroVal == 0)
        {
            this.specialAbility1.setText("Special Ability: Heal");
            this.specialAbility2.setText("Special Ability: Damage");
        }
        else if (_heroVal == 1)
        {
            this.specialAbility1.setText("Special Ability: Damage");
            this.specialAbility2.setText("Special Ability: Heal");
        }
    }
    
    public void refreshBoard()
    {
        for (int i = 0; i < 7; i++)
        {
            // Only refresh if there is something in that button
            if(!(this.buttonEnemyBoard[i].getCard().isEmpty()))
            {
                this.buttonEnemyBoard[i].changeCard(this.buttonEnemyBoard[i].getCard());
            }
            
            if (!(this.buttonPlayerBoard[i].isEmpty()))
            {
                this.buttonPlayerBoard[i].changeCard(this.buttonPlayerBoard[i].getCard());
            }
        }
    }
    
    
    public Game getGame()
    {
        return this.game;
    }

    
    // This is the first File Chooser thing that pops up
    public static String exitClose()
    {
        String file = "";
                        
                JFileChooser chooser = new JFileChooser();
                
                String startOption[] = {"Load deck from file", "Exit"};
		int value = JOptionPane.showOptionDialog(null, "Load Deck or Exit?", "Game Start", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, startOption, startOption[0]);
		
		if (value == JOptionPane.NO_OPTION || value == JOptionPane.CLOSED_OPTION)
		{
			System.out.println("clicked EXIT");
			System.exit(0);
		} else if (value == JOptionPane.YES_OPTION)
		{
			System.out.println("Loading Deck...");
			int returnVal = chooser.showOpenDialog(null);
                        if (returnVal == JFileChooser.CANCEL_OPTION)
                        {
                            System.exit(0);
                        }
                        else if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		            chooser.getSelectedFile().getName());
		    }
                    file = chooser.getSelectedFile().getAbsolutePath();
		}
                 return file;
    }
    
    public void heal(Hero _person, int power)
    {
        int health = _person.getHealth();
        if (power + health > 30)
        {
            updateHP(_person, 30);
        }
        else
        {
            updateHP(_person, health + power);
        }
    }
    
    // Our generalized attack function used by the player
    public void attack(Card _attacker, Creature _defender, Hero _person)
    {
        int power = 0;
        if (_attacker instanceof Spell)
        {
            Spell spell = (Spell) _attacker;
            power = spell.getPower();
        }
        else if (_attacker instanceof Creature)
        {
            Creature creature = (Creature) _attacker;
            power = creature.getAttack();
        }
        
        // Can the defender survive this attack?
        if (_defender.checkHealth(power))
        {
            int newHealth = _defender.getHealth() - power; 
            _defender.setHealth(newHealth);
            if (_defender instanceof Hero)
            {
            Hero dude = (Hero) _defender;
             this.updateHP(dude, newHealth);   
            }
            this.refreshBoard();
        }
        else
        {
            if (_defender instanceof Hero)
            {
                // Game Over make this do 
                String startOption[] = {"Print History", "Exit"};
                int val = -1;
                
                int value = JOptionPane.showOptionDialog(this, 
                new JLabel("Game Over", null, JLabel.LEFT), 
                "Game Over", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                startOption, 
                startOption[0]);
                
                if (value == JOptionPane.YES_OPTION)
                {
                    try{
                    File file = new File("History.txt");
                    file.createNewFile();
                    
                    
                    FileWriter fileWrite = new FileWriter(file);
                    fileWrite.write(this.game.history);
                    fileWrite.flush();
                    fileWrite.close();
                    System.exit(1);
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else 
                {
                    System.out.println("Ciao!");
                    System.exit(0);
                }
            }
            // Check for death effects and delete card
            if (_defender.getDeathNumber() == 3)
            {
                int resource = _defender.getResource();
                // Card replacement 
                
                // Delete First
                // If the person is the player delete from the enemy board and draw one from the enemy board
                if (_person.equals(this.game.getPlayer()))
                {
                  for (int i = 0; i < this.buttonEnemyBoard.length; i++) {
                            if (this.buttonEnemyBoard[i].getText().contains(_defender.getName()))
                            {
                                //this.game.getPlayer().getHand().remove(_.getCard());
                                updateCard(this.buttonEnemyBoard[i], new Card());
                                this.buttonEnemyBoard[i].setText("Player Deck Slot " + (i+1));
                            
                                break;
                            }
                    }
                

                    // See if there is a suitable card for replacement
                    for (int i = 0; i < this.game.getEnemy().getHand().size(); i++)
                    {
                        // First one that is of less resource than the original
                        if (this.game.getEnemy().getHand().get(i).getResource() < resource)
                        {
                            this.updateCard(this.firstEmpty(this.buttonEnemyBoard), this.game.getEnemy().getHand().get(i));
                            this.game.getEnemy().getHand().remove(this.game.getEnemy().getHand().get(i));
                            
                            // No need to update the hand
                                   // updateCard(this.enemyHand[i], new Card());
                                 
                                    //this.enemyHand[i].setText("Player Hand " + (i+1));
                                    this.refreshBoard();
                                    return;
                        }
                    }  
                }
                // Opposite
                else if (_person.equals(this.game.getEnemy()))
                {
                    for (int i = 0; i < this.buttonPlayerBoard.length; i++) {
                            if (this.buttonPlayerBoard[i].getText().contains(_defender.getName()))
                            {
                                //this.game.getPlayer().getHand().remove(_.getCard());
                                updateCard(this.buttonPlayerBoard[i], new Card());
                                this.buttonPlayerBoard[i].setText("Player Deck Slot " + (i+1));
                            
                                break;
                            }
                    }
                

                    // See if there is a suitable card for replacement
                    for (int i = 0; i < this.game.getEnemy().getHand().size(); i++)
                    {
                        // First one that is of less resource than the original
                        if (this.playerHand[i].getCard().getResource() < resource)
                        {
                            this.updateCard(this.firstEmpty(this.buttonPlayerBoard), this.playerHand[i].getCard());
                            this.game.getPlayer().getHand().remove(this.playerHand[i].getCard());

                                   updateCard(this.playerHand[i], new Card());
                                 
                                    this.playerHand[i].setText("Player Hand " + (i+1));
                                    this.refreshBoard();
                                    return;
                        }
                    } 
                }
                

                
                
            }
            else if (_defender.getDeathNumber() == 2)
            {

                    // If the person is the player delete from the enemy board and draw one from the enemy board
                if (_person.equals(this.game.getPlayer()))
                {
                  for (int i = 0; i < this.buttonEnemyBoard.length; i++) {
                            if (this.buttonEnemyBoard[i].getText().contains(_defender.getName()))
                            {
                                //this.game.getPlayer().getHand().remove(_.getCard());
                                updateCard(this.buttonEnemyBoard[i], new Card());
                                this.buttonEnemyBoard[i].setText("Player Deck Slot " + (i+1));
                            
                                break;
                            }
                    }
                    // Remove a random enemy Card
                  int random = (int) Math.random() * 7;
                  updateCard(this.buttonEnemyBoard[random], new Card());
                  this.buttonEnemyBoard[random].setText("Player Deck Slot " + (random+1));
                  this.refreshBoard();
                  return;

                }
                // Opposite
                else if (_person.equals(this.game.getEnemy()))
                {
                    for (int i = 0; i < this.buttonPlayerBoard.length; i++) {
                            if (this.buttonPlayerBoard[i].getText().contains(_defender.getName()))
                            {
                                //this.game.getPlayer().getHand().remove(_.getCard());
                                updateCard(this.buttonPlayerBoard[i], new Card());
                                this.buttonPlayerBoard[i].setText("Player Deck Slot " + (i+1));
                            
                                break;
                            }
                    }
                        // Remove a random enemy Card
                    int random = (int) Math.random() * 7;
                    updateCard(this.buttonPlayerBoard[random], new Card());
                    this.buttonPlayerBoard[random].setText("Player Deck Slot " + (random+1));
                    this.refreshBoard();
                    return;

                }

                
                
            }        
                
        }
        
    }
    
    
    // int return value tells people which hero you chose
    public int heroChooser()
    {
        String startOption[] = {"Console Peasant", "PCMR"};
        int val = -1;
        ImageIcon Peasant = new ImageIcon(new ImageIcon(NotStone.class.getResource("/Hero_Icon_Peasant.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        ImageIcon PCMR = new ImageIcon(new ImageIcon(NotStone.class.getResource("/Hero_Icon_PCMR.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        
        int value = JOptionPane.showOptionDialog(this, 
                new JLabel("Choose a hero", Peasant, JLabel.LEFT), 
                "Class Chooser", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                PCMR, 
                startOption, 
                startOption[1]);

		
		if (value == JOptionPane.CLOSED_OPTION)
		{
			System.out.println("clicked EXIT");
			System.exit(0);
		} else if (value == JOptionPane.YES_OPTION)
                {
                    // Chose the Peasant Option
                    this.heroIcon.setIcon(Peasant);
                    this.enemyIcon.setIcon(PCMR);
                    val = 1;
                }
                else if (value == JOptionPane.NO_OPTION)
                {
                    // Chose PCMR Option
                    this.heroIcon.setIcon(PCMR);
                    this.enemyIcon.setIcon(Peasant);
                    val = 0;
                }
                return val;

    }
    
    public void updatePlayerHP(int _hp)
    {
        if (_hp < 0 || _hp > 30)
        {
            System.out.println("Something bad happened");
        }
        else
        {
            this.playerHealth.setText("HP: " + _hp + "/30");
        }
    }
    
    public void updateEnemyHP(int _hp)
    {
        if (_hp < 0 || _hp > 30)
        {
            System.out.println("Something bad happened");
        }
        else
        {
            this.enemyHealth.setText("HP: " + _hp + "/30");
        }
    }
    
    public void updateHP(Hero _person, int _hp)
    {
        if (_person.equals(this.game.getPlayer()))
        {
            updatePlayerHP(_hp);
        }
        else
        {
            updateEnemyHP(_hp);
        }
    }
    
    public void updatePlayerResource(int _resource)
    {
        if (_resource < 0)
        {
            System.out.println("Something bad happened");
        }
        else if (_resource > 10)
        {
            return;
        }
        else
        {
            this.playerResource.setText("Resource: " + _resource + "/10");
        }
    }
    
    public void updateEnemyResource(int _resource)
    {
        if (_resource < 0)
        {
            System.out.println("Something bad happened");
        }
        else if (_resource > 10)
        {
            return;
        }
        else
        {
            this.enemyResource.setText("Resource: " + _resource + "/10");
        }
    }
    
    public void updateTurn()
    {
     int turn = this.game.getTurn();
     this.turnNumber.setText("Turn Number: " + turn);
    }
    
    public void updateCard(CardButton b, Card _card)
    {
        b.changeCard(_card);
    }
    
    public void errorMessage()
    {
        JOptionPane.showMessageDialog(this, "Invalid Deck Read!");
    }
    
    // Method invoked by the enemy board
    public void checkOut(CardButton _b)
    {
        if (!(_b.isEmpty()))
        {
            ImageIcon ICON = new ImageIcon(new ImageIcon(Game.imageMatch(_b.getCard())).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(
                        null,
                        new JLabel(_b.getCard().toString()),
                        "Enemy Board", JOptionPane.INFORMATION_MESSAGE,
                        ICON);
            // 
            
        }
    }
    
    // Method invoked by the players Deck
    public void getGUIAttack(CardButton _b)
    {
        if (!(_b.isEmpty()))
        {
            // Check if this thing is allowed to attack on this turn
            if(_b.getAttacked())
            {
                ImageIcon ICON = new ImageIcon(new ImageIcon(Game.imageMatch(_b.getCard())).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                Card[] creatureArr = new Card[this.buttonEnemyBoard.length + 1];
                        creatureArr[0] = this.game.getEnemy();
   
                            for (int i = 1; i < this.buttonEnemyBoard.length; i++) {
                                if (!(this.buttonEnemyBoard[i].getCard().isEmpty()))
                                {
                                creatureArr[i] = this.buttonEnemyBoard[i].getCard();
                                }
                            }
                            boolean empty = true;
                            for (int i=0; i<creatureArr.length; i++) {
                                // Exit if there's anything within the array
                              if (creatureArr[i] != null) {
                                empty = false;
                                break;
                              }
                            }
                            
                            if (empty)
                            {
                                JOptionPane.showMessageDialog(this, "Nothing to Attack!");
                                return;
                            }



                        Creature result = (Creature) JOptionPane.showInputDialog(this,
                                new JLabel("Who do you want to attack? "),
                                "Spell Hand",
                                JOptionPane.INFORMATION_MESSAGE,
                                ICON,
                                creatureArr,
                                creatureArr[0]);
                        
                        // Prevent users from selecting nothing;
                        if (result.isEmpty())
                        {
                            JOptionPane.showMessageDialog(this, "Sorry you didn't select anything");
                            return;
                        }
                        
                        // Do actual attack stuff
                        
                        this.attack(_b.getCard(), result, this.game.getPlayer());
                        // Prevent it from attacking again this round
                        _b.changeAttacked(false);

            }
            else
            {
                JOptionPane.showMessageDialog(this,"You can't use this creature to attack!");
            }
            // 
            
        }
    }
    
    // Method invoked by the cards in the players Hand
    public void placeDeck(CardButton _b)
    {
        if (!(_b.isEmpty()))
        {
            if (_b.getCard() instanceof Creature)
            {
                ImageIcon ICON = new ImageIcon(new ImageIcon(Game.imageMatch(_b.getCard())).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                String startOption[] = {"Place", "Discard"};
                int value = JOptionPane.showOptionDialog(null, new JLabel(_b.getCard().toString()), "Card Options", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, ICON, startOption, startOption[0]);
                
                if (value == JOptionPane.CANCEL_OPTION)
                {
                    return;
                }
                else if (value == JOptionPane.YES_OPTION)
                {
                    // Check resources
                    if (this.game.getPlayer().checkResource(_b.getCard()))
                    {
                        this.updateCard(this.firstEmpty(this.buttonPlayerBoard), _b.getCard());
                        //System.out.println(_b.getCard().getName());
                        for (int i = 0; i < this.playerHand.length; i++) {
                            if (this.playerHand[i].getText().contains(_b.getCard().getName()))
                            {
                             // Update Resource
                            int final_resource = this.game.getPlayer().getResource() - _b.getCard().getResource();
                            this.game.getPlayer().setResource(final_resource);      
                            this.updatePlayerResource(final_resource);
                            
                                this.game.history += "Player used" + _b.getCard().getName();
                                this.game.getPlayer().getHand().remove(_b.getCard());
                                updateCard(_b, new Card());
                                _b.setText("Player Hand " + (i+1));
                                
                                return;
                            }
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Not Enough Resources!");
                    }
                }
                else if (value == JOptionPane.NO_OPTION)
                {
                    // If the person presses cancel remove the card
                        for (int i = 0; i < this.playerHand.length; i++) {
                            if (this.playerHand[i].getText().contains(_b.getCard().getName()))
                            {
                                this.game.getPlayer().getHand().remove(_b.getCard());
                                updateCard(_b, new Card());
                                _b.setText("Player Hand " + (i+1));
                                return;
                            }
                        }
                }

            }
            // Now if its a spell card ...
            else if (_b.getCard() instanceof Spell)
            {
                ImageIcon ICON = new ImageIcon(new ImageIcon(Game.imageMatch(_b.getCard())).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                String startOption[] = {"Play", "Discard"};
                int value = JOptionPane.showOptionDialog(null, new JLabel(_b.getCard().toString()), "Card Options", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, ICON, startOption, startOption[0]);
                
                if (value == JOptionPane.CANCEL_OPTION)
                {
                    return;
                }
                else if (value == JOptionPane.NO_OPTION)
                {
                        for (int i = 0; i < this.playerHand.length; i++) {
                            if (this.playerHand[i].getText().contains(_b.getCard().getName()))
                            {
                                this.game.getPlayer().getHand().remove(_b.getCard());
                                updateCard(_b, new Card());
                                _b.setText("Player Hand " + (i+1));
                                return;
                            }
                        }
                }
                else if (value == JOptionPane.YES_OPTION)
                {
                    // First Check Resources
                    if (this.game.getPlayer().checkResource(_b.getCard()))
                    {
                        Card[] creatureArr = new Card[this.buttonEnemyBoard.length + 1];
                        
                        Spell newSpell = (Spell) _b.getCard();
                        
                        if (newSpell.getSpellType() == 2)
                        {
                            // Healing stuff
                            int power = newSpell.getPower();
                            this.heal(this.game.getPlayer(), power);
                        }
                        else 
                        {
                            if (newSpell.getSpellType() == 1)
                            {
                            creatureArr[0] = this.game.getEnemy();
                            }

                            for (int i = 1; i < this.buttonEnemyBoard.length; i++) {
                                if (!(this.buttonEnemyBoard[i].getCard().isEmpty()))
                                {
                                creatureArr[i] = this.buttonEnemyBoard[i].getCard();
                                }
                            }
                            boolean empty = true;
                            for (int i=0; i<creatureArr.length; i++) {
                              if (creatureArr[i] != null) {
                                empty = false;
                                break;
                              }
                            }
                            if (empty)
                            {
                                JOptionPane.showMessageDialog(this, "Nothing to Attack!");
                                return;
                            }


                            Creature result = (Creature) JOptionPane.showInputDialog(this,
                                    new JLabel("Who do you want to attack? "),
                                    "Spell Hand",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    ICON,
                                    creatureArr,
                                    creatureArr[0]);
                            
                            // Do attack stuff
                            this.attack(_b.getCard(), result, this.game.getPlayer());

                            // Delete the card after use
                            for (int i = 0; i < this.playerHand.length; i++) {
                                    if (this.playerHand[i].getText().contains(_b.getCard().getName()))
                                    {
                                    // Update Resource
                            int final_resource = this.game.getPlayer().getResource() - _b.getCard().getResource();
                            this.game.getPlayer().setResource(final_resource);      
                            this.updatePlayerResource(final_resource);
                            
                                        this.game.getPlayer().getHand().remove(_b.getCard());
                                        updateCard(_b, new Card());
                                        _b.setText("Player Hand " + (i+1));
                                        return;
                                    }
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Not enough resources to use!");
                    }
                }
            }
        }
    }
    
    public NotStoneGUI() {
		super("NotStone");
                super.setLayout(new BorderLayout());
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                
                this.playerSpace = new JPanel(new BorderLayout());
                // Initialize a GridLayout with gaps
                this.board = new JPanel(new GridLayout(2,0));
                this.center = new JPanel(new BorderLayout());
                
                this.enemySpace = new JPanel(new BorderLayout());
                
                this.playerDeck = new JPanel(new GridLayout(1,0));
                
                // Make a bunch of dummy buttons
                
                this.createContent();
		this.showFrame();
	}    
    
    public void addGame(Game _game)
    {
        this.game = _game;
    }
}
