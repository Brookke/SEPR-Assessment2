package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * The status bar shown throughout the game
 * Contains UI controls for presenting the game status to the player
 */
public class StatusBar {

    public Stage stage;
    private Skin buttonSkin;


    /**
     * The initializer for the StatusBar
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    public StatusBar() {

        stage = new Stage();
        initSkins();

        HorizontalGroup statusBar = new HorizontalGroup();
        statusBar.setPosition(0,0);
        statusBar.setHeight(50);

        TextButton newGameButton = new TextButton("Score: 0", buttonSkin);
        statusBar.addActor(newGameButton);
        TextButton newGameButton2 = new TextButton("Personality Meter", buttonSkin);
        statusBar.addActor(newGameButton2);
        TextButton newGameButton3 = new TextButton("Inventory", buttonSkin);
        statusBar.addActor(newGameButton3);
        TextButton newGameButton4 = new TextButton("Pause", buttonSkin);
        statusBar.addActor(newGameButton4);

        stage.addActor(statusBar);
    }

    /**
     * Renders the status bar
     * Should be called within the render() method of a screen
     */
    public void render() {
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    /**
     * Sets up skin variables used for defining UI control styles
     */
    private void initSkins() {
        initButtonSkin();
    }

    /**
     * Sets up the skin for buttons on the status bar
     */
    private void initButtonSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth()/4, 50, Pixmap.Format.RGB888);
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
}
