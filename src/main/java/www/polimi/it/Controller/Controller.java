package www.polimi.it.Controller;

import www.polimi.it.Model.Action;
import www.polimi.it.Model.Model;
import www.polimi.it.Server.Database;

public class Controller {
    private Database database;
    private Model model;
    private final int roomId;
    private final String pw;
    private boolean closed = false;

    public void manageAction(Action action){
        //TODO
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
}
