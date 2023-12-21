package www.polimi.it.Communication;

import javax.swing.*;
import java.util.ArrayList;

public class Chat {
    ArrayList<Message> list = new ArrayList<>();

    public void addMsg(Message msg){
        if(checkMsg(msg)) {
            list.add(msg);
        }
    }

    private boolean checkMsg(Message msg){
        if(msg.getPlayerId() == "")return false;
        if(msg.getTime() == null) return false;
        return true;
    }
}
