package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import me.lihq.game.living.NPC;
import me.lihq.game.living.Player;
import me.lihq.game.models.Clue;
import me.lihq.game.screen.elements.SpeechBox;
import me.lihq.game.screen.elements.SpeechBoxButton;

import java.util.ArrayList;

/**
 * Created by Ben on 23/12/2016.
 */
public class Conversation {

    //Buttons array
    private ArrayList<SpeechBoxButton> questionButtons; //store the buttons like so? if anyone else decides otherwise ust change it - more a placeholder than anything else

    //Persons
    private Player player;
    private NPC npc;
    private SpeechBox speechBox;

    public Conversation(Player inputPlayer, NPC inputNPC)
    {
        player = inputPlayer;
        npc = inputNPC;
    }

    /**
     * ALSO CHECK THIS
     * Handles conversation between player and an NPC - method to call from this class
     */
    public void startConversation() //this will be moved, just need to decide what goes where
    {
        //ArrayList<SpeechBoxButton> buttonList = new ArrayList<SpeechBoxButton>();

        //Introduction here
        //buttonList = ["ADD BUTTONS HERE"];
        speechBox = new SpeechBox(player.getPlayername(),player.getSpeech("Introduction"),questionButtons); //instead of placeholder use player.getdrivel() or whatever is the correct function
        speechBox = new SpeechBox(npc.getName(),npc.getSpeech("Introduction"),questionButtons);

        //wait for enter key - needs a 'speechbox controller' - -OR-- how to do timer?

        //now decide upon interaction
        //(AAAAAAH - this is gonna be a massive load of logic)
        int result = 1;
        switch(result)
        {
            case 1: result = 1;
                //go to question function
                question(speechBox);
                break;
            case 2: result = 2;
                //go to accuse function
                accuse(speechBox);
                break;
            case 3: result = 3;
                //go to ignore function
                break;
        }//does all this want to be in a loop?
    }

    private void question(SpeechBox speechBox)
    {
        int result = 1;
        while (result != 4)
        {
            speechBox = new SpeechBox("","What do you want to question theplayer about",questionButtons);

            //Clue item = new Clue(); not sure how to do this...
            //item = return from inventory...

            //prints questions
            switch(result) {
                case 1:
                    result = 1;
                    //ask kind question
                    speechBox = new SpeechBox(player.getPlayername(),"ITEM QUESTION PLACEHolder",questionButtons);
                    speechBox = new SpeechBox(npc.getName(),"NPC ITEM RESPONSE PLACEHOLDER",questionButtons);
                    //npc.addkindness
                    break;
                case 2:
                    result = 2;
                    //ask normal question
                    //these work as ABOVE -----edit
                    break;
                case 3:
                    result = 3;
                    //ask harsh question
                    break;
                case 4:
                    result = 4;
                    return;
            }
        }
    }

    private void accuse(SpeechBox speechBox)
    {
        //no sure how this works - sort out later
    }
}
