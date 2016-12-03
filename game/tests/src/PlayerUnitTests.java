import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.living.Player;
import me.lihq.game.models.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeshuff on 22/11/2016.
 */
public class PlayerUnitTests extends GameTest
{
    Player p = null;
    Map map = null;

    @Before
    public void before()
    {
        p = new Player("Test Name", "player.png");
        map = new Map();
        p.setRoom(map.getRoom(0));
    }

    @Test
    public void testPlayername()
    {
        assertEquals("Fail - Not returning correct playername", p.getPlayername(), "Test Name");
    }

    @Test
    public void testPlayerPersonality()
    {
        //Personality Level is default 50
        p.addToPersonality(100);
        //Should have surpassed the maximum of 100. Then changed to the maximum, 100
        assertEquals("Fail - Personality not Upper Capped", p.getPersonality(), 100);

        p.addToPersonality(-50);
        //Reduce back to 50
        assertEquals("Fail - Personality not reduced to 50", p.getPersonality(), 50);

        p.addToPersonality(-100);
        //Gone below 0, should move it back up to 0
        assertEquals("Fail - Personality not Lower Capped", p.getPersonality(), 0);
    }


    /**
     * This tests if the move function is working correctly.
     * The player move function is in terms of tiles so an increase of 1 is actually 1 tile width.
     */
    @Test
    public void doesPlayerMove()
    {
        //As the player doesnt get moved until they have moved the entire tile, we may need to start the main thread to test this.
    }

}
