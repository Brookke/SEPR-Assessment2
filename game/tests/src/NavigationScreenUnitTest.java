import me.lihq.game.screen.NavigationScreen;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.RoomTag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vishal on 22/01/2017.
 */
public class NavigationScreenUnitTest {

    public NavigationScreen navigationTest;
    public GameMain game;

    @Before
    public void navigationScreenMaker(GameMain game){
        navigationTest= new NavigationScreen(game);
    }

    @Test
    public void testUpdateTiledMapRenderer(){
        navigationTest.updateTiledMapRenderer();
        assertEquals("changeMap is not being updated to true", true, navigationTest.getChangeMap());
        assertEquals("currentNPCS is not being updated or getNPCS is not working", game.getNPCS(game.player.getRoom()), navigationTest.getNPCs());
    }

    @Test
    public void testSetRoomTag(RoomTag tag){
        navigationTest.setRoomTag(new RoomTag("Testing setRoomTag"));
        assertEquals("roomTag is not getting updated", new RoomTag("Testing setRoomTag"), navigationTest.getRoomTag());
    }
}
