package www.polimi.it.Actions;

import org.java_websocket.WebSocket;
import www.polimi.it.Exception.*;
import www.polimi.it.Model.Model;

public abstract class Action {
    private ActionType type;
    private String playerId;
    public abstract void run(Model model) throws PosNotFreeException, PosOutOfBoundException, NotDMException, NoTokenException, NegativeException, NotYourTokenException, NoPlayerException;
    public Action(ActionType type, String playerId){
        this.type=type;
        this.playerId = playerId;
    }

    public ActionType getType() {
        return type;
    }

    public String getPlayerId() {
        return playerId;
    }
}
