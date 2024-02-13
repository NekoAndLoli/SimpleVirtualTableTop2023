package www.polimi.it.Actions;

import www.polimi.it.Exception.*;
import www.polimi.it.Model.Model;
import www.polimi.it.Model.Pos;

public class RemoveTokenAction extends Action{
    public RemoveTokenAction(String playerId, Pos pos) {
        super(ActionType.REMOVE_TOKEN, playerId);
    }

    @Override
    public void run(Model model) throws PosNotFreeException, PosOutOfBoundException, NotDMException, NoTokenException, NegativeException, NotYourTokenException, NoPlayerException {

    }
}
