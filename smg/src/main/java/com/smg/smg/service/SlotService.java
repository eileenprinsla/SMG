package com.smg.smg.service;

import com.smg.smg.entity.Slot;
import com.smg.smg.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    // Create a new Slot
    public Slot createSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    // Cancel a Slot
    public String cancelSlot(int slotId) {
        slotRepository.deleteById(slotId);
        return "Slot canceled successfully";
    }

    // Modify an existing Slot
    public Slot modifySlot(Slot slot) {
        return slotRepository.save(slot);
    }
}
