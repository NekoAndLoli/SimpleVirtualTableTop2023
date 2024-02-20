package www.polimi.it.Actions;

import www.polimi.it.Exception.NegativeException;
import www.polimi.it.Exception.NotDMException;
import www.polimi.it.Model.Model;

public class SetGridAction extends Action{
    Integer rows;
    Integer columns;

    public SetGridAction(String playerId, Integer rows,Integer columns){
        super(ActionType.SET_GRID,playerId);
        this.columns = columns;
        this.rows = rows;
    }
    @Override
    public void run(Model model) throws NotDMException, NegativeException {
        if(model.checkDm(this.getPlayerId())) {
            model.setGrid(this.rows,this.columns);
            model.send("s"+this.rows+" "+this.columns);
        }else {
            throw new NotDMException();
        }
    }
}
