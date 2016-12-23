package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import me.lihq.game.living.NPC;
import me.lihq.game.living.Player;

/**
 * Created by Ben on 23/12/2016.
 */
public class Conversation {

    //Buttons array
    private Button[] questionButtons; //store the buttons like so? if anyone else decides otherwise ust change it - more a placeholder than anything else

    //Names
    private String playerName;
    private String npcName;

    public Conversation()
    {

    }

    /**
     * PLEASE CHECK THIS - MODIFY AS NECESSARY
     */
    public void setUpButtons(String[] buttons){ //buttons contains list of button names, max length 4
        if (buttons.length < 5){
            for (int i=0;i <5;i++)
            {
                questionButtons[i].setName(buttons[i]);
            }
            for (int i=buttons.length;i <5;i++) {
                questionButtons[i].setDisabled(true);
            }
        }
    }

    /**
     * ALSO CHECK THIS
     * Handles conversation between player and an NPC - method to call from this class
     * @param player
     * @param npc
     */
    public void startConversation(Player player, NPC npc) //this will be moved, just need to decide what goes where
    {
        //Assign values for ease
        String playerName = player.getPlayername();
        String npcName = npc.getName();

        //Introduction here
//        setPersonSpeech(npcName,"Placeholder");
        //wait for enter key - needs a 'speechbox controller'

        //now decide upon interaction
        //(AAAAAAH - this is gonna be a massive load of logic)
        int result = 1;
        switch (result)
        {
            case 1: result = 1;
                //go to question function
                break;
            case 2: result = 2;
                //go to accuse function
                break;
            case 3: result = 3;
                //go to ignore function
                break;
            case 4: result = 4;
                break;
        }
    }

//    /**
//     * set person
//     * @param inputPerson
//     */
//    public void setPersonSpeech(String inputPerson)
//    {
//        person = inputPerson;
//    }
//
//    /**
//     * set person and speech
//     * @param inputPerson
//     * @param inputSpeech
//     */
//    public void setPersonSpeech(String inputPerson,String inputSpeech)
//    {
//        person = inputPerson;
//        speech = inputSpeech;
//    }

}
