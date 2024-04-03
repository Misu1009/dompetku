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
@RequestMapping(path = "api/v1/dompetku/wallet")
public class WalletController { // sampe sini 4/1/2024
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping() // sukses
    public Wallet getWalletById(@RequestParam Long id){
        return walletService.getWalletById(id).get();
    }

    @PostMapping(path = "/addNewSlot/{walletId}") // sukses
    public void addNewSlot(@PathVariable("walletId") Long walletId,
                           @RequestParam String name,
                           @RequestParam int balance
    ){
        walletService.addNewSlot(walletId, name, balance);
    }

    @PutMapping(path = "/deposit/{walletId}")// sukses
    public void deposit(
            @PathVariable("walletId") Long walletId,
            @RequestParam int amount
    ){
        walletService.deposit(walletId, amount);
    }

    @PutMapping(path = "/decrease/{walletId}")// sukses
    public void decrease(
            @PathVariable("walletId") Long walletId,
            @RequestParam int amount
    ){
        walletService.decrease(walletId, amount);
    }

    @DeleteMapping(path = "/deleteSlot/{slotId}")// sukses
    public void deleteSlot(
            @PathVariable("slotId") Long slotId
    ){
        walletService.deleteSlot(slotId);
    }

    @GetMapping(path = "/getALlHistory/{walletId}")// sukses
    public List<TransactionHistory> getAllHistory(
            @PathVariable("walletId") Long walletId
    ){
        return walletService.getAllHistory(walletId);
    }

}
