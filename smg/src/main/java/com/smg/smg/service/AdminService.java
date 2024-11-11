package com.smg.smg.service;

import com.smg.smg.entity.Admin;
import com.smg.smg.entity.Booking;
import com.smg.smg.entity.Slot;
import com.smg.smg.entity.status;
import com.smg.smg.repository.BookingRepository;
import com.smg.smg.repository.AdminRepository;
import com.smg.smg.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotRepository slotRepository;

    // Admin Register
    public String register(String name, String email, String password, String role) {
        // Check if an admin with the same email already exists
        if (adminRepository.findByEmail(email).isPresent()) {
            return "Admin with this email already exists!";
        }

        // Create a new admin
        Admin admin = new Admin();
        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setRole(role);

        // Save the new admin
        adminRepository.save(admin);

        return "Admin registered successfully!";
    }

    // Admin Login
    public String login(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(email, password);
        return admin.isPresent() ? "Login successful" : "Invalid credentials";
    }

    // View all bookings
    public List<Booking> viewAllBookings() {
        return bookingRepository.findAll();
    }

    // Manage waitlist (filtering and sorting can be applied based on business needs)
    public List<Booking> manageWaitlist() {
        return bookingRepository.findByStatus(status.WAITLIST);
    }

}
