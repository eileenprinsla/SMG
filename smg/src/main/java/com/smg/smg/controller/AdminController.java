package com.smg.smg.controller;

import com.smg.smg.entity.Slot;
import com.smg.smg.entity.Booking;
import com.smg.smg.entity.User;
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