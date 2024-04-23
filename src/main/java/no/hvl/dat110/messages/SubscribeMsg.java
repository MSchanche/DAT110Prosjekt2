package no.hvl.dat110.messages;

public class SubscribeMsg extends Message {

    // message sent from client to subscribe on a topic
    private String topic;

    // Constructor
    public SubscribeMsg(String user, String topic) {
        super(MessageType.SUBSCRIBE, user);
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "SubscribeMsg [user=" + getUser() + ", topic=" + getTopic() + "]";
    }
}