package me.lihq.game.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.lihq.game.Assets;
import me.lihq.game.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the inventory which is where the player goes to see what items they have found so far.
 */
public class Inventory
{

    private List<Clue> clues = new ArrayList<>();

    //Constructor
    public Inventory()
    {

    }


    /**
     * Adds a clue to the inventory
     * @param clue
     */
    public void addClue(Clue clue)
    {
        if (!this.hasClue(clue)) {
            clues.add(clue);
        }
    }


    /**
     * Checks to see if a clue is in the inventory based on a name.
     * @param name the name of the clue to check
     * @return true if it does contain the clue, false otherwise
     */
    public boolean hasClue(String name)
    {
        for (Clue c: getClues()) {
            if (c.getName() == name) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks to see if a clue is in the inventory based on a name.
     * @param clue the clue to check
     * @return true if it does contain the clue, false otherwise
     */
    public boolean hasClue(Clue clue)
    {
        return getClues().contains(clue);
    }


    /**
     * This the list of clues contained in the inventory
     * @return List of clues
     */
    public List<Clue> getClues()
    {
        return this.clues;
    }



}