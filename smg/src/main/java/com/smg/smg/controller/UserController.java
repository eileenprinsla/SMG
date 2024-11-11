package com.smg.smg.controller;

import com.smg.smg.entity.Slot;
import com.smg.smg.entity.Booking;
import com.smg.smg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String role,
                           @RequestParam String year) {
        return userService.registerUser(name, email, password, role, year);
    }

    // User Login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }

    // View available slots
    @GetMapping("/available-slots")
    public List<Slot> viewAvailableSlots() {
        return userService.getAvailableSlots();
    }

    // Book a slot
    @PostMapping("/book-slot")
    public String bookSlot(@RequestParam int userId, @RequestParam int slotId, @RequestBody Booking bookingDetails) {
        return userService.bookSlot(userId, slotId, bookingDetails);
    }

    // Cancel a booking
    @DeleteMapping("/cancel-booking/{bookingId}")
    public String cancelBooking(@PathVariable int bookingId) {
        return userService.cancelBooking(bookingId);
    }




}
