package me.lihq.game.living.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import me.lihq.game.living.AbstractPerson;
import me.lihq.game.living.AbstractPerson.DIRECTION;
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
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
        {
            if (player.getOffsetX() == 0 && player.getOffsetY() == 0)
            {
                player.setMoveDirection(DIRECTION.WEST);
            }
        }
        else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
        {
            if (player.getOffsetX() == 0 && player.getOffsetY() == 0)
            {
                player.setMoveDirection(DIRECTION.EAST);
            }
        }
        else if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
        {
            if (player.getOffsetX() == 0 && player.getOffsetY() == 0)
            {
                player.setMoveDirection(DIRECTION.NORTH);
            }
        }
        else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
        {
            if (player.getOffsetX() == 0 && player.getOffsetY() == 0)
            {
                player.setMoveDirection(DIRECTION.SOUTH);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            moveLeft = false;
        }
        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
            moveRight = false;}
        if (keycode == Input.Keys.UP || keycode == Input.Keys.W){
            moveUp = false;}
        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
            moveDown = false;}
        return false;
    }
}
