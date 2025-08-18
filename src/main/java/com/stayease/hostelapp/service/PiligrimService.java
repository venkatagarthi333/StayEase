package com.stayease.hostelapp.service;

import com.stayease.hostelapp.dto.BookingResponseDTO;
import com.stayease.hostelapp.model.*;
import com.stayease.hostelapp.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiligrimService {

    @Autowired
    private PiligrimRepository pilgrimRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private PGHostelRepository pgHostelRepository;

    @Autowired
    private UserRepository userRepo;

    //search hostels based on location
    public List<PGHostel> searchHostelsByLocation(String location) {
        return pgHostelRepository.findByLocationIgnoreCase(location);
    }


    //get rooms of hostels
    public List<Room> getRoomsByHostel(Long hostelId) {
        return roomRepo.findByPgHostelId(hostelId);
    }

        @Transactional
        public BookingResponseDTO bookRoom(String pilgrimEmail, Long roomId) {
            //to check rooms available or not
            Room room1 = roomRepo.findById(roomId).orElseThrow();
            if (room1.getAvailableBeds() <= 0) {
                throw new RuntimeException("No beds available in this room");
            }

            User user = userRepo.findByEmail(pilgrimEmail)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + pilgrimEmail));

            // Ensure pilgrim exists, create if not
            Piligrim pilgrim = user.getPilgrim();
            if (pilgrim == null) {
                pilgrim = new Piligrim();
                pilgrim.setUser(user);
                pilgrim = pilgrimRepo.save(pilgrim);
                user.setPilgrim(pilgrim); // Update user to maintain bidirectional relationship
                userRepo.save(user);
            }

            // Check if pilgrim already has a booking
            if (pilgrim.getRoom() != null) {
                throw new RuntimeException("Pilgrim already has an active booking with room ID: " + pilgrim.getRoom().getId());
            }

            Room room = roomRepo.findById(roomId)
                    .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
            PGHostel hostel = pgHostelRepository.findById(room.getPgHostel().getId())
                    .orElseThrow(() -> new RuntimeException("Hostel not found with ID: " + room.getPgHostel().getId()));

            // Associate room and hostel with pilgrim
            pilgrim.setRoom(room);
            pilgrim.setPgHostel(hostel);
            pilgrimRepo.save(pilgrim);
            room.setAvailableBeds(room.getAvailableBeds() - 1); // reduce 1 bed
            return new BookingResponseDTO(pilgrim.getId(), hostel, room, pilgrimEmail);
        }

        @Transactional
        public void cancelBooking(String pilgrimEmail) {
            User user = userRepo.findByEmail(pilgrimEmail)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + pilgrimEmail));
            Piligrim pilgrim = user.getPilgrim();
            if (pilgrim == null) {
                throw new RuntimeException("Pilgrim not found for user with email: " + pilgrimEmail);
            }
            if (pilgrim.getRoom() == null) {
                throw new RuntimeException("No active booking to cancel for pilgrim with email: " + pilgrimEmail);
            }
            Room room = pilgrim.getRoom();
            pilgrim.setRoom(null);
            pilgrim.setPgHostel(null);
            pilgrimRepo.save(pilgrim);

            room.setAvailableBeds(room.getAvailableBeds()+1);
            roomRepo.save(room);
        }
    @Transactional()
    public BookingResponseDTO getMyBooking(String pilgrimEmail) {
        User user = userRepo.findByEmail(pilgrimEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + pilgrimEmail));
        Piligrim pilgrim = pilgrimRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Pilgrim not found for user with email: " + pilgrimEmail));

        if (pilgrim.getRoom() == null) {
            return null; // No active booking
        }

        Room room = pilgrim.getRoom();
        PGHostel hostel = pilgrim.getPgHostel();

        return new BookingResponseDTO(
                pilgrim.getId(),
                hostel,
                room,
                pilgrimEmail
        );
    }
}
