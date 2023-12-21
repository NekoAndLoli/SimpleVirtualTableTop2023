package www.polimi.it.Model;

import java.net.URI;
import java.util.ArrayList;

public class ResourceSet {
    private static ResourceSet instance;
    private ArrayList<TokenImage> tokens;
    private ArrayList<Music> musics;
    private ArrayList<MapImage> maps;

    public ResourceSet(){
        if(instance ==null){
            instance= new ResourceSet();
        }
    }

    public static ResourceSet getInstance(){
        return instance;
    }

    public void addMap(){

    }

    public void addMusic(){
        //TODO
    }

    public void addToken(){

    }

    public void loadResoueces(){
        //TODO
    }


    public TokenImage getToken(URI uri) {
        for(TokenImage t: tokens){
            if(t.getUri().equals(uri))return t;
        }
        return null;
    }
    public Music getMusic(URI uri) {
        for(Music m: musics){
            if(m.getUri().equals(uri))return m;
        }
        return null;
    }
}
