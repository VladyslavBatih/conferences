package db.entity;

import java.util.Objects;

public class Event extends Entity {

    private String name;
    private String place;
    private String date;
    private String time;

    public Event() {
    }

    public Event(String name, String place, String date, String time) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
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

    @Override
    public int compareTo(Object o) {
        Event event = (Event) o;
        return name.compareTo(event.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return name.equals(event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Event {" +
                "name = '" + name + '\'' +
                ", place = '" + place + '\'' +
                ", date = '" + date + '\'' +
                ", time = '" + time + '\'' +
                '}';
    }
}