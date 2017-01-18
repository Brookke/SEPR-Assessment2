import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.lihq.game.Assets;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Inventory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeshuff on 23/11/2016.
 */
public class InventoryUnitTests extends GameTester
{
    Inventory inv;
    Clue book;
    Clue light;

    @Before
    public void before()
    {
        //TODO: stop using arrow as test assets
        Assets.load();
        book = new Clue("Book", "This is a book", Assets.getArrowDirection("NORTH"));
        light = new Clue("Light", "This is a light", Assets.getArrowDirection("NORTH"));
        inv = new Inventory();
        inv.addClue(book);
    }

    @Test
    public void testHasClue()
    {

        assertEquals("Fail - Cannot Find Inventory clue by object", true, inv.hasClue(book));
        assertEquals("Fail - Cannot Find Inventory clue by Name", true, inv.hasClue("Book"));

        assertEquals("Fail - Providing false positives", false, inv.hasClue(light));
        assertEquals("Fail - Providing false positives", false, inv.hasClue("light"));


    }

    @Test
    public void addClue()
    {
        Clue footPrint = new Clue("Foot Print", "This is a foot print", Assets.getArrowDirection("NORTH"));
        inv.addClue(footPrint);

        assertEquals("Fail - Cannot Find Inventory clue by object", true, inv.hasClue(footPrint));
        assertEquals("Fail - Cannot Find Inventory clue by Name", true, inv.hasClue("Foot Print"));
    }

}
