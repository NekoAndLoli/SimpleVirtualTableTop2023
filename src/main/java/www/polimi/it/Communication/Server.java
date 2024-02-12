package www.polimi.it.Communication;




import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import www.polimi.it.Controller.Controller;
import www.polimi.it.Exception.PlayerOnlineException;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class Server extends WebSocketServer{
    private HashMap<Integer,Controller> rooms;
    Integer roomID;

    public Server(int port) {
        super(new InetSocketAddress(port));
        rooms = new HashMap<>();
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("ciao");
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println(webSocket + " has left the room!");
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println(webSocket + ": " + s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

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
        //TODO maybe better to check in controller
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

}
