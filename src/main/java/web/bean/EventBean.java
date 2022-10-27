package web.bean;

import db.entity.Event;

public class EventBean {

    private String name;
    private String place;
    private String date;
    private String time;
    private Event event;

    public EventBean() {
    }

    public EventBean(String name, String place, String date, String time) {
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventBean {" +
                "name = '" + name + '\'' +
                ", place = '" + place + '\'' +
                ", date = '" + date + '\'' +
                ", time = '" + time + '\'' +
                '}';
    }
}