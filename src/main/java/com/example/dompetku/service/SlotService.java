package com.example.dompetku.service;

import com.example.dompetku.model.Slot;
import com.example.dompetku.model.TransactionHistory;
import com.example.dompetku.model.Wallet;
import com.example.dompetku.repository.SlotRepository;
import com.example.dompetku.repository.TransactionHistoryRepository;
import com.example.dompetku.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Service
public class SlotService {
    private final SlotRepository slotRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public SlotService(WalletRepository walletRepository, SlotRepository slotRepository, TransactionHistoryRepository transactionHistoryRepository) {
        this.walletRepository = walletRepository;
        this.slotRepository = slotRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }
    public Optional<Slot> getSlotById(Long id){
        return slotRepository.findById(id);
    }
    public void deposit(Long id, int amount){
        Slot slot = slotRepository.getReferenceById(id);
        Wallet wallet = slot.getWallet();

        wallet.setUnUsedBalance(wallet.getUnUsedBalance() - amount);
        wallet.setUsedBalance(wallet.getUsedBalance() + amount);

        walletRepository.save(wallet);

        slot.setBalance(slot.getBalance() + amount);
        slotRepository.save(slot);

        addNewTransactionHistory(
                slot.getId(),
                amount,
                "DEPOSIT",
                "DEPOSIT"
        );
    }
    public void decrease(Long id, int amount, String description){
        Slot slot = slotRepository.getReferenceById(id);
        Wallet wallet = slot.getWallet();

        wallet.setUsedBalance(wallet.getUsedBalance() - amount);
        wallet.setUnUsedBalance(wallet.getUnUsedBalance() + amount);
        walletRepository.save(wallet);

        slot.setBalance(slot.getBalance() - amount);
        slotRepository.save(slot);

        addNewTransactionHistory(
                slot.getId(),
                amount,
                description,
                "DECREASE"
        );
    }
    public void addNewTransactionHistory(long id,
                                         int amount,
                                         String description,
                                         String type
    ){
        Slot slot = slotRepository.getReferenceById(id);

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAmount(amount);
        transactionHistory.setDescription(description);
        transactionHistory.setType(type);
        transactionHistory.setSlot(slot);
        transactionHistory.setDate(new Date());
        transactionHistoryRepository.save(transactionHistory);

        slot.getHistories().add(transactionHistory);
        slotRepository.save(slot);



    }
    public void edit(Long id, String name){
        Slot slot = slotRepository.getReferenceById(id);

        slot.setName(name);
        slotRepository.save(slot);
    }
//    public void deleteTransactionHistory(Long id){
//        transactionHistoryRepository.deleteById(id);
//    }

}
