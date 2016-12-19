package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * Created by Ben on 11/12/2016.
 */
public class SpeechBox {

    public Stage stage;

    //Properties
    private String personTalking = "TESTPERSON";//the person talking
    private String voiceTalking = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";//what the person says
    private Boolean playerQuestion;


    //Styles
    private Skin buttonSkin;
    private static final Color BORDER_COLOUR = Color.GOLD;
    private static final Color TEXT_COLOUR = Color.LIGHT_GRAY;

    //Constants
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = 90;
    private static final int PADDING = 10;
    private static final int Y_OFFSET = StatusBar.HEIGHT;


    /**
     * The initializer for the SpeechBox
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    public SpeechBox() {

        stage = new Stage();

        initSkins();


        //Init container
        Container container = new Container();
        container.setBounds(0, Y_OFFSET, WIDTH, HEIGHT);
        container.setBackground(getBackgroundDrawable(BORDER_COLOUR));


        //Init table containing contents of speech box
        Table table = new Table();
        container.setBackground(getBackgroundDrawable(Color.BLACK));


        //Add table to container contents, and add padding
        container.setActor(table);
        container.pad(PADDING);

        //Add container to stage for rendering later
        stage.addActor(container);


        /*
        playerQuestion = false; //decide how to implement this properly
        if (playerQuestion == true) {
            TextButton questionButton = new TextButton("Question", buttonSkin);
            speechBoxTable.addActor(questionButton);
            TextButton accuseButton = new TextButton("Accuse", buttonSkin);
            speechBoxTable.addActor(accuseButton);
            //TextButton ignoreButton = new TextButton("Ignore",buttonSkin); //for the next group to just un-comment - you get the gist from above
            questionButton.setPosition(PADDING, HEIGHT / 2 + PADDING);
            accuseButton.setPosition(PADDING, PADDING); //use "textBoxWidth/2" to get the button on the right half
            stage.addActor(speechBoxTable);
        } else {
            TextField.TextFieldStyle fontStyle = new TextField.TextFieldStyle();
            BitmapFont font = new BitmapFont();
            fontStyle.font = font;
            fontStyle.fontColor = textBoxTextColour;
            TextField person = new TextField(personTalking, fontStyle);
            speechBoxTable.addActor(person);
            person.setPosition(PADDING, Y_OFFSET + HEIGHT - PADDING);

            TextArea voice = new TextArea(voiceTalking, fontStyle); //what the person says
            speechBoxTable.addActor(voice);
            voice.setPosition(person.getWidth() + PADDING, Y_OFFSET + HEIGHT - PADDING);
        }
        speechBoxTable.pack();
        stage.addActor(speechBoxTable); */
    }

    /**
     * Renders the speech box
     * Should be called within the render() method of a screen
     */
    public void render() {
        stage.act();
        stage.draw();
    }

    /**
     * Sets up skin variables used for defining UI control styles
     */
    private void initSkins() {
        initButtonSkin();
    }

    /**
     * Sets up the skin for buttons on the speech box
     */
    private void initButtonSkin() {
        //Create a font
        BitmapFont font = new BitmapFont();
        font.setColor(TEXT_COLOUR);
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGB888); //may need to edit
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = buttonSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.over = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        textButtonStyle.font.setColor(Color.BLUE);//why doesn't this work?
        buttonSkin.add("default", textButtonStyle);

    }


    //We should probs move this into a shared class
    /** Returns drawable with single colour fill
     * @param color Colour to fill drawable with
     * @return Drawable to use with LibGdx Scene2d controls
     */
    private Drawable getBackgroundDrawable(Color color) {
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        return new Image(new Texture(pixmap)).getDrawable();
    }


    public void setPersonVoice(String person, String voice, Boolean askQuestion) //use to set who is talking and what they are saying
    {
        if (!askQuestion) {
            personTalking = person + " :";
            voiceTalking = voice; //might need to check length for so it doesnt go out of the text box
        } else {
            playerQuestion = true; //to set playerQuestion just use 'setPersonVoice("","",true);
        }
    }

    public void dispose() {
        stage.dispose();
    }
}
