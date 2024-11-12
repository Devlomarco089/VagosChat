package Domain;

public class MessagesGroup {
    String senderID;
    String message;

    public MessagesGroup(String senderID, String message) {
        this.senderID = senderID;
        this.message = message;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
