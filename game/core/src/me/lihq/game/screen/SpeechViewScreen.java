package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;


/**
 * Created by Ben on 05/12/2016.
 */
public class SpeechViewScreen extends AbstractScreen {

    private Viewport viewport;
    //add a new controller - maybe best to put this in the menu?
    private OrthographicCamera camera = new OrthographicCamera();
    private String PersonTalking = "";
    private SpriteBatch spriteBatch;
    private SpeechBoxScreen speechBox;

    public SpeechViewScreen(GameMain game)
    {
        super(game);

        //menuController = new MenuController(game.menu); //need to change from 'playerController' to 'menuController'

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera.setToOrtho(false,w,h);
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
        Gdx.input.setInputProcessor(null); //??
        speechBox = new SpeechBoxScreen(game);
    }


    @Override
    public void render(float delta){
        camera.position.x = 0; //0;//check (0,0) works ok
        camera.position.y = 0; //0;
        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        game.player.draw(spriteBatch); //move Player to the left and draw NPC
        //game.     how do i say 'draw THIS NPC?' how are the NPCs stored?
       spriteBatch.end();

        speechBox.render(delta); //er? help...
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
        spriteBatch.dispose();
    }
    @Override
    public void update()
    {

    }
}
