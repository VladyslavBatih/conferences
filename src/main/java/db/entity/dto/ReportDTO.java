package db.entity.dto;

import java.util.List;
import java.util.Objects;

public class ReportDTO {

    private int id;
    private String topic;
    private String speakerFirstName;
    private String speakerLastName;
    private List<UserDTO> userDTOList;

    public ReportDTO() {
    }

    public ReportDTO(int id, String topic, String speakerFirstName, String speakerLastName, List<UserDTO> userDTOList) {
        this.id = id;
        this.topic = topic;
        this.speakerFirstName = speakerFirstName;
        this.speakerLastName = speakerLastName;
        this.userDTOList = userDTOList;
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

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDTO reportDTO = (ReportDTO) o;
        return topic.equals(reportDTO.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic);
    }

    @Override
    public String toString() {
        return "ReportDTO {" +
                "topic = '" + topic + '\'' +
                ", speaker = '" + speakerFirstName + '\'' +
                '}';
    }
}