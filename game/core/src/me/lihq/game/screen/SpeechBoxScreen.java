package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.screen.SpeechViewScreen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Ben on 11/12/2016.
 */
public class SpeechBoxScreen extends AbstractScreen
{

    private Viewport viewport;
    private OrthographicCamera camera = new OrthographicCamera();
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    public SpeechBoxScreen(GameMain game)
    {
        super(game);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera.setToOrtho(false,w,h);
        camera.update();
        viewport = new FitViewport(w, h, camera);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    public void render(float delta)
    {
        int padding = 5;//the padding to put around the textbox
        int textBoxHeight = 100;//the height of the text box

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        BitmapFont font = new BitmapFont();
        font.setColor(Color.CYAN);
        font.draw(spriteBatch, "Hello World!",  5, (-viewport.getWorldHeight()/4+20)); //work out how to place things properly //use worldheight/width
        spriteBatch.end();
        //draw textbox background
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(padding, 5, viewport.getWorldWidth()-2*padding,textBoxHeight);
        shapeRenderer.end();
        //draw textbox outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 1, 1);
        shapeRenderer.rect(padding, 5, viewport.getWorldWidth()-2*padding,textBoxHeight);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    private void draw_box()
    {

    }

    public void show()
    {

    }

    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }

    public void write()
    {

    }

    @Override
    public void update()
    {

    }
/***
    public String menu(String optionOne,String optionTwo,String optionThree, String optionFour)
    {
        if (optionOne.length() > 0)
        {
            //add one to write
            if (optionTwo.length() > 0)
            {
                //add two to write
                if (optionThree.length() > 0)
                {
                    //..
                    if (optionFour.length() > 0)
                    {
                        //..
                    }
                }
            }
        }
        return(selection);
    }
 */
}
