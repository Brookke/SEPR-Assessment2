package me.lihq.game.living;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Assets;
import me.lihq.game.Settings;
import me.lihq.game.models.Vector2Int;

/**
 * The abstract person is an abstract representation of a person. A person can be a non playable character or Player.
 * It extends the sprite class which provides methods for the person to be rendered in the game.
 */
public abstract class AbstractPerson extends Sprite
{

    //Storing the characters coordinates on the map
    /**
     * This is the location of the person in the room in terms of tiles eg (0,0) would be the bottom left of the room
     */
    protected Vector2Int tileCoordinates = new Vector2Int(0, 0);
    protected int offsetX = 0;
    protected int offsetY = 0;

    /**
     * The direction determines the way the character is facing.
     */
    protected DIRECTION lastDirection = DIRECTION.NORTH;
    protected DIRECTION moveDirection = null;


    /**
     * This constructs the player calling super on the sprite class
     *
     * @param img this a path to the image
     */
    public AbstractPerson(String img)
    {
        super(Assets.loadTexture(img));

        this.setPosition(tileCoordinates.getX() * Settings.TILE_SIZE, tileCoordinates.getY() * Settings.TILE_SIZE);
    }

    public void setTileCoordinates(int x, int y)
    {
        tileCoordinates.x = x;
        tileCoordinates.y = y;

        setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }

    public int getOffsetX()
    {
        return this.offsetX;
    }

    public void setOffsetX(int offsetX)
    {
        this.offsetX = offsetX;
    }

    public int getOffsetY()
    {
        return this.offsetY;
    }

    public void setOffsetY(int offsetY)
    {
        this.offsetY = offsetY;
    }

    public DIRECTION getLastDirection()
    {
        return this.lastDirection;
    }

    public void setMoveDirection(DIRECTION direction)
    {
        this.moveDirection = direction;
    }

    public abstract void move(int dx, int dy);

    /**
     * movementTick
     *
     * This method is called once every game tick, which is defined in the Settings class
     *
     * It completes tile movement and checks for more inputs. If a player or NPC has an offset != 0, they are currently
     * moving so it completes the movement cycle
     */
    public abstract void movementTick();

    public enum DIRECTION
    {
        NORTH, SOUTH, EAST, WEST
    }
}
