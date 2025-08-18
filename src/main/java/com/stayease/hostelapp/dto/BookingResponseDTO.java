package com.stayease.hostelapp.dto;

import com.stayease.hostelapp.model.PGHostel;
import com.stayease.hostelapp.model.Room;

public class BookingResponseDTO {
    private Long pilgrimId;
    private Long hostelId;
    private String hostelName;
    private String location;
    private String contactNumber;
    private Long roomId;
    private String roomNumber;
    private Integer capacity;
    private Double price;
    private String pilgrimEmail;

    // Constructors
    public BookingResponseDTO() {}

    public BookingResponseDTO(Long pilgrimId, PGHostel hostel, Room room, String pilgrimEmail) {
        this.pilgrimId = pilgrimId;
        this.hostelId = hostel.getId();
        this.hostelName = hostel.getHostelName();
        this.location = hostel.getLocation();
        this.contactNumber = hostel.getContactNumber();
        this.roomId = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.capacity = room.getCapacity();
        this.price = room.getPrice();
        this.pilgrimEmail = pilgrimEmail;
    }

    // Getters and Setters
    public Long getPilgrimId() { return pilgrimId; }
    public void setPilgrimId(Long pilgrimId) { this.pilgrimId = pilgrimId; }

    public Long getHostelId() { return hostelId; }
    public void setHostelId(Long hostelId) { this.hostelId = hostelId; }

    public String getHostelName() { return hostelName; }
    public void setHostelName(String hostelName) { this.hostelName = hostelName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getPilgrimEmail() { return pilgrimEmail; }
    public void setPilgrimEmail(String pilgrimEmail) { this.pilgrimEmail = pilgrimEmail; }
}