package www.polimi.it.Communication;

import www.polimi.it.Model.Player;



import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends WebSocketServer{

    public Server(int port) {
        super(new InetSocketAddress(port));
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

    private void CreateRoom(){

    }

    private void joinRoom(String playerId, Integer roomId, String pw) {

    }

    private void deleteRoom(Integer roomId){

    }

}
