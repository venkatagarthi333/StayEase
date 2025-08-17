package com.stayease.hostelapp.dto;

import com.stayease.hostelapp.model.Piligrim;
import lombok.Data;

@Data
public class PilgrimResponseDTO {
    private Long id;
    private String username;
    private String email; // Derived from User

    public PilgrimResponseDTO(Piligrim pilgrim) {
        this.id = pilgrim.getId();
        this.username=pilgrim.getUser() != null ? pilgrim.getUser().getUsername() : null;
        this.email = pilgrim.getUser() != null ? pilgrim.getUser().getEmail() : null;
    }
}