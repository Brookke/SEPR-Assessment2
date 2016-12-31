package me.lihq.game.living;

import jdk.nashorn.internal.parser.JSONParser;
import me.lihq.game.GameMain;
import me.lihq.game.models.Inventory;
import me.lihq.game.models.Room;
//import org.json.simple.JSONObject;              //------------------------------------------------------------------------------------------------------------------------------------why cant it find this?
//import org.json.simple.JSONArray;               //------------------------------------------------------------------------------------------------------------------------------------------------------------
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import java.util.Random;

/**
 * This class defines the player that the person playing the game will be represented by.
 */
public class Player extends AbstractPerson
{
    /**
     * Dictionary to be populated with dialogue of the character.
     */
    Map<String, String[]> dialogue = new HashMap<String, String[]>();

    //The personality will be a percent score (0-100) 50 being neutral etc etc
    private int personalityLevel = 50;
    private Inventory inventory = new Inventory();
    private int score = 0;


    public Player(String name, String imgSrc, int tileX, int tileY)
    {
        super(name, imgSrc, tileX, tileY);
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
     *
     * @param dir the direction that the player should move in.
     */
    public void move(Direction dir)
    {
        if (this.state != PersonState.STANDING) {
            return;
        }

        if (this.isOnTriggerTile() && dir.toString().equals(getRoom().getMatRotation(this.tileCoordinates.x, this.tileCoordinates.y))) {
            GameMain.me.getNavigationScreen().initialiseRoomChange();
            return;
        }

        if (!getRoom().isWalkableTile(this.tileCoordinates.x + dir.getDx(), this.tileCoordinates.y + dir.getDy())) {
            setDirection(dir);
            return;
        }

        initialiseMove(dir);
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }



    public boolean isOnTriggerTile() {
        return this.getRoom().isTriggerTile(this.tileCoordinates.x, this.tileCoordinates.y);

    }
    public int getPersonality()
    {
        return this.personalityLevel;
    }


    /**
     * This takes the player at its current position, and automatically gets the transition data for the next room and applies it to the player and game
     */
    public void moveRoom()
    {
        if (isOnTriggerTile()) {
            Room.Transition newRoomData = this.getRoom().getTransitionData(this.getTileCoordinates().x, this.getTileCoordinates().y);


            this.setRoom(newRoomData.getNewRoom());


            if (newRoomData.newDirection != null) {
                this.setDirection(newRoomData.newDirection);
                this.updateTextureRegion();
            }

            this.setTileCoordinates(newRoomData.newTileCoordinates.x, newRoomData.newTileCoordinates.y);

            //TODO: Look into making a getter for the players Game this way we can do this.getGame() here instead of GameMain.

            GameMain.me.navigationScreen.updateTiledMapRenderer();
        }
    }


    /**
     * Reads in the JSON file of tha character and stores dialogue in the dialogue HashMap
     *
     * @param fileName
     */
    private void importDialogue(String fileName)
    {
        //JSONParser parser = new JSONParser(); //needs to be included in project
        //Object obj = parser.parse(); //give it the file location
        //for (Item item: obj)
        //{
        //    dialogue.put(item[string],item[value]);
        //}
    } //this is the general idea - once the JSON thing is here will need to play with this a bit.

    /**
     * Gets a random item from the correct dictionary key clueName.
     *
     * @param clueName
     * @return
     */
    public String getSpeech(String clueName)
    {
        String[] responseList = dialogue.get(clueName);
        int rndm = new Random().nextInt(responseList.length);
        String returnValue = (responseList[rndm]);
        //String returnValue = responseList[0];
        return returnValue; //change to random
    }
}
