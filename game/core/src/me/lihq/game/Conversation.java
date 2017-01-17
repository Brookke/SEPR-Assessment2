package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import me.lihq.game.living.NPC;
import me.lihq.game.living.Player;
import me.lihq.game.models.Clue;
import me.lihq.game.screen.elements.SpeechBox;
import me.lihq.game.screen.elements.SpeechBoxButton;

import java.util.ArrayList;
import java.util.List;

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

    private List<SpeechBox> stack = new ArrayList<>();

    //Globals for making EventHandling easier
    private String[] buttonNames;
    private int result = -1;

    public Conversation(Player inputPlayer, NPC inputNPC)
    {
        player = inputPlayer;
        npc = inputNPC;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------------------<<<<CHECK THIS
     * Handles conversation between player and an NPC - method to call from this class
     */

    public void addSpeechBox(SpeechBox speechBox) {
        stack.add(speechBox);
    }

    public void render()
    {
        if (!this.stack.isEmpty()) {
            this.stack.get(0).render();
        }
    }

    public void update()
    {
        if (!this.stack.isEmpty()) {
            if (this.stack.get(0).timeoutDuration == 0) {
                this.stack.remove(0);
            } else {
                this.stack.get(0).update();
            }
        }
    }

    public void startConversation() //this will be moved, just need to decide what goes where
    {

        //Introduction
        addSpeechBox(new SpeechBox("player",player.getSpeech("Introduction"),emptyButtons, 5)); //instead of placeholder use player.getdrivel() or whatever is the correct function
        addSpeechBox(new SpeechBox(npc.getName(),npc.getSpeech("Introduction"),emptyButtons, 5));

        //Deciding upon interaction
        setQuestionButtons("Question","Accuse","Ignore","");
        while (result < 0 || result > 2) //remove loop if wanted
        {
            speechBox = new SpeechBox("", "What do you want to do?", questionButtons, -1);
            switch (result) {
                case 0:
                    //go to question function
                    question();
                    break;
                case 1:
                    //go to accuse function
                    accuse();
                    break;
                case 2:
                    //go to ignore function
                    break;
            }
        }
    }

    private void question()
    {
        while (result < 0 || result > 3)
        {
            //decide item
            setQuestionButtons("Glasses","Lipstick","Handedness","Handbag"); //when done this should not be done in buttons, should be done from the journal/inventory class
            speechBox = new SpeechBox("","What do you want to question the character about",questionButtons, -1);

            String clueName = "";
            switch (result)
            {
                case 0:
                    clueName = "Glasses";
                    break;
                case 1:
                    clueName = "Lipstick";
                    break;
                case 2:
                    clueName = "Handedness";
                    break;
                case 3:
                    clueName = "Handbag";
                    break;
            }
            //decide how to question
            setQuestionButtons("","","","");
            speechBox = new SpeechBox("","How do you want to question the character",questionButtons, -1);
            if (result < 0 || result > 3)
            {
                questionNPC(clueName,result);
            }
        }
    }

    /**
     *
     * @param clue
     * @param severity
     */
    private void questionNPC(String clue, int severity)
    {
        speechBox = new SpeechBox("Player",player.getSpeech(clue+severity),emptyButtons, -1); //----how it should look.
        //should also do something to do with adding/taking away niceness from NPCs

        //speechBox = new SpeechBox(player.getPlayername(),"ITEM QUESTION PLACEHolder",questionButtons);
        //speechBox = new SpeechBox(npc.getName(),"NPC ITEM RESPONSE PLACEHOLDER",questionButtons);
    }

    private void accuse()
    {
        //no sure how this works - sort out later
    }

    /**
     * Sets the contents of questionButtons ArrayList. Set item to "" if it is not wanted
     * EventHandler uses two globals String[] buttonNames and int result
     * eventHandler0 sets result to 0,1,2 or 3 dependant on which button was clicked
     *
     * @param string0
     * @param string1
     * @param string2
     * @param string3
     */
    private void setQuestionButtons(String string0,String string1,String string2,String string3)
    {
        result = -1; //reset result
        buttonNames[0] = string0;
        buttonNames[1] = string1;
        buttonNames[2] = string2;
        buttonNames[3] = string3;
        SpeechBoxButton.EventHandler eventHandler0 = (int buttonResult)-> //if you guys can make this more elegant then say how/change it - this was the easiest way I could see.
        {
            result = buttonResult;
        };

        SpeechBoxButton button0 = new SpeechBoxButton(string0,0,eventHandler0); //remember to add '0',1,2 etc
        SpeechBoxButton button1 = new SpeechBoxButton(string1,1,eventHandler0);
        SpeechBoxButton button2 = new SpeechBoxButton(string2,2,eventHandler0);
        SpeechBoxButton button3 = new SpeechBoxButton(string3,3,eventHandler0);
        questionButtons.clear(); //remove all items from the list just in case
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
