package me.lihq.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class Player extends Sprite {

    //The personality will be a percent score (0-100) 50 being neutral etc etc
    private int personalityLevel = 50;

    private Inventory inventory = new Inventory();

    private int score = 0;


    private Vector2 position;

    private String playername = "Joe";

    public Player(String name) {
        super(new Texture("player.png"));
        this.position = new Vector2();
        this.position.x = 0;
        this.position.y =0;


        this.setPosition(0, 0);
        this.playername = name;
        Settings.PLAYERNAME = name;
    }

    /*
        This method will change the players personality by the given amount.
        It will cap the personality between 0 and 100.

        If the change takes it out of these bounds, it will change it to the min or max.

        @param int change - The amount to change by, can be positive or negative
     */
    public void changePersonality(int change) {
        personalityLevel = personalityLevel + change;

        if (personalityLevel < 0) {
            personalityLevel = 0;
        } else if (personalityLevel > 100) {
            personalityLevel = 100;
        }
    }

    public void move(int dx, int dy) {
        this.position.x += dx;
        this.position.y += dy;

        this.setPosition(this.position.x*32,this.position.y*32);
    }


    public Inventory getInventory() {
        return this.inventory;
    }

    public String getPlayername() {
        return this.playername;
    }

    public int getPersonality() {
        return this.personalityLevel;
    }
}
