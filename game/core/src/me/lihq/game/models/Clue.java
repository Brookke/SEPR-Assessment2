package me.lihq.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Settings;

import java.util.Set;

/**
 * This class defines the clues that the player needs to find in order to solve the murder.
 */
public class Clue extends Sprite
{
    /**
     * The image to be used for the clue.
     */
    private static String imagePath = "clueSheet.png";

    /**
     * The name of the clue.
     */
    private String clueName = "Super Secret Clue";

    /**
     * The 2D vector position of the clue.
     */
    private Vector2Int position;

    //TODO: Clues generate from the killer
    //TODO: Initialise Characters -> Generate Killer -> Generate Clues

    private int roomID;

    private int imageSrcX;
    private int imageSrcY;

    /**
     * Creates a new clue.
     * @param name - Clue name.
     * @param roomID - RoomID of room the clue is in.
     * @param x - x coordinate of clues position.
     * @param y - y coordinate of clues position.
     * @param imageSrcX - x coordinate of imageSrc.
     * @param imageSrcY - y coordinate of clues imageSrc.
     */
    public Clue(String name, int imageSrcX, int imageSrcY)
    {
        super(new Texture(imagePath));
        this.clueName = name;

        this.position = new Vector2Int(0,0);

        this.imageSrcX = imageSrcX * Settings.TILE_SIZE;
        this.imageSrcY = imageSrcY * Settings.TILE_SIZE;
    }

    /**
     * @param obj - The clue object.
     * @return - Returns if it is equal as a boolean result.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Clue) {
            Clue c = (Clue) obj;

            //Might have to do same coordinates AND same room AND same name

            return c.getClueName().equals(this.getClueName());
        }

        return false;
    }

    /**
     * Getter for Clue name.
     * @return - Returns name of clue.
     */
    public String getClueName()
    {
        return this.clueName;
    }

    public void setClueName(String name)
    {
        this.clueName = name;
    }

    public Clue setCoords(Vector2Int v)
    {
        return setCoords(v.x, v.y);
    }

    /**
     * Setter for clue coordinates.
     * @param x - The x coordinate for where the clue is.
     * @param y - The y coordinate for where the clue is.
     */
    public Clue setCoords(int x, int y)
    {
        this.position.x = x;
        this.position.y = y;

        return this;
    }

    public Vector2Int getPosition()
    {
        return this.position;
    }

    public Clue setRoomID(int roomID)
    {
        this.roomID = roomID;
        return this;
    }

    /**
     * Getter for RoomID.
     * @return - Returns the ID.
     */
    public int getRoomID()
    {
        return roomID;
    }
}
