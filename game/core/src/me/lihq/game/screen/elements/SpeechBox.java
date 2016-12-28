package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;


/**
 * SpeechBox class
 * Used for rendering box containing text and buttons on screen
 *
 * Usage:
 *   SpeechBox sb = new SpeechBox(..)
 *   sb.render();
 *
 *   Note: add to InputMultiplexer if using with other UI elements.
 */
public class SpeechBox {

    public Stage stage;

    //Properties
    private String person;
    private String textContent;
    private ArrayList<SpeechBoxButton> buttons;

    //Styles
    private Skin buttonSkin;
    private Skin textFieldSkin;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color BORDER_COLOUR = Color.GOLD;
    private static final Color TEXT_COLOUR = Color.LIGHT_GRAY;

    //Layout Constants
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = 120;
    private static final int PADDING = 4;
    private static final int BORDER_WIDTH = 4;
    private static final int Y_OFFSET = StatusBar.HEIGHT;

    //Element Constants
    private static final int TABLE_WIDTH = WIDTH - (2 * BORDER_WIDTH);
    private static final int TABLE_HEIGHT = HEIGHT - (2 * BORDER_WIDTH);
    private static final int TEXT_ROW_HEIGHT = 60;
    private static final int BUTTON_ROW_HEIGHT = 40;


    /**
     * The constructor for the SpeechBox
     */
    public SpeechBox(String content, ArrayList<SpeechBoxButton> buttonList) {

        textContent = content;
        buttons = buttonList;

        setupStage();
    }

    /**
     * The constructor for the SpeechBox with personName
     */
    public SpeechBox(String personName, String speechText, ArrayList<SpeechBoxButton> buttonList) {

        person = personName;
        textContent = speechText;
        buttons = buttonList;

        setupStage();
    }

    /**
     * Sets up stage ready for rendering
     */
    private void setupStage() {
        //Init stage
        stage = new Stage();

        initSkins();

        //Init container
        Container container = new Container();
        container.setBounds(0, Y_OFFSET, WIDTH, HEIGHT);
        container.setBackground(UIHelpers.getBackgroundDrawable(BORDER_COLOUR, WIDTH, HEIGHT));

        //Init table containing contents of speech box
        Table table = new Table();
        table.setSize(TABLE_WIDTH, TABLE_HEIGHT);
        table.setBackground(UIHelpers.getBackgroundDrawable(BACKGROUND_COLOR, TABLE_WIDTH, TABLE_HEIGHT));
        fillTableContent(table);

        //Add table to container contents, and add padding
        container.pad(BORDER_WIDTH);
        container.setActor(table);

        //Add container to stage for rendering later
        stage.addActor(container);
    }


    /**
     * Add relevant SpeechBox UI controls to table element
     *
     * @param table Table to add controls to
     */
    private void fillTableContent(Table table) {

        //table.defaults().pad(PADDING);
        table.debugAll();

        int buttonCount = buttons.size();
        int buttonWidth = (TABLE_WIDTH / buttonCount) - PADDING;

        table.row().height(TEXT_ROW_HEIGHT);

        /*TextField personLabel = new TextField(person, textFieldSkin);
        table.add(personLabel);*/

        TextArea contentLabel = new TextArea(textContent, textFieldSkin);
        table.add(contentLabel).colspan(buttonCount);


        table.row().height(BUTTON_ROW_HEIGHT);

        //Iterate over buttons and render them to the screen
        for (int i = 0; i < buttonCount; i++) {
            final SpeechBoxButton buttonDetails = buttons.get(i); //find button in array

            TextButton buttonElement = new TextButton(buttonDetails.text, buttonSkin);

            //Setup button event handler
            buttonElement.addListener(new ClickListener() {
                @Override public void clicked(InputEvent event, float x, float y) {
                    buttonDetails.eventHandler.handleClick(buttonDetails.text);
                }
            });

            table.add(buttonElement).width(buttonWidth).pad(0, PADDING / 2, PADDING, PADDING / 2);
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
        buttonSkin.add("background", new Texture(pixmap));

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

    /**
     * Disposes of SpeechBox resources
     */
    public void dispose() {
        stage.dispose();
    }

}
