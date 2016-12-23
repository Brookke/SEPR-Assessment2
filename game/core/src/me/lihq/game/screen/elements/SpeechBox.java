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
 * Created by Ben on 11/12/2016.
 */
public class SpeechBox {

    public Stage stage;

    //Properties
    private String person;
    private String speech;
    private ArrayList<SpeechBoxButton> buttons;

    //Styles
    private Skin buttonSkin;
    private Skin textFieldSkin;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color BORDER_COLOUR = Color.GOLD;
    private static final Color TEXT_COLOUR = Color.LIGHT_GRAY;

    //Constants
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = 120;
    private static final int BORDER_WIDTH = 4;
    private static final int Y_OFFSET = StatusBar.HEIGHT;
    private static final int PADDING = 4;


    /**
     * The initializer for the SpeechBox
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    public SpeechBox(String personName, String speechText, ArrayList<SpeechBoxButton> buttonList) {

        //Setup class properties
        person = personName;
        speech = speechText;
        buttons = buttonList;

        //Init stage
        stage = new Stage();

        initSkins();

        //Init container
        Container container = new Container();
        container.setBounds(0, Y_OFFSET, WIDTH, HEIGHT);
        container.setBackground(UIHelpers.getBackgroundDrawable(BORDER_COLOUR, WIDTH, HEIGHT));

        //Init table containing contents of speech box
        Table table = new Table();
        table.setBackground(UIHelpers.getBackgroundDrawable(BACKGROUND_COLOR, WIDTH, HEIGHT));
        fillTableContent(table);

        //Add table to container contents, and add padding
        container.setActor(table);
        container.pad(BORDER_WIDTH);

        //Add container to stage for rendering later
        stage.addActor(container);
    }


    /**
     * Add relevant SpeechBox UI controls to table element
     *
     * @param table Table to add controls to
     */
    private void fillTableContent(Table table) {

        table.defaults().width(250).pad(PADDING);

        table.row();

        TextField personLabel = new TextField(person, textFieldSkin);
        table.add(personLabel);

        TextArea voiceLabel = new TextArea(speech, textFieldSkin);
        table.add(voiceLabel);


        table.row().height(50);

        //Iterate over buttons and render them to the screen
        for (int i = 0; i < buttons.size(); i++) {
            final SpeechBoxButton buttonDetails = buttons.get(i); //find button in array

            TextButton buttonElement = new TextButton(buttonDetails.text, buttonSkin);

            //Setup button event handler
            buttonElement.addListener(new ClickListener() {
                @Override public void clicked(InputEvent event, float x, float y) {
                    buttonDetails.eventHandler.handleClick(buttonDetails.text);
                }
            });

            table.add(buttonElement);
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

    public void dispose() {
        stage.dispose();
    }

}
