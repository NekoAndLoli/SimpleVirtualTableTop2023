package www.polimi.it.Model;

import java.net.URI;

public class TokenImage extends Resource{
    public TokenImage(URI uri, String name, boolean isLocal) {
        super(uri, name, isLocal, ResourceType.Token);
    }
}
