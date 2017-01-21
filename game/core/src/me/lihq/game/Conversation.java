package me.lihq.game;

import com.badlogic.gdx.InputMultiplexer;
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


    //Persons
    public InputMultiplexer multiplexer;

    private Player player;

    /**
     * Stores the current NPC that is being questioned
     */
    private NPC tempNPC;

    private List<SpeechBox> stack = new ArrayList<>();

    /**
     * This stores the position of the clue in the players list for use in the questioning
     */
    private int tempCluePos;

    /**
     * This stores the style of questioning for how the player wants to ask the question
     * 0 = Nice
     * 1 = Neutral
     * 2 = Harsh
     */
    private int tempQuestionStyle;


    public Conversation(Player inputPlayer)
    {
        multiplexer = new InputMultiplexer();
        player = inputPlayer;
    }


    public void addSpeechBox(SpeechBox speechBox) {
        this.stack.add(speechBox);


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
                this.multiplexer.removeProcessor(this.stack.get(0).stage);
                this.stack.remove(0);
            } else {
                this.stack.get(0).update();

            }
        }
        updateInputProcessor();
    }

    private void updateInputProcessor() {
        if (this.multiplexer.getProcessors().size == 0 && !this.stack.isEmpty()) {
            this.multiplexer.addProcessor(this.stack.get(0).stage);
        }
    }


    public void startConversation(NPC npc)
    {
        //reset temp stores
        this.tempCluePos = -1;
        this.tempQuestionStyle = -1;
        this.tempNPC = npc;

        //TODO: move this to the abstractPerson construct
        this.player.importDialogue("colin.JSON");
        this.tempNPC.importDialogue("colin.JSON");
        //Introduction
        addSpeechBox(new SpeechBox(this.player.getName(), this.player.getSpeech("Introduction"), 5)); //instead of placeholder use player.getdrivel() or whatever is the correct function
        addSpeechBox(new SpeechBox(this.tempNPC.getName(), this.tempNPC.getSpeech("Introduction"), 5));


        queryQuestionType();
        //addSpeechBox(new SpeechBox("What do you want to ask about?", ));


    }

    /**
     * This constructs the speech box that finds out what question the player wishes to ask the NPC
     */
    private void queryQuestionType() {

        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.TYPE, result);

        buttons.add(new SpeechBoxButton("Question?",0, eventHandler));
        buttons.add(new SpeechBoxButton("Accuse?",1, eventHandler));
        addSpeechBox(new SpeechBox("What do you want to do?", buttons,-1));

    }


    /**
     * This constructs the speechbox that asks the player how they wish to ask the question
     */
    private void queryQuestionStyle() {
        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.STYLE, result);

        buttons.add(new SpeechBoxButton("Nice",0, eventHandler));
        buttons.add(new SpeechBoxButton("Neutral",1, eventHandler));
        buttons.add(new SpeechBoxButton("Harsh",2, eventHandler));
        addSpeechBox(new SpeechBox("How do you want to ask the question?", buttons,-1));

    }

    /**
     * This constructs the speechbox that asks the player what clue they wish to ask about
     */
    private void queryWhichClue() {
        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> {
            handleResponse(QuestionStage.CLUE, result);
        };

        //TODO: finish when clues have been merged
//        for (Clue c: this.player.clues) {
//            buttons.add(new SpeechBoxButton(clue.getName(), );
//        }

        addSpeechBox(new SpeechBox("What clue doe you want to ask about?", buttons,-1));
    }

    private void questionNPC() {
        //TODO: finish when clues have been merged
    }

    private void handleResponse(QuestionStage stage, int option) {
        switch (stage) {
            case TYPE:
                this.stack.get(0).timeoutDuration = 0;
                if (option == 0) {
                    queryQuestionStyle();
                } else if (option == 1) {
                    //accuseNPC();
                }
                break;

            case STYLE:
                this.stack.get(0).timeoutDuration = 0;
                this.tempQuestionStyle = option;
                queryWhichClue();
                break;

            case CLUE:
                this.stack.get(0).timeoutDuration = 0;
                this.tempCluePos = option;
                //questionNPC();
                break;




        }


    }

    public enum QuestionStage
    {

        /**
         * This stage indicates that the player has been asked what type of question they have asked
         * e.g. question or accuse
         */
        TYPE,

        /**
         * Thus stage means that the player has been asked the how they want to ask the question
         * e.g. nice, neutral or harsh
         */
        STYLE,

        /**
         * This stage indicates that the player has been asked what clue they want to ask about.
         */
        CLUE


    }

}
