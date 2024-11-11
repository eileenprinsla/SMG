package com.smg.smg.service;

import com.smg.smg.entity.Booking;
import com.smg.smg.entity.Slot;
import com.smg.smg.entity.User;
import com.smg.smg.entity.status;
import com.smg.smg.repository.UserRepository;
import com.smg.smg.repository.BookingRepository;
import com.smg.smg.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotRepository slotRepository;

    public String registerUser(String name, String email, String password, String role, String year) {
        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return "Email already exists";
        }

        // Create and save new user
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setYear(year);
        userRepository.save(newUser);

        return "User registered successfully";
    }

    // User login
    public String login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .map(user -> "Login successful")
                .orElse("Invalid credentials");
    }

    // View available slots
    public List<Slot> getAvailableSlots() {
        return slotRepository.findAll(); // Can filter by teacher or subject
    }

    // Book a slot
    public String bookSlot(int userId, int slotId, Booking bookingDetails) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Slot> slot = slotRepository.findById(slotId);

        if (user.isPresent() && slot.isPresent()) {
            Booking booking = new Booking();
            booking.setUser(user.get());
            booking.setSlot(slot.get());
            booking.setPurpose(bookingDetails.getPurpose());
            booking.setIncharge(bookingDetails.getIncharge());
            booking.setSlotStartDate(bookingDetails.getSlotStartDate());
            booking.setSlotEndDate(bookingDetails.getSlotEndDate());
            booking.setSlotStartTime(bookingDetails.getSlotStartTime());
            booking.setSlotEndTime(bookingDetails.getSlotEndTime());
            booking.setStatus(status.PENDING);
            bookingRepository.save(booking);
            return "Slot booked successfully";
        }
        return "Booking failed: User or Slot not found";
    }

    // Cancel a booking
    public String cancelBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
        return "Booking canceled successfully";
    }
}
