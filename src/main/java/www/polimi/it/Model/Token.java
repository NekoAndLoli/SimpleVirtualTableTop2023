package www.polimi.it.Model;

public class Token {
    private String url;

    /**
     * Constructor
     * @param url url of the resource
     */
    public Token(String url){
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
