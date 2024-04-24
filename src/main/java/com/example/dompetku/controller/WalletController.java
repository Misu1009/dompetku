package com.example.dompetku.controller;

import com.example.dompetku.model.Slot;
import com.example.dompetku.model.TransactionHistory;
import com.example.dompetku.model.Wallet;
import com.example.dompetku.service.WalletService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "api/v1/dompetku/wallet")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping()
    public Wallet getWalletById(@RequestParam Long id){
        return walletService.getWalletById(id).get();
    }

    @PostMapping(path = "/addNewSlot/{walletId}")
    public void addNewSlot(@PathVariable("walletId") Long walletId,
                           @RequestParam String name,
                           @RequestParam int balance
    ){
        walletService.addNewSlot(walletId, name, balance);
    }

    @PutMapping(path = "/deposit/{walletId}")
    public void deposit(
            @PathVariable("walletId") Long walletId,
            @RequestParam int amount
    ){
        walletService.deposit(walletId, amount);
    }

    @PutMapping(path = "/decrease/{walletId}")
    public void decrease(
            @PathVariable("walletId") Long walletId,
            @RequestParam int amount
    ){
        walletService.decrease(walletId, amount);
    }

    @DeleteMapping(path = "/deleteSlot/{slotId}")
    public void deleteSlot(
            @PathVariable("slotId") Long slotId
    ){
        walletService.deleteSlot(slotId);
    }

    @GetMapping(path = "/getALlHistory/{walletId}")
    public List<TransactionHistory> getAllHistory(
            @PathVariable("walletId") Long walletId
    ){
        return walletService.getAllHistory(walletId);
    }

}
