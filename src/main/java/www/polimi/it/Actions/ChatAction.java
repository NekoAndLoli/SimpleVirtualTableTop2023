package www.polimi.it.Actions;

import www.polimi.it.Exception.*;
import www.polimi.it.Model.Model;

import java.util.Random;

public class ChatAction extends Action{

    String message;

    public ChatAction(String playerId,String message) {
        super(ActionType.CHAT, playerId);
        this.message = message;

    }

    @Override
    public void run(Model model) throws PosNotFreeException, PosOutOfBoundException, NotDMException, NoTokenException, NegativeException, NotYourTokenException, NoPlayerException {
        model.send(" "+getPlayerId()+":"+message);
        if (message.charAt(0)=='d'){
            try {
                int roll = Integer.parseInt(message.substring(1));
                model.send(" Dice roll:"+model.diceRoll(roll));
            }catch (Exception e){
                //Do nothing
            }
        }
    }
}
