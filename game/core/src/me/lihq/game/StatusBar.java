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
 * Created by jason on 11/12/2016.
 */
public class StatusBar {
    public Stage stage;
    private Skin skin;

    public StatusBar() {

        stage = new Stage();
        createBasicSkin();

        HorizontalGroup statusBar = new HorizontalGroup();
        statusBar.setPosition(0,0);
        statusBar.setHeight(50);

        TextButton newGameButton = new TextButton("Score: 0", skin);
        statusBar.addActor(newGameButton);
        TextButton newGameButton2 = new TextButton("Personality Meter", skin);
        statusBar.addActor(newGameButton2);
        TextButton newGameButton3 = new TextButton("Inventory", skin);
        statusBar.addActor(newGameButton3);
        TextButton newGameButton4 = new TextButton("Pause", skin);
        statusBar.addActor(newGameButton4);

        stage.addActor(statusBar);
    }

    public void render() {
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth()/4, 50, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.BLACK);
        textButtonStyle.checked = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

    }
}
