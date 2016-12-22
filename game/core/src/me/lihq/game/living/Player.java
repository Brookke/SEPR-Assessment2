package me.lihq.game.living;

import me.lihq.game.GameMain;
import me.lihq.game.models.Inventory;
import me.lihq.game.models.Room;

/**
 * This class defines the player that the person playing the game will be represented by.
 */
public class Player extends AbstractPerson
{

    /**
     * The personality will be a percent score (0-100) 50 being neutral etc etc
     */
    private int personalityLevel = 50;

    /**
     * inventory holds items collected by the player.
     */
    private Inventory inventory = new Inventory();

    /**
     * The score the player has earned so far.
     */
    private int score = 0;

    /**
     * Whether the player is moving.
     */
    public Boolean move = false;

    /**
     * Player name.
     */
    private String name;

    /**
     * The room the player is currently exploring.
     */
    private Room currentRoom;

    /**
     * @param name - The name for the new player.
     * @param imgSrc - The image used to represent it.
     */
    public Player(String name, String imgSrc)
    {
        super(imgSrc);
        this.name = name;
    }

    /**
     * This method will change the players personality by the given amount.
     * It will cap the personality between 0 and 100.
     * <p>
     * If the change takes it out of these bounds, it will change it to the min or max.
     *
     * @param change - The amount to change by, can be positive or negative
     */
    public void addToPersonality(int change)
    {
        personalityLevel = personalityLevel + change;

        if (personalityLevel < 0) {
            personalityLevel = 0;
        } else if (personalityLevel > 100) {
            personalityLevel = 100;
        }
    }


    /**
     * This Moves the player to a new tile.
     * @param dir the direction that the player should move in.
     */
    public void move(Direction dir)
    {
        if (this.state != PersonState.STANDING) {
            return;
        }

        if (!currentRoom.isWalkableTile(this.tileCoordinates.x + dir.getDx(),this.tileCoordinates.y + dir.getDy())) {
            setDirection(dir);
            return;
        }

        initialiseMove(dir);
    }

    /**
     * Getter for inventory.
     * @return - Returns the Inventory of this player.
     */
    public Inventory getInventory()
    {
        return this.inventory;
    }

    /**
     * Getter for player name.
     * @return - Returns the name of this player.
     */
    public String getPlayername()
    {
        return this.name;
    }

    /**
     * Getter for personality.
     * @return - Returns the personality of this player.
     */
    public int getPersonality()
    {
        return this.personalityLevel;
    }

    /**
     * Change from one room to another using room ID.
     * @param roomID - The ID of the room to go to.
     * @param newX - The x coordinate to go to.
     * @param newY - The y coordinate to go to.
     */
    public void changeRoom(int roomID, int newX, int newY)
    {
        changeRoom(GameMain.me.gameMap.getRoom(roomID), newX, newY);
    }

    /**
     * Change from one room to another using room object.
     * @param newRoom - The ID of the room to go to.
     * @param newX - The x coordinate to go to.
     * @param newY - The y coordinate to go to.
     */
    public void changeRoom(Room newRoom, int newX, int newY)
    {
        currentRoom = newRoom;

        this.setTileCoordinates(newX, newY);
    }

    /**
     * Setter for room.
     * @param room - The room you want to set the player to.
     */
    public void setRoom(Room room)
    {
        this.currentRoom = room;
    }

    /**
     * Getter for room.
     * @return - Returns the room the player is currently in.
     */
    public Room getRoom()
    {
        return this.currentRoom;
    }
}
