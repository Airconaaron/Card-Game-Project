/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notstone;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import deck.*;

/**
 *
 * @author user
 */
public class CSVReader {
    public static ArrayList<Card> ReadFile(String _file) throws InvalidDeckException
    {
        String file = _file;
        String line = "";
        String csvSplit = ",";
        
        ArrayList<Card> Deck = new ArrayList<Card>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] stuff = line.split(csvSplit);
                
                //System.out.println(stuff[1].equals("0"));
                
                if (stuff[1].equals("0"))
                {
                    if(stuff[6].equals("-1"))
                    { 
                        Creature temp = new Creature(stuff[0], Integer.valueOf(stuff[2]), Integer.valueOf(stuff[3]), Integer.valueOf(stuff[4]), Integer.valueOf(stuff[5]));
                        //System.out.println(temp.getAttack());
                        Deck.add(temp);
                    }
                    else
                    {
                    throw new InvalidDeckException();
                    }
                }
                else if (stuff[1].equals("1"))
                {
                    if(stuff[5].equals( "-1"))
                    {                       
                        Spell temp = new Spell(stuff[0], Integer.valueOf(stuff[2]), Integer.valueOf(stuff[6]));
                        Deck.add(temp); 
                    }
                    else
                    {
                        throw new InvalidDeckException();
                    }
               }

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Reading in File");
            System.exit(0);
        }
        return Deck;    
    }
}
