package www.polimi.it.Controller;

import www.polimi.it.Actions.Action;
import www.polimi.it.Exception.*;
import www.polimi.it.Model.Model;
import www.polimi.it.Server.Database;

public class Controller {
    private Database database;
    private Model model;
    private final int roomId;
    private final String pw;
    private boolean closed = false;

    public void manageAction(Action action){
        try {
            action.run(model);
        } catch (NoTokenException e) {
            e.printStackTrace();
        } catch (PosNotFreeException e) {
            e.printStackTrace();
        } catch (NotYourTokenException e) {
            e.printStackTrace();
        } catch (NotDMException e) {
            e.printStackTrace();
        } catch (NegativeException e) {
            e.printStackTrace();
        } catch (PosOutOfBoundException e) {
            e.printStackTrace();
        } catch (NoPlayerException e) {
            e.printStackTrace();
        }
    }

    public Controller(int roomId, String pw, String playerID){
        this.roomId = roomId;
        this.pw = pw;
        model = new Model(playerID);
    }

    /**
     * Closes the room and ends the game
     */
    public void closeRoom(){
        closed = true;
    }

    public boolean isClosed(){
        return closed;
    }

    public boolean checkPw(String pw){
        return pw.equals(pw);
    }

    public void addPlayer(String playerID) throws PlayerOnlineException {
        model.addPlayer(playerID);
    }

}
