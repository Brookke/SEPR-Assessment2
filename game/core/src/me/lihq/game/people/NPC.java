package me.lihq.game.people;
import me.lihq.game.GameMain;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The class which is responsible for the non-playable characters within the game that the player will meet.
 */
public class NPC extends AbstractPerson
{

    //These variables are specific to the NPC only

    private Random random;

    /**
     * The motive string details why the NPC committed the murder.
     */
    private String motive = "";

    //The NPCs 'blood' graphics will also be on the regular NPCs sprite sheet


    //These are characteristics about the NPC that could be used as clues by the player in a "Guess who" style.

    /**
     * These two booleans decide whether an NPC has the potential to be a killer and if, in this particular game, they
     * are the killer.
     */
    private boolean canBeKiller = false;
    private boolean isKiller = false;
    private boolean isVictim = false;

    /**
     * T that
     */
    private Personality personality;

    /**
     * Associated clues
     */
    public List<Clue> associatedClues = new ArrayList<>();

    /**
     * Define an NPC with location coordinates , room, spritesheet and whether or not they can be the killer
     *
     * @param tileX       - x coordinate of tile that the NPC will be initially rendered on.
     * @param tileY       - y coordinate of tile that the NPC will be initially rendered on.
     * @param room        - ID of room they are in
     * @param spriteSheet - Spritesheet for this NPC
     * @param canBeKiller - Boolean whether they can or cannot be the killer
     */
    public NPC(String name, String spriteSheet, int tileX, int tileY, Room room, boolean canBeKiller, Personality personality)
    {
        super(name, "npc/" + spriteSheet, tileX, tileY);
        this.setRoom(room);
        this.personality = personality;
        this.random = new Random();
        this.canBeKiller = canBeKiller;

        importDialogue(name+".JSON");

    }


    @Override
    public void update() {
        super.update();
        this.randomMove();
    }
    /**
     * Allow the NPC to move around their room.
     * @param dir the direction person should move in
     */
    public void move(Direction dir)
    {
        if (this.state != PersonState.STANDING) {
            return;
        }

        if (!getRoom().isWalkableTile(this.tileCoordinates.x + dir.getDx(), this.tileCoordinates.y + dir.getDy())) {
            setDirection(dir);
            return;
        }

        initialiseMove(dir);
    }

    private void randomMove()
    {
        if (getState() == PersonState.WALKING) return;

        if (random.nextDouble() > 0.01) {
            return;
        }

        Direction dir;

        Double dirRand = random.nextDouble();
        if (dirRand < 0.5) {
            dir = this.direction;
        } else if (dirRand < 0.62) {
            dir = Direction.NORTH;
        } else if (dirRand < 0.74) {
            dir = Direction.SOUTH;
        } else if (dirRand < 0.86) {
            dir = Direction.EAST;
        } else {
            dir = Direction.WEST;
        }

        move(dir);
    }



    /**
     * Getter for canBeKiller
     *
     * @return Returns value of canBeKiller for this object.
     */
    public boolean canBeKiller()
    {
        return canBeKiller;
    }

    /**
     * Getter for isKiller.
     *
     * @return Return a value of isKiller for this object.
     */
    public boolean isKiller()
    {
        return isKiller;
    }

    /**
     * Getter for isVictim
     *
     * @return Returns the value of isVictim for this object
     */
    public boolean isVictim() {return isVictim;}

    /**
     * Getter for motive.
     *
     * @return Returns the motive string for this object.
     */
    public String getMotive()
    {
        return motive;
    }

    /**
     * Setter for the NPC's motive string.
     *
     * @param motive - The motive this particular NPC has for committing the murder.
     * @return Returns the NPC object as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setMotive(String motive)
    {
        this.motive = motive;
        return this;
    }

    /**
     * This method sets the NPC as the killer for this game.
     *
     * It first checks they arent the victim and if they can be the killer
     *
     * @return Returns whether it successfully set the NPC to the killer or not
     */
    public boolean setKiller()
    {
        if (isVictim() || !canBeKiller) return false;

        isKiller = true;
        System.out.println(getName() + " is the killer");
        return true;
    }

    /**
     * This method sets the NPC to be the victim for the game
     *
     * It first checks if the NPC isn't also the killer
     *
     * @return Returns whether it successfully set the NPC to the victim or not
     */
    public boolean setVictim()
    {
        if (isKiller()) return false;

        isVictim = true;
        System.out.println(getName() + " is the victim");
        return true;
    }



    /**
     * This handles speech for a clue that has a question style
     *
     * @param clue  the clue to be questioned about
     * @param style the style of questioning
     * @return the speech
     */
    @Override
    public String getSpeech(Clue clue, Personality style)
    {
        if (style == this.personality) {
            return getSpeech(clue);
        } else {
            return getSpeech("");
        }
    }

    @Override
    public Personality getPersonality()
    {
        return this.personality;
    }
}
