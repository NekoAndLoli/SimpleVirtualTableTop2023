package www.polimi.it.Communication;

import java.sql.Time;

public class Message {
    private String playerId;
    private Time time;
    private String messageText;
    private Integer roomId;

    public Message(String playerId, Time time, String messageText,Integer roomId) {
        this.roomId = roomId;
        this.playerId = playerId;
        this.time = time;
        this.messageText = messageText;
    }

    public String getPlayerId() {
        return playerId;
    }

    public Time getTime() {
        return time;
    }

    public String getMessageText() {
        return messageText;
    }
}
