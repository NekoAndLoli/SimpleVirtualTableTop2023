package www.polimi.it.Model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import www.polimi.it.Exception.*;

import java.net.URI;
import java.net.URISyntaxException;

public class GridTest extends TestCase {

    Grid grid = new Grid();

    @BeforeEach
    public void initGrid(){
        grid = new Grid();
    }

    public void testSetRows() throws NegativeException {
            Assertions.assertThrows(NegativeException.class ,()->{grid.setRows(-1);});
            grid.setRows(2);
            assertEquals(2,grid.getRows().intValue());
    }

    public void testSetColumns() throws NegativeException {

        Assertions.assertThrows(NegativeException.class ,()->{grid.setColumns(-1);});
        grid.setColumns(2);
        assertEquals(2,grid.getColumns().intValue());
    }

    public void testSetGrid() throws NegativeException {
        Assertions.assertThrows(NegativeException.class ,()->{grid.setGrid(-1,1);});
        grid.setGrid(2,2);
        assertEquals(2,grid.getColumns().intValue());
        assertEquals(2,grid.getRows().intValue());

    }

    public void testMoveToken() {
        //TODO
        try {
            Pos pos = new Pos(0, 0);
            TokenImage image = new TokenImage(new URI("uri"), "", false);
            grid.addToken(pos, image);
            assertEquals(image, grid.getTokenImage(pos));
            Pos pos2 = new Pos(1,1);
            Pos posOut = new Pos(1,15);
            //move the cell to itself
            Assertions.assertDoesNotThrow(()->grid.moveToken(pos2,pos2,"gianni"));
            //move an empty cell
            Assertions.assertThrows(NoTokenException.class,()->grid.moveToken(pos2,pos,""));
            //move to out of bound
            Assertions.assertThrows(PosOutOfBoundException.class,()->grid.moveToken(pos,posOut,""));

            //move a proper token
            grid.moveToken(pos,pos2,"");
            assertEquals(image, grid.getTokenImage(pos2));
            //create a new token in 0 0 and move it back
            grid.addToken(pos,image);
            Assertions.assertThrows(PosNotFreeException.class,()->grid.moveToken(pos,pos2,"gianni"));

        }catch (PosNotFreeException | NegativeException | URISyntaxException | NotYourTokenException | NoTokenException | PosOutOfBoundException e){
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testAddToken() {
        try {
            Pos pos = new Pos(0, 0);
            TokenImage image = new TokenImage(new URI("uri"),"",false);
            grid.addToken(pos,image);
            assertEquals(image,grid.getTokenImage(pos));
            Pos finalPos = pos;
            Assertions.assertThrows(PosNotFreeException.class,()->grid.addToken(finalPos,image));
        }catch (NegativeException | URISyntaxException | PosNotFreeException e){
            e.printStackTrace();
            assertEquals(0,1);
        }

    }

    public void testRemoveToken() {
        try {
            Pos pos = new Pos(0, 0);
            Assertions.assertThrows(NoTokenException.class,()->grid.removeToken(pos)) ;
            TokenImage image = new TokenImage(new URI("uri"),"",false);
            grid.addToken(pos,image);
            assertEquals(image,grid.getTokenImage(pos));
            grid.removeToken(pos);
            assertNull(grid.getToken(pos));
            Pos pos2 = new Pos(20, 0);
            Assertions.assertThrows(PosOutOfBoundException.class,()->grid.removeToken(pos2)) ;

        }catch (PosNotFreeException | NegativeException | URISyntaxException | PosOutOfBoundException | NoTokenException e){
            e.printStackTrace();
            assertTrue(false);
        }
    }
}