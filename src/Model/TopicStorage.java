package Model;


public class TopicStorage {
    private final static TopicStorage topicStorage = new TopicStorage();
    private String topic;

    public TopicStorage(){}

    public static TopicStorage getInstance() {
        return topicStorage;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}