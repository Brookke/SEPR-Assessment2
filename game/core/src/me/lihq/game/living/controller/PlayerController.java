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

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
        {
            if (player.getOffsetX() == 0 && player.getOffsetY() == 0)
            {
                player.moveLeft = true;
            }
        }

        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
        {
            if (player.getOffsetX() == 0 && player.getOffsetY() == 0)
            {
                player.moveRight = true;
            }
        }

        if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
        {
            if (player.getOffsetX() == 0 && player.getOffsetY() == 0)
            {
                player.moveUp = true;
            }
        }

        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
        {
            if (player.getOffsetX() == 0 && player.getOffsetY() == 0)
            {
                player.moveDown = true;
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
        {
            player.moveLeft = false;
        }

        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
        {
            player.moveRight = false;
        }

        if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
        {
            player.moveUp = false;
        }

        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
        {
            player.moveDown = false;
        }

        return false;
    }
}
