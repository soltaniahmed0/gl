package com.example.Backend.DTO;

import java.util.Date;

public class StartupEventDTO {
    private int eventId;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String location;
    private String host;
    private double price;
    private boolean approve;
    private byte[] eventimg;
    private int employeeId;
    private String employeeFullName;

    public StartupEventDTO() {}

    public StartupEventDTO(int eventId, String eventName, String eventDescription, Date eventDate, 
                          String eventStartTime, String eventEndTime, String location, String host, 
                          double price, boolean approve, byte[] eventimg, int employeeId, String employeeFullName) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.location = location;
        this.host = host;
        this.price = price;
        this.approve = approve;
        this.eventimg = eventimg;
        this.employeeId = employeeId;
        this.employeeFullName = employeeFullName;
    }

    // Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public byte[] getEventimg() {
        return eventimg;
    }

    public void setEventimg(byte[] eventimg) {
        this.eventimg = eventimg;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }
}