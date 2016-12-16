package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import me.lihq.game.GameMain;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import me.lihq.game.screen.AbstractScreen;


/**
 * Created by Ben on 11/12/2016.
 */
public class SpeechBox extends AbstractScreen
{

    private ShapeRenderer renderer = new ShapeRenderer();
    public Stage stage;
    private Group group;
    private Skin buttonSkin;
    private TextField.TextFieldStyle fontStyle;

    private String personTalking = "TESTPERSON";//the person talking
    private String voiceTalking = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";//what the person says
    private Boolean playerQuestion;

    private Table speechBoxTable;
    private ShapeRenderer shapeRenderer;
    private int padding = 15;//the padding to put around the textbox
    private int innerPadding = 10;//padding inside the text box
    private int lowerBarHeight = 100+padding;//height of the bar thing at the bottom + padding
    private int textBoxHeight = 100;//the height of the text box
    private int textBoxWidth = 1;//will be reassigned upon creation of textbox
    private Color textBoxOutline = Color.GOLD;//textbox outline colour
    private Color textBoxTextColour = Color.LIGHT_GRAY;//text colour
    private int roundRadius = 15;

    public SpeechBox(GameMain game)
    {
        super(game);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        stage = new Stage();
        initSkins();
        textBoxWidth = Math.round(w-2*padding-2*innerPadding);
        Table speechBoxTable = new Table();
        speechBoxTable.setPosition(padding+innerPadding,lowerBarHeight);
        speechBoxTable.setHeight(textBoxHeight);
        speechBoxTable.setWidth(textBoxWidth);

        //Pixmap backgroundMap = new Pixmap(textBoxWidth,textBoxHeight+innerPadding, Pixmap.Format.RGB888); //try to use this to affect the back of the text box?
        //backgroundMap.setColor(Color.CLEAR);
        //Texture backgroundTexture = new Texture(backgroundMap);
        //Sprite backgroundSprite = new Sprite(backgroundTexture);
        //SpriteDrawable textBoxBackground = new SpriteDrawable();
        //textBoxBackground.setSprite(backgroundSprite);
        //speechBoxTable.setBackground(textBoxBackground);

        playerQuestion = false; //decide how to implement this properly
        if (playerQuestion == true)
        {
            TextButton questionButton = new TextButton("Question", buttonSkin);
            speechBoxTable.addActor(questionButton);
            TextButton accuseButton = new TextButton("Accuse", buttonSkin);
            speechBoxTable.addActor(accuseButton);
            //TextButton ignoreButton = new TextButton("Ignore",buttonSkin); //for the next group to just un-comment - you get the gist from above
            questionButton.setPosition(innerPadding, textBoxHeight /2+innerPadding);
            accuseButton.setPosition(innerPadding, innerPadding); //use "textBoxWidth/2" to get the button on the right half
            stage.addActor(speechBoxTable);
        }
        else
        {
            TextField.TextFieldStyle fontStyle = new TextField.TextFieldStyle();
            BitmapFont font = new BitmapFont();
            fontStyle.font = font;
            fontStyle.fontColor = textBoxTextColour;
            TextField person = new TextField(personTalking, fontStyle);
            speechBoxTable.addActor(person);
            TextArea voice = new TextArea(voiceTalking,fontStyle); //what the person says
            speechBoxTable.addActor(voice);
            person.setPosition(innerPadding,textBoxHeight-10);
            voice.setPosition(person.getWidth()+innerPadding,0);
            voice.setWidth(textBoxWidth-person.getWidth()-innerPadding*2);
            voice.setHeight(textBoxHeight);
            stage.addActor(speechBoxTable);
        }
        speechBoxTable.pack();
    }

    private void initSkins()
    {
        initButtonSkin();
    }

    private void initButtonSkin()
    {
        //Create a font
        BitmapFont font = new BitmapFont();
        font.setColor(textBoxTextColour);
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth()/2-padding*2, textBoxHeight/2-innerPadding, Pixmap.Format.RGB888); //may need to edit
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonSkin.add("background",new Texture(pixmap));

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

    public void render(float delta)
    {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(textBoxOutline);
        shapeRenderer.rect(padding+roundRadius, lowerBarHeight+padding, textBoxWidth+padding*2-2*roundRadius, textBoxHeight+padding*2); //doesnt follow the resizing
        shapeRenderer.rect(padding,lowerBarHeight+padding+roundRadius,textBoxWidth+padding*2,textBoxHeight+padding*2-roundRadius*2);
        shapeRenderer.circle(padding+roundRadius,lowerBarHeight+padding+roundRadius,roundRadius);
        //shapeRenderer.circle(padding+textBoxWidth,lowerBarHeight,roundRadius);
        //shapeRenderer.circle(padding,lowerBarHeight+textBoxHeight,roundRadius);
        //shapeRenderer.circle(padding+textBoxWidth,lowerBarHeight+textBoxHeight,roundRadius);

        shapeRenderer.end();
        stage.act();
        stage.draw();
    }

    public void setPersonVoice(String person,String voice,Boolean askQuestion) //use to set who is talking and what they are saying
    {
        if (askQuestion = false){
            personTalking = person+" :";
            voiceTalking = voice; //might need to check length for so it doesnt go out of the text box
        }
        else
        {
            playerQuestion = true; //to set playerQuestion just use 'setPersonVoice("","",true);
        }
        update();
    }

    @Override
    public void resize(int width, int height)
    {
        //viewport.update(width, height); //find equivalent... er?
        setPersonVoice("...","hello",false);
        //shapeRenderer.setProjectionMatrix();
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void show()
    {
        //help!!!
    }

    @Override
    public void hide()
    {
        dispose();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }

    @Override
    public void update()
    {

    }
}
