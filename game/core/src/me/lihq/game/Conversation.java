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
    private ArrayList<SpeechBoxButton> questionButtons; //store the buttons like so? if anyone else decides otherwise just change it - more a placeholder than anything else
    private ArrayList<SpeechBoxButton> emptyButtons;//empty list of buttons

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
        int result = 0;
        //ArrayList<SpeechBoxButton> buttonList = new ArrayList<SpeechBoxButton>();
        //Introduction here
        speechBox = new SpeechBox(player.getPlayername(),player.getSpeech("Introduction"),emptyButtons); //instead of placeholder use player.getdrivel() or whatever is the correct function
        speechBox = new SpeechBox(npc.getName(),npc.getSpeech("Introduction"),emptyButtons);

        //now decide upon interaction
        //(AAAAAAH - this is gonna be a massive load of logic)
        setQuestionButtons("Question","Accuse","Ignore","");
        speechBox = new SpeechBox("","What do you want to do?",questionButtons);
        result = 1;
        switch(result)
        {
            case 1: result = 1;
                //go to question function
                question();
                break;
            case 2: result = 2;
                //go to accuse function
                accuse();
                break;
            case 3: result = 3;
                //go to ignore function
                break;
        }//does all this want to be in a loop?
    }

    private void question()
    {
        int result = 1;
        while (result != 4)
        {
            speechBox = new SpeechBox("","What do you want to question the player about",questionButtons);

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

    private void accuse()
    {
        //no sure how this works - sort out later
    }

    /**
     * sets the contents of questionButtons ArrayList. Set item to "" if it is not wanted
     *
     * @param string0
     * @param string1
     * @param string2
     * @param string3
     */
    private void setQuestionButtons(String string0,String string1,String string2,String string3)
    {
        SpeechBoxButton.EventHandler eventHandler0 = new SpeechBoxButton.EventHandler() {
            @Override
            public void handleClick(String buttonText) {
                //result = 1;
            }
        };
        SpeechBoxButton.EventHandler eventHandler1 = new SpeechBoxButton.EventHandler() {
            @Override
            public void handleClick(String buttonText) {

            }
        };
        SpeechBoxButton.EventHandler eventHandler2 = new SpeechBoxButton.EventHandler() {
            @Override
            public void handleClick(String buttonText) {

            }
        };
        SpeechBoxButton.EventHandler eventHandler3 = new SpeechBoxButton.EventHandler() {
            @Override
            public void handleClick(String buttonText) {

            }
        };
        SpeechBoxButton button0 = new SpeechBoxButton(string0,eventHandler0);
        SpeechBoxButton button1 = new SpeechBoxButton(string1,eventHandler1);
        SpeechBoxButton button2 = new SpeechBoxButton(string2,eventHandler2);
        SpeechBoxButton button3 = new SpeechBoxButton(string3,eventHandler3);
        questionButtons.clear();
        if (string0.length()>0)
        {
            questionButtons.add(0, button0);
        }
        if (string1.length()>0)
        {
            questionButtons.add(1, button1);
        }
        if (string2.length()>0)
        {
            questionButtons.add(2, button2);
        }
        if (string3.length()>0)
        {
            questionButtons.add(3, button3);
        }
    }
}
