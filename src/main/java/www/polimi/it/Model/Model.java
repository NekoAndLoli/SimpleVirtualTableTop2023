package www.polimi.it.Model;

import java.net.URI;
import java.util.ArrayList;

public class Model {
    private Player dm;
    private ArrayList<Player> players = new ArrayList<>();
    private Grid grid = new Grid();
    private MapImage backGround;
    private Music bgm;

    public Model(String dm){
        this.dm = new Player(dm);
        players.add(this.dm);
    }
    public String toJson(){
        return "";
        //TODO
    }

    public void addToken(URI uri, Pos pos){
        //TODO
    }

    public void associateToken(){
        //TODO
    }

    public void changeMap(URI uri){
        //TODO
    }

    public void changeMusic(URI uri){
        //TODO
    }


    public void setStats(Pos token, String stat, Integer value, Integer max){
        //TODO
    }
    public void setStats(Pos token, String stat, Integer value){
        //TODO
    }

    public void setPlayerStats(Player player,String stat, Integer value, Integer max){
        setStats(new Pos(-1,-1),stat,value,max);
    }

    public void setPlayerStats(Player player, String stat, Integer value){
        //TODO Make an interface for Player and Token
    }

    public void uploadToken(){
        //TODO
    }

    public void uploadMusic(){

    }

    public void uploadMap(){

    }

    public void moveToken(){

    }

    public void addPlayer(){

    }


}
