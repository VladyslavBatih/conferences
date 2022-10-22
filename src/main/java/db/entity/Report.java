package db.entity;

import java.util.Objects;

public class Report extends Entity {

    private String topic;
    private int eventId;
    private int speakerId;

    public Report() {
    }

    public Report(String topic, int eventId, int speakerId) {
        this.topic = topic;
        this.eventId = eventId;
        this.speakerId = speakerId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    @Override
    public int compareTo(Object o) {
        Report report = (Report) o;
        return topic.compareTo(report.topic) + eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return eventId == report.eventId && topic.equals(report.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, eventId);
    }

    @Override
    public String toString() {
        return "Report {" +
                "topic = '" + topic + '\'' +
                ", eventId = " + eventId +
                ", speakerId = " + speakerId +
                '}';
    }
}