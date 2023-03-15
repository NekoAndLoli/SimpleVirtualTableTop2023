package www.polimi.it;

import org.junit.Test;
import www.polimi.it.Model.Token;

import static org.junit.Assert.*;

public class TokenTest {
    @Test
    public void constructorCheck()
    {
        Token t = new Token("first");
        assertNotNull(t);
        assertEquals(t.getUrl(), "first");
        t.setUrl("third");
        assertNotEquals(t.getUrl(),"first");
        assertEquals(t.getUrl(), "second");
    }
}
