package www.polimi.it.Actions;

import www.polimi.it.Exception.NoTokenException;
import www.polimi.it.Exception.NotYourTokenException;
import www.polimi.it.Exception.PosNotFreeException;
import www.polimi.it.Exception.PosOutOfBoundException;
import www.polimi.it.Model.Model;
import www.polimi.it.Model.Pos;

public class MoveTokenAction extends Action{
    Pos posStart;
    Pos posEnd;
    public MoveTokenAction(String playerId, Pos posEnd, Pos posStart){
        super(ActionType.MOVE_TOKEN,playerId);
    }
    @Override
    public void run(Model model) throws NoTokenException, PosNotFreeException, NotYourTokenException, PosOutOfBoundException {
        model.moveToken(posStart,posEnd,this.getPlayerId());
    }
}
