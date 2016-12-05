package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.living.controller.PlayerController;


/**
 * Created by Ben on 05/12/2016.
 */
public class SpeechViewScreen extends AbstractScreen {

    private Viewport viewport;
    private PlayerController playerController;
    private PerspectiveCamera camera = new PerspectiveCamera(); //check which camera will be easiest
    private String PersonTalking = "";
    private SpriteBatch spriteBatch;

    public SpeechViewScreen(GameMain game)
    {
        super(game);

        //menuController = new MenuController(game.menu); //need to change from 'playerController' to 'menuController'

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera.update();
        viewport = new FitViewport(w/ Settings.ZOOM, h/Settings.ZOOM, camera);

        spriteBatch = new SpriteBatch();
    }

    public void setPersonTalking(String NPCTalking) //must call this func so that the name and picture are correct.
    {
        PersonTalking = NPCTalking;
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render(float delta){
        camera.position.x = 0;//check (0,0) works ok
        camera.position.y = 0;

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        game.player.draw(spriteBatch);
        spriteBatch.end();
        //place image 1 and image 2
        //place text box etc - needs a new class? - would like to discuss with someone at some point
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void pause() { //does the speech view need pause?

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //just 'dispose' everything

    }
}
