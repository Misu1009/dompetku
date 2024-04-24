package com.example.dompetku.controller;

import com.example.dompetku.model.Slot;
import com.example.dompetku.model.Wallet;
import com.example.dompetku.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "api/v1/dompetku/slot")
public class SlotController {
    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping
    public Slot getSlot(@RequestParam Long id){
        return slotService.getSlotById(id).get();
    }

    @PutMapping(path = "/deposit/{slotId}")
    public void deposit(
            @PathVariable("slotId") Long slotId,
            @RequestParam int amount
    ){
        slotService.deposit(slotId, amount);
    }

    @PutMapping(path = "/decrease/{slotId}")
    public void decrease(
            @PathVariable("slotId") Long slotId,
            @RequestParam int amount,
            @RequestParam String description
    ){
        slotService.decrease(slotId, amount, description);
    }

    @PutMapping(path = "/edit/{slotId}")
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
