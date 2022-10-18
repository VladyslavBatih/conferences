package db.entity.dto;

public class ReportDTO {

    private int id;
    private String topic;
    private int event_id;
    private int speaker_id;

    public ReportDTO() {
    }

    public ReportDTO(int id, String topic, int event_id, int speaker_id) {
        this.id = id;
        this.topic = topic;
        this.event_id = event_id;
        this.speaker_id = speaker_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(int speaker_id) {
        this.speaker_id = speaker_id;
    }
}