package Domain;

public class MenssagePrivate {

    String senderID;
    String receptorID;
    String menssage;


    public MenssagePrivate(String senderID, String receptorID, String menssage) {
        this.senderID = senderID;
        this.receptorID = receptorID;
        this.menssage = menssage;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getMenssage() {
        return menssage;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }
}
