package www.polimi.it.Model;

import java.net.URI;

public class Music extends Resource{
    public Music(URI uri, String name, boolean isLocal) {
        super(uri, name, isLocal, ResourceType.Music);
    }
}
