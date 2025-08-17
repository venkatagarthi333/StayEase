package com.stayease.hostelapp.service;

import com.stayease.hostelapp.dto.PilgrimResponseDTO;
import com.stayease.hostelapp.model.PGHostel;
import com.stayease.hostelapp.model.Piligrim;
import com.stayease.hostelapp.model.Room;
import com.stayease.hostelapp.repository.PiligrimRepository;
import com.stayease.hostelapp.repository.RoomRepository;
import com.stayease.hostelapp.repository.PGHostelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PiligrimRepository pilgrimRepo;

    @Autowired
    private PGHostelRepository pgHostelRepository;

    //add room
    public Room addRoom(Long pgHostelId, Room room) {
        PGHostel pgHostel = pgHostelRepository.findById(pgHostelId)
                .orElseThrow(() -> new RuntimeException("PG Hostel not found"));

        room.setPgHostel(pgHostel);
        return roomRepository.save(room);
    }

    //update room
    public Room updateRoom(Long roomId, Room updatedRoom) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setBedType(updatedRoom.getBedType());
        room.setCapacity(updatedRoom.getCapacity());
        room.setAvailableBeds(updatedRoom.getAvailableBeds());
        room.setPrice(updatedRoom.getPrice());

        return roomRepository.save(room);
    }

    //delete room
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    //get all rooms
    public List<Room> getRoomsByPG(Long pgHostelId) {
        PGHostel pgHostel = pgHostelRepository.findById(pgHostelId)
                .orElseThrow(() -> new RuntimeException("PG Hostel not found"));
        return pgHostel.getRooms(); // assuming PGHostel has `List<Room> rooms`
    }


    @Transactional
        public List<PilgrimResponseDTO> getPilgrimsByRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Room number cannot be null or empty");
        }
        List<Piligrim> pilgrims = pilgrimRepo.findByRoomNumber(roomNumber);
        if (pilgrims.isEmpty()) {
            throw new RuntimeException("No pilgrims found for room number: " + roomNumber);
        }
        return pilgrims.stream().map(PilgrimResponseDTO::new).toList();
    }

}
