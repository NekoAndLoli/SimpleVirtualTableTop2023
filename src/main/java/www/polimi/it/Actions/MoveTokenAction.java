package www.polimi.it.Actions;

import www.polimi.it.Exception.*;
import www.polimi.it.Model.Model;
import www.polimi.it.Model.Pos;

public class MoveTokenAction extends Action{
    Pos posStart;
    Pos posEnd;
    public MoveTokenAction(String playerId,  Pos posStart, Pos posEnd){
        super(ActionType.MOVE_TOKEN,playerId);
        this.posEnd = posEnd;
        this.posStart = posStart;
    }
    @Override
    public void run(Model model) throws NoTokenException, PosNotFreeException, NotYourTokenException, PosOutOfBoundException, NoPlayerException {
        model.moveToken(posStart,posEnd,this.getPlayerId());
    }
}
