package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
    private OrthographicCamera camera = new OrthographicCamera(); //check which camera will be easiest

    public SpeechViewScreen(GameMain game)
    {
        super(game);

        menuController = new MenuController(game.menu); //need to change from 'playerController' to 'menuController'

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera.setToOrtho(false,w,h);
        camera.update();
        viewport = new FitViewport(w/ Settings.ZOOM, h/Settings.ZOOM, camera);
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(menuController);
    }

    @Override
    public void render(float delta){

    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
