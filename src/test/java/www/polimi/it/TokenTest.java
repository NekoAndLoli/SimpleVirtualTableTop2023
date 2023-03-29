package www.polimi.it;

import org.junit.Test;
import www.polimi.it.Model.ResourceType;
import www.polimi.it.Model.Token;
import www.polimi.it.Model.TokenImage;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TokenTest {
    @Test
    public void constructorCheck()
    {
        try {
            URI uri = new URI("src/main/resources/token/default.jpg");
            Token t = new Token(new TokenImage(uri,"default",true));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
