package me.lihq.game;

import com.badlogic.gdx.InputMultiplexer;
import me.lihq.game.living.NPC;
import me.lihq.game.living.Player;
import me.lihq.game.screen.elements.SpeechBox;
import me.lihq.game.screen.elements.SpeechBoxButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 23/12/2016.
 */
public class Conversation {


    //Persons
    public InputMultiplexer multiplexer;

    private Player player;
    private NPC npc;

    private List<SpeechBox> stack = new ArrayList<>();

    //Globals for making EventHandling easier
    private String[] buttonNames = new String[4];
    private int result = -1;

    //do i need this? ---------------------------------------------------------------------------------------------------------<<CHECK THIS
    public Conversation(Player inputPlayer)
    {
        multiplexer = new InputMultiplexer();
        player = inputPlayer;
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
                multiplexer.removeProcessor(stack.get(0).stage);
                this.stack.remove(0);
                multiplexer.addProcessor(stack.get(0).stage);
            } else {
                this.stack.get(0).update();
            }
        }
    }

    public void startConversation(NPC npc)
    {
        this.npc = npc;
        this.player.importDialogue("colin.JSON");
        this.npc.importDialogue("colin.JSON");
        //Introduction
        addSpeechBox(new SpeechBox(this.player.getName(), this.player.getSpeech("Introduction"), 5)); //instead of placeholder use player.getdrivel() or whatever is the correct function
        addSpeechBox(new SpeechBox(this.npc.getName(), this.npc.getSpeech("Introduction"), 5));


        queryQuestionType();
        //addSpeechBox(new SpeechBox("What do you want to ask about?", ));


    }
    private void queryQuestionType() {

        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.TYPE, result);

        buttons.add(new SpeechBoxButton("Question?",0, eventHandler));
        buttons.add(new SpeechBoxButton("Accuse?",1, eventHandler));
        stack.add(new SpeechBox("What do you want to do?", buttons,-1));

    }


    private void queryQuestionStyle() {
        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.STYLE, result);

        buttons.add(new SpeechBoxButton("Nice",0, eventHandler));
        buttons.add(new SpeechBoxButton("Neutral",1, eventHandler));
        buttons.add(new SpeechBoxButton("Harsh",2, eventHandler));
        stack.add(new SpeechBox("How do you want to ask the question?", buttons,-1));

    }

    private void queryWhichClue(int style) {
        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.CLUE, result);


        //for (player)

        buttons.add(new SpeechBoxButton("",0, eventHandler));
        buttons.add(new SpeechBoxButton("Neutral",1, eventHandler));
        buttons.add(new SpeechBoxButton("Harsh",1, eventHandler));
        stack.add(new SpeechBox("How do you want to ask the question?", buttons,-1));
    }

    private void handleResponse(QuestionStage stage, int option) {
        switch (stage) {
            case TYPE:
                if (option == 0) {
                    queryQuestionStyle();
                } else if (option == 1) {
                    //accuseNPC();
                }
                break;

            case STYLE:
                //queryWhichClue(option)
                break;

            case CLUE:
                //questionNPC(option);
                break;




        }


    }

    public enum QuestionStage
    {

        TYPE,
        STYLE,
        CLUE


    }
}
