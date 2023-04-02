package www.polimi.it.Controller;

import www.polimi.it.Model.Model;
import www.polimi.it.Server.Database;

public class Controller {
    private Database database;
    private Model model;
    private final int roomId;
    private final String pw;

    public void manageAction(){

    }

    public Controller(int roomId, String pw, String playerID){
        this.roomId = roomId;
        this.pw = pw;
        model = new Model(playerID);
    }
}
