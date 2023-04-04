package www.polimi.it.Model;

import java.net.URI;

public class Resource {
    private URI uri;
    private String name;
    private boolean isLocal;
    private final ResourceType type;

    public Resource(URI uri, String name, boolean isLocal, ResourceType type) {
        this.uri = uri;
        this.name = name;
        this.isLocal = isLocal;
        this.type = type;
    }

    public URI getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public ResourceType getType() {
        return type;
    }
}
