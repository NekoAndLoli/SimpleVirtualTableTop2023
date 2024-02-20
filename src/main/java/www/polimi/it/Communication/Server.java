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

public class  Server extends WebSocketServer{
    private HashMap<Integer,Controller> rooms;
    private Integer roomID = 0;
    private HashMap<WebSocket,String> userConnections;
    private Set<String> usernames;
    private HashMap<WebSocket,Controller> player_room;
    private HashMap<Controller,WebSocket> room_player;
    private HashMap<String,String> player_password;

    public Server(int port) {
        super(new InetSocketAddress(port));
        rooms = new HashMap<>();
        userConnections = new HashMap<>();
        usernames = new HashSet<>();
        player_room = new HashMap<>();
        room_player = new HashMap<>();
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        //Nothing to do
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
                String playerId = userConnections.get(webSocket);
                room.manageAction(webSocket,buildAction(s,playerId));
                //send mod to all clients
            } catch (MessageFormatException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (NegativeException e) {
                e.printStackTrace();
            } catch (PlayerInRoomException e) {
                e.printStackTrace();
            }
        }

        if(s.indexOf("login:")==0){
            try {
                login(webSocket,s);
                webSocket.send("lSuccess");
            } catch (PlayerOnlineException | MessageFormatException e) {
                e.printStackTrace();
                webSocket.send("Fail");
            }
        }
        if(s.indexOf("create:")==0){
            try {
                Controller r = createRoom(webSocket,s);//TODO
                webSocket.send("cSuccess ");
                webSocket.send("i"+r.getinfo());
            } catch (MessageFormatException e) {
                e.printStackTrace();
                webSocket.send("Fail");
            }
        }
        if(s.indexOf("join:")==0){
            try {
                joinRoom(webSocket,s);//TODO
                webSocket.send("jSuccess");
                if(player_room.get(webSocket) != null){
                    room = player_room.get(webSocket);
                    webSocket.send("i"+room.getinfo());//To test
                }
            } catch (PlayerOnlineException | MessageFormatException | NoRoomException | WrongPWException | NoPlayerException e) {
                e.printStackTrace();
                webSocket.send("Fail");
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

    private Controller createRoom(WebSocket webSocket,String s) throws MessageFormatException {
        String[] strings = splitter(s);
        if(!checkFormat(strings,2,3))throw new MessageFormatException();
        String roomName = strings[1].substring(1);
        String dm = userConnections.get(webSocket);
        String pw = null;
        if(strings.length==3 && strings[1].length()>1){
            pw = strings[2].substring(1);
        }
        //TODO check input
        Controller room = new Controller(roomID,pw,dm);
        room.setRoomName(roomName);
        room.setDMSocket(webSocket);
        rooms.put(roomID,room);
        System.out.println("Created room id:"+roomID);
        int id = roomID;
        roomID++;
        player_room.put(webSocket,room);
        return room;
    }

    private Integer joinRoom(WebSocket webSocket,String s) throws NoRoomException, PlayerOnlineException, MessageFormatException, WrongPWException, NoPlayerException {
        String[] strings = splitter(s);
        if(!checkFormat(strings,2,3))throw new MessageFormatException();

        Integer roomId = Integer.parseInt(strings[1].substring(1));
        String pw = null;
        if(strings.length==3 && strings[1].length()>1){
            pw = strings[2].substring(1);
        }
        String playerId = userConnections.get(webSocket);
        if (playerId == null)throw new NoPlayerException();
        //TODO check inputs
        Controller room = rooms.get(roomId);
        if(room == null)throw new NoRoomException();
        if(room.checkPw(pw)){
            room.addPlayer(playerId,webSocket);
            player_room.put(webSocket,room);
        }else{
            throw new WrongPWException();
        }
        return roomId;
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
        String[] strings = splitter(s);
        if(!checkFormat(strings,2,3))throw new MessageFormatException();
        String username = strings[1].substring(1);
        if(!usernames.add(username))throw new PlayerOnlineException();
        userConnections.put(webSocket,username);
        //TODO password
        //String pw = strings[2];
    }

    private Action buildAction(String s,String playerId) throws MessageFormatException, URISyntaxException, NegativeException, NumberFormatException, PlayerInRoomException {
        String[] strings = splitter(s);
        Action action;
        int x,y;
        URI uri;
        Pos pos1;
        Pos pos2;
        switch (strings[0]){//addtoken x y uri
            case "AddToken:":
                if(!checkFormat(strings,4))throw new MessageFormatException();
                x =Integer.parseInt(strings[1].substring(1));
                y =Integer.parseInt(strings[2].substring(1));
                pos1 = new Pos(x,y);
                uri = new URI(strings[3].substring(1));
                action = new AddTokenAction(playerId,pos1,uri);
                break;
            case "ChangeBackGround:":
                if(!checkFormat(strings,2))throw new MessageFormatException();
                uri = new URI(strings[1].substring(1));
                action = new ChangeBackgroundAction(playerId,uri);
                break;
            case "MoveToken:":
                if(!checkFormat(strings,5))throw new MessageFormatException();
                x =Integer.parseInt(strings[1].substring(1));
                y =Integer.parseInt(strings[2].substring(1));
                pos1 = new Pos(x,y);
                x =Integer.parseInt(strings[3].substring(1));
                y =Integer.parseInt(strings[4].substring(1));
                pos2 = new Pos(x,y);
                action = new MoveTokenAction(playerId,pos1,pos2);
                break;
            case "SetGrid:":
                if(!checkFormat(strings,3))throw new MessageFormatException();
                x =Integer.parseInt(strings[1].substring(1));
                y =Integer.parseInt(strings[2].substring(1));
                action = new SetGridAction(playerId,x,y);
                break;
            case "RemoveToken:":
                if(!checkFormat(strings,3))throw new MessageFormatException();
                x =Integer.parseInt(strings[1].substring(1));
                y =Integer.parseInt(strings[2].substring(1));
                pos1 = new Pos(x,y);
                action = new RemoveTokenAction(playerId,pos1);
                break;
            case "join:":
                throw new PlayerInRoomException();
            case "message:":
                action = new ChatAction(playerId,strings[1]);
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
        return checkFormat(s,n,n);
    }
    private boolean checkFormat(String[] s,int min, int max){
        int count = 0;
        for(int i = 0;i<s.length && i<min;i++){
            if(s[i].length()!=1)count++;
        }
        if(count!=min)return false;
        if (s.length>max)return false;
        return true;
    }
}
