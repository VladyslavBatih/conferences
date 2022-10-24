package db.entity.dto;

import java.util.List;
import java.util.Objects;

public class EventDTO {

    private int id;
    private String name;
    private String place;
    private String date;
    private String time;
    private List<ReportDTO> reportDTOList;

    public EventDTO() {
    }

    public EventDTO(int id, String name, String place, String date, String time, List<ReportDTO> reportDTOList) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
        this.reportDTOList = reportDTOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ReportDTO> getReportDTOList() {
        return reportDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDTO eventDTO = (EventDTO) o;
        return name.equals(eventDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "EventDTO {" +
                "name = '" + name + '\'' +
                ", place = '" + place + '\'' +
                ", date = '" + date + '\'' +
                ", time = '" + time + '\'' +
                '}';
    }
}