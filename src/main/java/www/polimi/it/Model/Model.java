package www.polimi.it.Model;

import www.polimi.it.Exception.NegativeException;
import www.polimi.it.Exception.PosNotFreeException;

import java.net.URI;
import java.util.ArrayList;

public class Model {
    private Player dm;
    private ArrayList<Player> players = new ArrayList<>();
    private Grid grid = new Grid();
    private MapImage backGround;
    private Music bgm;
    private ResourceSet resourceSet = ResourceSet.getInstance();

    public Model(String dm){
        this.dm = new Player(dm);
        players.add(this.dm);
    }
    public String toJson(){
        return "";
        //TODO
    }

    public void addToken(URI uri, Pos pos) throws PosNotFreeException {
        TokenImage image = resourceSet.getToken(uri);
        grid.addToken(pos,image);
    }

    public void associateToken(Token token, String playerId){
        //TODO
    }

    public void changeMap(URI uri){
        backGround = new MapImage(uri,"",false);
    }
    public MapImage getBackGround(){
        return backGround;
    }

    public Player getDm(){
        return dm;
    }

    public ArrayList<Player> getPlayers(){
        return players;
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

    public void setPlayerStats(Player player,String stat, Integer value, Integer max) throws NegativeException {
        setStats(new Pos(-1,-1),stat,value,max);
    }

    public void setPlayerStats(Player player, String stat, Integer value){
        //TODO Make an interface for Player and Token
    }

    public void uploadToken(){
        //TODO
    }

    public void uploadMusic(){//TODO

    }

    public void uploadMap(){//TODO

    }

    public void moveToken(){//TODO

    }

    public void addPlayer(String playerId){
        Player player = new Player(playerId);
        players.add(player);
    }

    public void setGrid(int rows, int columns) throws NegativeException {
        grid.setGrid(rows,columns);
    }


}
