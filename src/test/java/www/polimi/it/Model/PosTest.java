package www.polimi.it.Model;


import org.junit.*;
import www.polimi.it.Exception.NegativeException;


public class PosTest{
    Pos pos;
    int i = 1;

    @Before
    public void constructor() throws NegativeException {
        pos = new Pos(0,0);
    }

    @Test(expected = NegativeException.class)
    public void testSetXNeg() throws NegativeException {
        pos.setX(-1);
    }

    @Test(expected = NegativeException.class)
    public void testSetYNeg() throws NegativeException {
        pos.setY(-1);
    }

    @Test
    public void testSetX() throws NegativeException {
        pos.setX(i);
        Assert.assertEquals(pos.getX(),new Integer(i));
    }

    @Test
    public void testSetY() throws NegativeException {
        pos.setY(i);
        Assert.assertEquals(pos.getY(),new Integer(i));
    }
}