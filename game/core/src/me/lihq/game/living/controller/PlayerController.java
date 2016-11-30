package me.lihq.game.living.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import me.lihq.game.living.Player;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class PlayerController extends InputAdapter
{

    private Player player;

    public PlayerController(Player player)
    {
        this.player = player;
    }

    //could put in a class of its own for neatness?
    public boolean moveLeft = false;
    public boolean moveRight = false;
    public boolean moveUp = false;
    public boolean moveDown = false;

    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
            moveLeft = true;
            moveRight = false; //decide whether to remove or not (so only the most recently pressed key is acted upon)
            moveDown = false;
            moveUp = false;
        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
            moveRight = true;
            moveLeft = false;
            moveDown = false;
            moveUp = false;
        if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
            moveUp = true;
            moveLeft = false;
            moveRight = false;
            moveDown = false;
        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
            moveDown = true;
            moveLeft = false;
            moveRight = false;
            moveUp = false;
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
            moveLeft = false;
        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
            moveRight = false;
        if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
            moveUp = false;
        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
            moveDown = false;
        return false;
    }
}
