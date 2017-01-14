package me.lihq.game.living;
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

    /**
     * Associated clues
     */
    private List<Clue> associatedClues = new ArrayList<>();

    /**
     * Define an NPC with location coordinates , room, spritesheet and whether or not they can be the killer
     *
     * @param tileX       - x coordinate of tile that the NPC will be initially rendered on.
     * @param tileY       - y coordinate of tile that the NPC will be initially rendered on.
     * @param room        - ID of room they are in
     * @param spriteSheet - Spritesheet for this NPC
     * @param canBeKiller - Boolean whether they can or cannot be the killer
     */
    public NPC(String name, String spriteSheet, int tileX, int tileY, Room room, boolean canBeKiller)
    {

        super(name, spriteSheet, tileX, tileY);
        this.setRoom(room);
        this.random = new Random();
        this.canBeKiller = canBeKiller;

    }


    @Override
    public void update() {
        super.update();
        this.randomMove();
    }
    /**
     * Allow the NPC to move around their room.
     */
    public void move(Direction dir)
    {

        if (this.state != PersonState.STANDING) {
            return;
        }


        if (!getRoom().isWalkableTile(this.tileCoordinates.x + dir.getDx(), this.tileCoordinates.y + dir.getDy())) {
            return;
        }
        if (GameMain.me.player.tileCoordinates.x == this.tileCoordinates.x + dir.getDx() && GameMain.me.player.tileCoordinates.y == this.tileCoordinates.y + dir.getDy())
        {
            return;
        }

        initialiseMove(dir);
    }

    private void randomMove() {

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

    public void addClues(List<Clue> clues)
    {
        this.associatedClues.addAll(clues);
    }

    public List<Clue> getClues()
    {
        return this.associatedClues;
    }

    public boolean hasClue(Clue clue)
    {
        return this.associatedClues.contains(clue);

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
     * @return Returna value of isKiller for this object.
     */
    public boolean isKiller()
    {
        return isKiller;
    }

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
}
