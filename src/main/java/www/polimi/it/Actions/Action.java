package www.polimi.it.Actions;

import www.polimi.it.Exception.PosNotFreeException;
import www.polimi.it.Model.Model;

public abstract class Action {
    private ActionType type;
    private String playerId;
    public abstract void run(Model model) throws Exception;
    public Action(ActionType type){
        type=type;
    }

}
