package www.polimi.it.Actions;

import www.polimi.it.Actions.Action;
import www.polimi.it.Exception.NotDMException;
import www.polimi.it.Model.Model;

import java.net.URI;

public class ChangeBackgroundAction extends Action {
    URI uri;
    public ChangeBackgroundAction(String playerId,URI uri){
        super(ActionType.CHANGE_BACKGROUND,playerId);
        this.uri = uri;
    }
    @Override
    public void run(Model model) throws NotDMException {
        if(model.checkDm(this.getPlayerId())){
            model.changeMap(uri);
        }else {
            throw new NotDMException();
        }
    }
}
