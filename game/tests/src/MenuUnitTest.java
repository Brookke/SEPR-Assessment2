import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.Menu;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vishal on 22/01/2017.
 */
public class MenuUnitTest {

    public Menu testMenu;
    public Menu testPauseMenu;

    @Test
    public void mainMenuTest(GameMain game){
        try{
            testMenu = new Menu(game,false);
        }
        catch(Exception e){
            System.out.println("Main Menu initialisation error"+e);
        }

    }
    @Test
    public void pauseMenuTest(GameMain game){
        try{
            testPauseMenu = new Menu(game,true);
        }
        catch(Exception e){
            System.out.println("Pause Menu initialisation error"+e);
        }

    }
}
