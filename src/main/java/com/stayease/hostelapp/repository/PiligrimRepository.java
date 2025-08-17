package com.stayease.hostelapp.repository;

import com.stayease.hostelapp.model.Piligrim;
import com.stayease.hostelapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PiligrimRepository extends JpaRepository<Piligrim, Long> {

    Optional<Piligrim> findByUser(User user);

    @Query("SELECT p FROM Piligrim p JOIN p.room r WHERE r.roomNumber = :roomNumber")
    List<Piligrim> findByRoomNumber(String roomNumber);

}
