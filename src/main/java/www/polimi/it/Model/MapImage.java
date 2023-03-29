package www.polimi.it.Model;

import java.net.URI;

public class MapImage extends Resource{
    public MapImage(URI uri, String name, boolean isLocal) {
        super(uri, name, isLocal, ResourceType.BackGround);
    }
}
