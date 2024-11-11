package com.smg.smg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Enumerated(EnumType.STRING)
    private status status;

    private LocalDate dateOfBooking;

    private LocalDate slotStartDate;

    private LocalDate slotEndDate;

    private LocalTime slotStartTime;

    private LocalTime slotEndTime;

    private String specification;

    private String purpose;

    private String incharge;

    @ManyToOne
    private Slot slot;

    @ManyToOne
    private User user;

}




