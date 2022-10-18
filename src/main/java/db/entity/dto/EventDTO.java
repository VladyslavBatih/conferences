package db.entity.dto;

public class EventDTO {

    private int id;
    private String name;
    private String place;
    private String date;
    private String time;

    public EventDTO() {
    }

    public EventDTO(int id, String name, String place, String date, String time) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
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
}