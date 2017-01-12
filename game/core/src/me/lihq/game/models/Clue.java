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
    private static String imagePath = "clueSheet.png";
    private String clueName = "Super Secret Clue";
    private Vector2Int position;

    //TODO: Clues generate from the killer
    //TODO: Initialise Characters -> Generate Killer -> Generate Clues

    private int roomID;

    private int imageSrcX;
    private int imageSrcY;

    public Clue(String name, int imageSrcX, int imageSrcY)
    {
        super(new Texture(imagePath));
        this.clueName = name;

        this.position = new Vector2Int(0,0);

        this.imageSrcX = imageSrcX * Settings.TILE_SIZE;
        this.imageSrcY = imageSrcY * Settings.TILE_SIZE;
    }

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

    public int getRoomID()
    {
        return roomID;
    }
}
