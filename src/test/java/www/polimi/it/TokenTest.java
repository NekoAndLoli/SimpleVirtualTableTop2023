package www.polimi.it;

import org.junit.Test;
import www.polimi.it.Model.Token;
import www.polimi.it.Model.TokenImage;

import static org.junit.Assert.*;

public class TokenTest {
    @Test
    public void constructorCheck()
    {
        Token t = new Token(new TokenImage());
    }
}
