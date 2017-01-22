package me.lihq.game.screen.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.living.AbstractPerson;
import me.lihq.game.living.AbstractPerson.PersonPositionComparator;


import java.util.*;

/**
 * OrthogonalTiledMapRendererWithSprite
 * <p>
 * This class is an extension of the OrthogonalTiledMapRenderer that deals with
 * rendering sprites aswell. The last layer of the map is designed to be drawn OVER
 * the player sprite and NPCs. So this controls that by drawing each layer until it comes to the last
 * one, then it draws the sprites, then the final layer.
 */
public class OrthogonalTiledMapRendererWithSprite extends OrthogonalTiledMapRenderer
{

    public List<Sprite> sprites;

    public OrthogonalTiledMapRendererWithSprite(TiledMap map)
    {
        super(map);

        sprites = new ArrayList<Sprite>();
    }

    /**
     * This adds sprite to the list of sprites to be rendered before the final layer.
     *
     * @param sprite - Sprite to be added
     */
    public void addSprite(Sprite sprite)
    {
        sprites.add(sprite);
    }


    public void addSprites(List<Sprite> sprites)
    {
        this.sprites.addAll(sprites);
    }

    public void clearSprites()
    {
        sprites.clear();
    }

    /**
     * This overrides the render method in the super class.
     * <p>
     * It draws all the map layers until the final one. Then it draws all the sprites in the
     * sprite list, then it draws the final layer.
     */
    @Override
    public void render()
    {
        beginRender();

        /*
        Convert the sprites list into a list of AbstractPerson to be sorted
         */
        List<AbstractPerson> people = new ArrayList<AbstractPerson>();

        for (Sprite sprite : sprites)
        {
            people.add((AbstractPerson) sprite);
        }

        Collections.sort(people, new PersonPositionComparator());

        int amountOfLayers = map.getLayers().getCount();

        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            MapLayer layer = map.getLayers().get(currentLayer);

            if (layer.getName().equals("Blood") && !GameMain.me.player.getRoom().isMurderRoom())
            {
                //Don't draw the layer as its not the murder room
            }
            else
            {
                renderTileLayer((TiledMapTileLayer) layer);
            }

            if (currentLayer == amountOfLayers - 2 || amountOfLayers == 1) {

                for (Sprite s : people)
                {
                    s.draw(this.getBatch());
                }
            }
        }

        if (Settings.DEBUG)
        {
           DebugOverlay.renderDebugTiles(map, this.getBatch());
        }

        endRender();
    }
}
