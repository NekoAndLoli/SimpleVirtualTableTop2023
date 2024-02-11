package www.polimi.it.Model;

import java.net.URI;

public class TokenImage extends Resource{
    public TokenImage(URI uri, String name, boolean isLocal) {
        super(uri, name, isLocal, ResourceType.Token);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)return false;
        if(obj instanceof TokenImage){
            TokenImage o = (TokenImage) obj;
            return this.getUri().equals(o.getUri());
        }
        return false;
    }
}
