package me.lihq.game.models;

import me.lihq.game.GameMain;

import java.util.ArrayList;
import java.util.List;

/**
 * The map is a collection of Rooms , it links them all together.
 *
 */
public class Map {

    List<Room> rooms = new ArrayList<Room>();

    public Map()
    {
        initialiseRooms();
    }

    /**
     * This function initialises all the rooms of the Ron Cooke Hub
     */
    public void initialiseRooms()
    {
        rooms.add(new Room(0, "map.tmx", "Main Foyer")
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2))
                    .addTransition(new Room.Transition().setFrom(1, 1).setTo(2, 0, 0)));

        rooms.add(new Room(1, "map.tmx", "RCH/037 Lecture Theatre")
                    .addTransition(new Room.Transition().setFrom(10, 10).setTo(2, 0, 0)));
    }

    /**
     * Move room .
     * @param currentRoomID - The Room ID of the current room.
     * @param currentX - The current x coordinate.
     * @param currentY - The current y coordinate.
     * @return return the information for the new room.
     */
    public Room.Transition moveRoom(int currentRoomID, int currentX, int currentY)
    {
        Room currentRoom = rooms.get(currentRoomID);

        Room.Transition newRoomData = currentRoom.getNewRoom(currentX, currentY);

        int newRoomID = newRoomData.newRoom;
        int newX = newRoomData.to.x;
        int newY = newRoomData.to.y;

        //Change current information and do map change transition
        return newRoomData;
    }

    /**
     * @param id - ID of room you want to find.
     * @return - returns the found room.
     */
    public Room getRoom(int id)
    {
        return rooms.get(id);
    }

}
