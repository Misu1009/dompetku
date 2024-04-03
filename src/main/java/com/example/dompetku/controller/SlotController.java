package com.example.dompetku.controller;

import com.example.dompetku.model.Slot;
import com.example.dompetku.model.Wallet;
import com.example.dompetku.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/dompetku/slot")
public class SlotController {
    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping // sukses
    public Slot getSlot(Long id){
        return slotService.getSlotById(id).get();
    }

    @PutMapping(path = "/deposit/{slotId}") // sukses
    public void deposit(
            @PathVariable("slotId") Long slotId,
            @RequestParam int amount
    ){
        slotService.deposit(slotId, amount);
    }

    @PutMapping(path = "/decrease/{slotId}") //  sukses
    public void decrease(
            @PathVariable("slotId") Long slotId,
            @RequestParam int amount,
            @RequestParam String description
    ){
        slotService.decrease(slotId, amount, description);
    }

    @PutMapping(path = "/edit/{slotId}") // sukses
    public void edit(
            @PathVariable("slotId") Long slotId,
            @RequestParam String name
    ){
        slotService.edit(slotId, name);
    }

//    @DeleteMapping(path = "/deleteTransactionHistory/{slotId}")
//    public void deleteTransactionHistory(
//            @PathVariable("slotId") Long slotId
//    ){
//        slotService.deleteTransactionHistory(slotId);
//    }
}
