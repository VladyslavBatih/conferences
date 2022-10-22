package db.entity.dto;

import java.util.List;
import java.util.Objects;

public class ReportDTO {

   private String topic;
   private String speaker;
   private List<UserDTO> userDTOList;

    public ReportDTO() {
    }

    public ReportDTO(String topic, String speaker, List<UserDTO> userDTOList) {
        this.topic = topic;
        this.speaker = speaker;
        this.userDTOList = userDTOList;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
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
                ", speaker = '" + speaker + '\'' +
                '}';
    }
}