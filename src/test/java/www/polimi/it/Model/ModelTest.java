package www.polimi.it.Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import www.polimi.it.Exception.*;

import java.net.URI;
import java.net.URISyntaxException;

public class ModelTest extends TestCase {

    Model model;

    @BeforeEach
    public void init(){
        model = new Model("dm");
    }

    @Test
    public void testAddToken() {
        try {
            Pos pos = new Pos(0,1);
            model.addToken(new URI("uri"),pos);
        } catch (NegativeException | URISyntaxException | PosNotFreeException | PosOutOfBoundException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void testChangeMap() {
        try {
            URI uri = new URI("uri");
            model.changeMap(new URI("uri"));
            assertEquals(model.getBackGround().getUri(),uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void testMoveToken() {
        try {
            Pos pos = new Pos(0,1);
            Pos end = new Pos(1,1);
            model.addPlayer("player",null);
            model.addToken(new URI("uri"),pos);
            model.moveToken(pos,end,"player");
        } catch (NegativeException | URISyntaxException | PosNotFreeException | PosOutOfBoundException | NoTokenException | NotYourTokenException | NoPlayerException | PlayerOnlineException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void testAddPlayer() {
        try {
            model.addPlayer("player",null);
            Assertions.assertThrows(PlayerOnlineException.class,()->model.addPlayer("player",null));
        } catch (PlayerOnlineException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void testSetGrid() {
        try {
            model.setGrid(5,5);
        } catch (NegativeException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }
}