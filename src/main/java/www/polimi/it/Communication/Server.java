package www.polimi.it.Communication;




import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import www.polimi.it.Actions.*;
import www.polimi.it.Controller.Controller;
import www.polimi.it.Exception.*;
import www.polimi.it.Model.Pos;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Server extends WebSocketServer{
    private HashMap<Integer,Controller> rooms;
    private Integer roomID;
    private HashMap<WebSocket,String> userConnections;
    private Set<String> usernames;
    private HashMap<WebSocket,Controller> player_room;

    public Server(int port) {
        super(new InetSocketAddress(port));
        rooms = new HashMap<>();
        userConnections = new HashMap<>();
        usernames = new HashSet<>();
        player_room = new HashMap<>();
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("ciao");
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        String username = userConnections.remove(webSocket);
        if(username != null){
            usernames.remove(username);
            System.out.println(username + " has gone offline!");
        }else{
            System.out.println(webSocket + " has gone offline!");
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        //TODO everything
        Controller room = player_room.get(webSocket);
        if(room!=null){//if the player has joined a room
            try {
                room.manageAction(webSocket,buildAction(s));
            } catch (MessageFormatException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (NegativeException e) {
                e.printStackTrace();
            }
        }

        if(s.indexOf("login:")==0){
            try {
                login(webSocket,s);
            } catch (PlayerOnlineException | MessageFormatException e) {
                e.printStackTrace();
            }
        }
        if(s.indexOf("create:")==0){
            try {
                createRoom(webSocket,s);//TODO
            } catch (MessageFormatException e) {
                e.printStackTrace();
            }
        }
        if(s.indexOf("join:")==0){
            try {
                joinRoom(webSocket,s);//TODO
            } catch (PlayerOnlineException | MessageFormatException | NoRoomException e) {
                e.printStackTrace();
            }
        }
        System.out.println(webSocket + ": " + s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
    }

    private void createRoom(WebSocket webSocket,String s) throws MessageFormatException {
        String[] strings = splitter(s);
        if(!checkFormat(strings,3))throw new MessageFormatException();
        String pw = strings[1];
        String dm = strings[2];
        //TODO check input
        Controller room = new Controller(roomID,pw,dm);
        rooms.put(roomID,room);
        roomID++;
        player_room.put(webSocket,room);
    }

    private void joinRoom(WebSocket webSocket,String s) throws NoRoomException, PlayerOnlineException, MessageFormatException {
        String[] strings = splitter(s);
        if(!checkFormat(strings,4))throw new MessageFormatException();
        Integer roomId = Integer.parseInt(strings[1]);
        String pw = strings[2];
        String playerId = strings[3];
        //TODO check inputs
        Controller room = rooms.get(roomId);
        if(room == null)throw new NoRoomException();
        if(room.checkPw(pw)){
            room.addPlayer(playerId);
            player_room.put(webSocket,room);
        }
    }

    private void deleteRoom(Integer roomId){
        rooms.remove(roomId);
    }


    /**
     *
     * @param webSocket
     * @param s should be login:\n[username]\n[password]
     * @throws PlayerOnlineException
     * @throws MessageFormatException
     */
    private void login(WebSocket webSocket,String s) throws PlayerOnlineException, MessageFormatException {//TODO password
        if(!usernames.add(s))throw new PlayerOnlineException();
        String[] strings = splitter(s);
        if(!checkFormat(strings,2,3))throw new MessageFormatException();
        userConnections.put(webSocket,"-"+strings[1]);
        //TODO password;
        String pw = strings[2];
    }

    private Action buildAction(String s) throws MessageFormatException, URISyntaxException, NegativeException,NumberFormatException {
        String[] strings = splitter(s);
        Action action = null;
        String playerId = strings[1];
        int x,y;
        URI uri;
        Pos pos1;
        Pos pos2;
        switch (strings[0]){
            case "AddToken:":
                if(!checkFormat(strings,5))throw new MessageFormatException();
                x =Integer.parseInt(strings[2]);
                y =Integer.parseInt(strings[3]);
                pos1 = new Pos(x,y);
                uri = new URI(strings[4]);
                action = new AddTokenAction(playerId,pos1,uri);
                break;
            case "ChangeBackGround:":
                if(!checkFormat(strings,3))throw new MessageFormatException();
                uri = new URI(strings[2]);
                action = new ChangeBackgroundAction(playerId,uri);
                break;
            case "MoveToken:":
                if(!checkFormat(strings,6))throw new MessageFormatException();
                x =Integer.parseInt(strings[2]);
                y =Integer.parseInt(strings[3]);
                pos1 = new Pos(x,y);
                x =Integer.parseInt(strings[4]);
                y =Integer.parseInt(strings[5]);
                pos2 = new Pos(x,y);
                action = new MoveTokenAction(playerId,pos1,pos2);
                break;
            case "SetGrid:":
                if(!checkFormat(strings,4))throw new MessageFormatException();
                x =Integer.parseInt(strings[2]);
                y =Integer.parseInt(strings[3]);
                action = new SetGridAction(playerId,x,y);
                break;
            case "RemoveToken:":
                if(!checkFormat(strings,3))throw new MessageFormatException();
                x =Integer.parseInt(strings[2]);
                y =Integer.parseInt(strings[3]);
                pos1 = new Pos(x,y);
                action = new RemoveTokenAction(playerId,pos1);
                break;
            default:
                throw new MessageFormatException();
        }
        //TODO
        return action;
    }

    private String[] splitter(String s){
        return s.split("\n");
    }
    private boolean checkFormat(String[] s,int n){
        return s.length == n;
    }
    private boolean checkFormat(String[] s,int min, int max){
        if(s.length<min)return false;
        if (s.length>max)return false;
        return true;
    }
}
