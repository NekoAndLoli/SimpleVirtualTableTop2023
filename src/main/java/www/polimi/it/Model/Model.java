package www.polimi.it.Model;

import org.java_websocket.WebSocket;
import www.polimi.it.Exception.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Model {
    private DM dm;
    private HashMap<String,Player> players = new HashMap<>();
    private Grid grid = new Grid();
    private MapImage backGround;
    private Music bgm;
    private ResourceSet resourceSet = ResourceSet.getInstance();
    private Random random = new Random();

    private String roomName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    private String update = "";

    public Model(String dm){
        random.setSeed(System.currentTimeMillis());
        this.dm = new DM(dm);
        players.put(dm,this.dm);
        try{
            URI uri = new URI("https://media.52poke.com/wiki/f/ff/%E9%87%91%E9%BB%83%E5%B8%82_HGSS.png");
            backGround = new MapImage(uri,"",false);
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public String toJson(){
        return "";
        //TODO
    }

    public void addToken(URI uri, Pos pos) throws PosNotFreeException, PosOutOfBoundException {
        TokenImage image = new TokenImage(uri,"",false);
        grid.addToken(pos,image);
        String message = "a"+pos.getX() +" "+ pos.getY()+" "+uri;
        send(message);
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
        String message = "m"+start.getX() +" "+ start.getY()+" "+end.getX() +" "+ end.getY();
        send(message);
    }

    public void removeToken(Pos start, String playerID) throws NoTokenException, PosOutOfBoundException {
        grid.removeToken(start);
        String message = "r"+start.getX() +" "+ start.getY();
        send(message);
    }

    public void addPlayer(String playerId, WebSocket socket) throws PlayerOnlineException {
        if(players.containsKey(playerId))throw new PlayerOnlineException();
        Player player;
        if(playerId.equals(dm.getPlayerID())){
            dm = new DM(playerId);
            player = dm;
        }else {
            player= new Player(playerId);
        }
        player.setWebSocket(socket);
        players.put(playerId,player);
    }

    public void setGrid(int rows, int columns) throws NegativeException {
        grid.setGrid(rows,columns);
    }

    public boolean checkDm(String playerId){
        return this.dm.getPlayerID().equals(playerId);
    }

    public Player getPlayer(String playerId) throws NoPlayerException {
        Player player = players.get(playerId);
        if(player == null)throw new NoPlayerException();
        return player;
    }

    public void initFromData(){
        //TODO
    }

    public void setUpdate(String s){
        update=s;
    }
    public String getUpdate(){
        return update;
    }

    public String getInfo() {
        //TODO
        String playerList = "";
        for(Player p: players.values()){
            playerList+=p.getPlayerID()+" ";
        }
        playerList = playerList.substring(0,playerList.length()-1);
        String mapUrl = backGround.getUri().toString();
        String tokens = grid.getTokenInfo();
        if(tokens.length()>1) {
            tokens = tokens.substring(0, tokens.length() - 1);
        }
        String menuToken = "";
        return playerList+"|"+tokens+"|"+mapUrl+"|"+grid.getRows()+"|"+grid.getColumns()+"|"+dm.getPlayerID();
    }

    public void send(String message){
        for (Player p : players.values()){
            p.send(message);
        }
    }

    public void send(String message,WebSocket exclude){
        for (Player p : players.values()){
            if (!p.getWebSocket().equals(exclude)){
                p.send(message);
            }
        }
    }

    public int diceRoll(int d){
        return random.nextInt(d+1);
    }

    public void removePlayer(String username) {
        players.remove(username);
        send("o"+username);
    }
}
