package www.polimi.it.Model;

import www.polimi.it.Exception.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private DM dm;
    private HashMap<String,Player> players = new HashMap<>();
    private Grid grid = new Grid();
    private MapImage backGround;
    private Music bgm;
    private ResourceSet resourceSet = ResourceSet.getInstance();

    public Model(String dm){
        this.dm = new DM(dm);
        players.put(dm,this.dm);
    }
    public String toJson(){
        return "";
        //TODO
    }

    public void addToken(URI uri, Pos pos) throws PosNotFreeException, PosOutOfBoundException {
        TokenImage image = new TokenImage(uri,"",false);
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

    public DM getDm(){
        return dm;
    }

    public HashMap<String,Player> getPlayers(){
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

    public void moveToken(Pos start, Pos end, String playerID) throws NoTokenException, PosNotFreeException, NotYourTokenException, PosOutOfBoundException, NoPlayerException {//TODO
        grid.moveToken(start,end,getPlayer(playerID));
    }

    public void addPlayer(String playerId) throws PlayerOnlineException {
        Player player = new Player(playerId);
        if(players.containsKey(playerId))throw new PlayerOnlineException();
        players.put(playerId,player);
    }

    public void setGrid(int rows, int columns) throws NegativeException {
        grid.setGrid(rows,columns);
    }

    public boolean checkDm(String playerId){
        return this.dm.equals(playerId);
    }

    public Player getPlayer(String playerId) throws NoPlayerException {
        Player player = players.get(playerId);
        if(player == null)throw new NoPlayerException();
        return player;
    }

    public void initFromData(){
        //TODO
    }
}
