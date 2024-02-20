package www.polimi.it.Controller;

import org.java_websocket.WebSocket;
import www.polimi.it.Actions.Action;
import www.polimi.it.Exception.*;
import www.polimi.it.Model.Model;
import www.polimi.it.Server.Database;

public class Controller {
    private Database database;
    private Model model;
    private final int roomId;
    private final String pw;
    private boolean closed = false;


    public void manageAction(WebSocket webSocket, Action action) {
        //TODO finish
        String message="";
        try {
            action.run(model);
        }catch (NoTokenException e) {
            e.printStackTrace();
            message = "No token selected.";
        } catch (PosNotFreeException e) {
            e.printStackTrace();
            message = "Cell is already taken.";
        } catch (NotYourTokenException e) {
            e.printStackTrace();
            message = "You have not permission on this token.";
        } catch (NotDMException e) {
            e.printStackTrace();
            message = "You are not the Dungeon Master.";
        } catch (NegativeException e) {
            e.printStackTrace();
            message = "Number should be positive.";
        } catch (PosOutOfBoundException e) {
            e.printStackTrace();
            message = "Cell position is out of map.";
        } catch (NoPlayerException e) {
            e.printStackTrace();
            message = "This player does not exist.";
        }finally {
            if(message!="")sendError(webSocket,message);
        }
    }

    public Controller(int roomId, String pw, String playerID){
        this.roomId = roomId;
        this.pw = pw;
        model = new Model(playerID);
    }

    /**
     * Closes the room and ends the game
     */
    public void closeRoom(){
        closed = true;
    }

    public boolean isClosed(){
        return closed;
    }

    public boolean checkPw(String pw){
        if(this.pw == null)return true;
        return this.pw.equals(pw);
    }

    public void addPlayer(String playerID,WebSocket socket) throws PlayerOnlineException {
        model.addPlayer(playerID,socket);
        model.send("n"+playerID,socket);
    }

    private void sendError(WebSocket webSocket,String s){
        webSocket.send("[ERROR]:"+s);
    }


    public String getUpdate(){
        return model.getUpdate();
    }

    public void setDMSocket(WebSocket socket){
        model.getDm().setWebSocket(socket);
    }

    public String getinfo(){
        return model.getInfo()+"|"+roomId+"|"+getRoomname();
    }

    public String getRoomname(){
        return     model.getRoomName();
    }

    public void setRoomName(String s){
        model.setRoomName(s);
    }

    public Integer getRoomId() {
        return roomId;
    }
}
