package www.polimi.it.Actions;

import www.polimi.it.Exception.PosNotFreeException;
import www.polimi.it.Model.Model;
import www.polimi.it.Model.Pos;
import www.polimi.it.Model.TokenImage;

import java.net.URI;

public class AddTokenAction extends Action{

    private final Pos pos;
    private final URI tokenImage;

    public AddTokenAction(ActionType type, Pos pos, URI token){
        super(type);
        this.pos = pos;
        this.tokenImage = token;
    }

    public void run(Model model) throws Exception {
        model.addToken(tokenImage,pos);
    }
}
