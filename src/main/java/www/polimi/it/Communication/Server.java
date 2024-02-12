package www.polimi.it.Communication;




import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import www.polimi.it.Actions.Action;
import www.polimi.it.Actions.AddTokenAction;
import www.polimi.it.Controller.Controller;
import www.polimi.it.Exception.*;

import java.net.InetSocketAddress;
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
        if(room!=null){
            try {
                room.manageAction(buildAction(s));
            } catch (NoTokenException e) {
                e.printStackTrace();
            } catch (PosNotFreeException e) {
                e.printStackTrace();
            } catch (NotYourTokenException e) {
                e.printStackTrace();
            } catch (NotDMException e) {
                e.printStackTrace();
            } catch (NegativeException e) {
                e.printStackTrace();
            } catch (PosOutOfBoundException e) {
                e.printStackTrace();
            } catch (NoPlayerException e) {
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

    private void CreateRoom(String dm,String pw){
        Controller room = new Controller(roomID,pw,dm);
        rooms.put(roomID,room);
        roomID++;
    }

    private void joinRoom(String playerId, Integer roomId, String pw) {
        Controller room = rooms.get(roomId);
        if(room.checkPw(pw)){
            try {
                room.addPlayer(playerId);
            }catch (PlayerOnlineException e){
                //TODO reply with name taken
            }
        }
    }

    private void deleteRoom(Integer roomId){
        rooms.remove(roomId);
    }


    private void login(WebSocket webSocket,String s) throws PlayerOnlineException {//TODO password
        if(!usernames.add(s))throw new PlayerOnlineException();
        userConnections.put(webSocket,s);
    }

    private Action buildAction(String s){
        //TODO
        return null;
    }
}
