package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import me.lihq.game.living.NPC;
import me.lihq.game.living.Player;

import java.lang.reflect.Array;


/**
 * Created by Ben on 11/12/2016.
 */
public class SpeechBox {

    public Stage stage;

    //Properties
    private String person;
    private String speech;
    private Boolean playerQuestion = false; //Defines whether SpeechBox asks the player a question or displays a speech


    //Styles
    private Skin buttonSkin;
    private Skin textFieldSkin;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color BORDER_COLOUR = Color.GOLD;
    private static final Color TEXT_COLOUR = Color.LIGHT_GRAY;

    //Constants
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = 100;
    private static final int BORDER_WIDTH = 4;
    private static final int Y_OFFSET = StatusBar.HEIGHT;
    private static final int PADDING = 4;

    /**
     * The initializer for the SpeechBox
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    //Note: we need to discuss these properties
    public SpeechBox(String personName, String speechText, Boolean isQuestionForPlayer) {

        //Setup class properties
        person = personName;
        speech = speechText;
        playerQuestion = isQuestionForPlayer;

        //Init stage
        stage = new Stage();

        initSkins();

        //Init container
        Container container = new Container();
        container.setBounds(0, Y_OFFSET, WIDTH, HEIGHT);
        container.setBackground(getBackgroundDrawable(BORDER_COLOUR));

        //Init table containing contents of speech box
        Table table = new Table();
        table.defaults().width(250);
        table.setBackground(getBackgroundDrawable(BACKGROUND_COLOR));
        fillTableContent(table);

        //Add table to container contents, and add padding
        container.setActor(table);
        container.pad(BORDER_WIDTH);

        //Add container to stage for rendering later
        stage.addActor(container);
    }


    /**
     * Add relevant SpeechBox UI controls to table element
     * @param table Table to add controls to
     */
    private void fillTableContent(Table table) {

        if (playerQuestion == true) {

            TextField label = new TextField("What would you like to do?", textFieldSkin);
            table.add(label).pad(PADDING);

            int BUTTON_ROW_HEIGHT = (HEIGHT - (2 * BORDER_WIDTH)) / 2;
            table.row().height(BUTTON_ROW_HEIGHT);

            TextButton questionButton = new TextButton("Question", buttonSkin);
            table.add(questionButton).pad(PADDING);

            TextButton accuseButton = new TextButton("Accuse", buttonSkin);
            table.add(accuseButton).pad(PADDING);

        } else {

            table.row().width(WIDTH - 200).height(HEIGHT - 20);

            TextField personLabel = new TextField(person, textFieldSkin);
            table.add(personLabel).top().width(150);

            TextArea voiceLabel = new TextArea(speech, textFieldSkin); //what the person says
            table.add(voiceLabel).fill();

        }
        table.pack();
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
        initTextFieldSkin();
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
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonSkin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.BLACK);
        textButtonStyle.checked = buttonSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", textButtonStyle);

    }

    /**
     * Sets up the skin for buttons on the speech box
     */
    private void initTextFieldSkin() {
        textFieldSkin = new Skin();

        TextField.TextFieldStyle fontStyle = new TextField.TextFieldStyle();
        BitmapFont font = new BitmapFont();
        fontStyle.font = font;
        fontStyle.fontColor = TEXT_COLOUR;

        textFieldSkin.add("default", fontStyle);
    }

    //We should probs move this into a shared class
    /**
     * Returns drawable with single colour fill
     * @param color Colour to fill drawable with
     * @return Drawable to use with LibGdx Scene2d controls
     */
    private Drawable getBackgroundDrawable(Color color) {
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        return new Image(new Texture(pixmap)).getDrawable();
    }

    public void dispose() {
        stage.dispose();
    }

}
