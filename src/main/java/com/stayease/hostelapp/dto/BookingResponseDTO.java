package com.stayease.hostelapp.dto;

import com.stayease.hostelapp.model.PGHostel;
import com.stayease.hostelapp.model.Room;

public class BookingResponseDTO {
    private Long pilgrimId;
    private PGHostel hostel;
    private Room room;
    private String pilgrimEmail;

    public BookingResponseDTO() {}

    public BookingResponseDTO(Long pilgrimId, PGHostel hostel, Room room, String pilgrimEmail) {
        this.pilgrimId = pilgrimId;
        this.hostel = hostel;
        this.room = room;
        this.pilgrimEmail = pilgrimEmail;
    }

    // Getters and Setters
    public Long getPilgrimId() {
        return pilgrimId;
    }

    public void setPilgrimId(Long pilgrimId) {
        this.pilgrimId = pilgrimId;
    }

    public PGHostel getHostel() {
        return hostel;
    }

    public void setHostel(PGHostel hostel) {
        this.hostel = hostel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getPilgrimEmail() {
        return pilgrimEmail;
    }

    public void setPilgrimEmail(String pilgrimEmail) {
        this.pilgrimEmail = pilgrimEmail;
    }
}