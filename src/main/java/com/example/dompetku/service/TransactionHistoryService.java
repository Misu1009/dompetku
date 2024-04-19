package com.example.dompetku.service;

import com.example.dompetku.model.TransactionHistory;
import com.example.dompetku.repository.SlotRepository;
import com.example.dompetku.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final SlotRepository slotRepository;

    @Autowired
    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository, SlotRepository slotRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.slotRepository = slotRepository;
    }
    public Optional<TransactionHistory> getTrasactionHistoryById(Long id){
        return transactionHistoryRepository.findById(id);
    }
    public void edit(Long id, String description, String type){
        TransactionHistory transactionHistory = transactionHistoryRepository.getReferenceById(id);
        transactionHistory.setDescription(description);
        transactionHistory.setType(type);

        transactionHistoryRepository.save(transactionHistory);
    }

    public List<TransactionHistory> getALlTransactionHistoryByType(String type){
        return transactionHistoryRepository.getAllTransactionHistoryByType(type);
    }

}
