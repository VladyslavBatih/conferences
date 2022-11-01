package web.bean;

import db.entity.Report;

public class ReportBean {

    private int eventId;
    private String topic;
    private String speakerFirstName;
    private String speakerLastName;
    private Report report;

    public ReportBean() {
    }

    public ReportBean(int eventId, String topic, String speakerFirstName, String speakerLastName) {
        this.eventId = eventId;
        this.topic = topic;
        this.speakerFirstName = speakerFirstName;
        this.speakerLastName = speakerLastName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSpeakerFirstName() {
        return speakerFirstName;
    }

    public void setSpeakerFirstName(String speakerFirstName) {
        this.speakerFirstName = speakerFirstName;
    }

    public String getSpeakerLastName() {
        return speakerLastName;
    }

    public void setSpeakerLastName(String speakerLastName) {
        this.speakerLastName = speakerLastName;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "ReportBean {" +
                "eventId = " + eventId +
                ", topic = '" + topic + '\'' +
                ", speakerFirstName = '" + speakerFirstName + '\'' +
                ", speakerLastName = '" + speakerLastName + '\'' +
                '}';
    }
}