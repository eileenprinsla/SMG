package com.smg.smg.controller;

import com.smg.smg.entity.Slot;
import com.smg.smg.entity.Booking;
import com.smg.smg.entity.User;
import com.smg.smg.entity.status;
import com.smg.smg.repository.SlotRepository;
import com.smg.smg.repository.UserRepository;
import com.smg.smg.service.AdminService;
import com.smg.smg.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SlotService slotService;

    @Autowired
    private UserRepository userRepository;

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

    // Admin Register
    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email,
                           @RequestParam String password, @RequestParam String role) {
        return adminService.register(name, email, password, "Admin");
    }

    // Admin Login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return adminService.login(email, password);
    }

    // Create Slot
    @PostMapping("/create-slot")
    public Slot createSlot(@RequestBody Slot slot) {
        return slotService.createSlot(slot);
    }

    // Cancel Slot
    @DeleteMapping("/cancel-slot/{slotId}")
    public String cancelSlot(@PathVariable int slotId) {
        return slotService.cancelSlot(slotId);
    }

    // Modify Slot
    @PutMapping("/modify-slot")
    public Slot modifySlot(@RequestBody Slot slot) {
        return slotService.modifySlot(slot);
    }

    // View Bookings
    @GetMapping("/view-bookings")
    public List<Booking> viewBookings() {
        return adminService.viewAllBookings();
    }

    // Manage Waitlist
    @GetMapping("/manage-waitlist")
    public List<Booking> manageWaitlist() {
        return adminService.manageWaitlist();
    }



}
