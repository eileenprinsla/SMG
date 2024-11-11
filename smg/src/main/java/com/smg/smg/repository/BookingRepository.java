package com.smg.smg.repository;

import com.smg.smg.entity.Booking;
import com.smg.smg.entity.status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserId(int userId);// This method will find bookings by User ID
    List<Booking> findByStatus(status status);
}
