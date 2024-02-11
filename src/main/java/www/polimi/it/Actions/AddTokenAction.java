package www.polimi.it.Actions;

import www.polimi.it.Exception.PosNotFreeException;
import www.polimi.it.Model.Model;
import www.polimi.it.Model.Pos;
import www.polimi.it.Model.TokenImage;

import java.net.URI;

public class AddTokenAction extends Action{

    private final Pos pos;
    private final URI tokenImage;

    public AddTokenAction(String playerId, Pos pos, URI token){
        super(ActionType.ADD_TOKEN,playerId);
        this.pos = pos;
        this.tokenImage = token;
    }

    public void run(Model model) throws Exception {
        model.addToken(tokenImage,pos);
    }
}
