package com.example.dompetku.controller;

import com.example.dompetku.model.TransactionHistory;
import com.example.dompetku.model.Wallet;
import com.example.dompetku.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/dompetku/transactionHistory")
public class TransactionHistoryController {
    private final TransactionHistoryService transactionHistoryService;

    @Autowired
    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @GetMapping
    public TransactionHistory getTransactionHistory(Long id){ // sukses
        return transactionHistoryService.getTrasactionHistoryById(id).get();
    }

    @PutMapping(path = "/edit/{transactionHistoryId}")
    public void edit(
            @PathVariable("transactionHistoryId") Long id,
            @RequestParam String description,
            @RequestParam String type
    ){
        transactionHistoryService.edit(id, description, type);
    }

}