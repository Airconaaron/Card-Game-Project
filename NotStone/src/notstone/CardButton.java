/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notstone;

import javax.swing.JButton;
import deck.*;

/**
 *
 * @author user
 */
public class CardButton extends JButton{
    private Card card = new Card();
    private boolean attacked = false;
    
    public CardButton()
    {
        
    }
    
    public boolean getAttacked()
    {
        return this.attacked;
    }
    
    public void changeAttacked(boolean _b)
    {
     this.attacked = _b;   
    }
    
    public CardButton(String _name, Card _card)
    {
        super(_name);
        this.card = _card;
    }
    
    public void changeCard(Card _card)
    {
        if (_card.isEmpty())
        {
            super.setText("empty");
        }
        else
        {
        super.setText("<html>" + _card.toString().replaceAll("\\n", "<br>") + "</html>");
        }
        this.card = _card;
    }
    
    public Card getCard()
    {
        return this.card;
    }
    
    public boolean isEmpty()
    {
        return this.card.getName().isEmpty();
    }
    
}
