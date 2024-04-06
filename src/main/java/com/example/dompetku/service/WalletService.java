package com.example.dompetku.service;

import com.example.dompetku.model.Slot;
import com.example.dompetku.model.TransactionHistory;
import com.example.dompetku.model.Wallet;
import com.example.dompetku.repository.SlotRepository;
import com.example.dompetku.repository.TransactionHistoryRepository;
import com.example.dompetku.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final SlotRepository slotRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    public WalletService(
            WalletRepository walletRepository,
            SlotRepository slotRepository,
            TransactionHistoryRepository transactionHistoryRepository) {
        this.walletRepository = walletRepository;
        this.slotRepository = slotRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }
    public Optional<Wallet> getWalletById(Long id){
        return walletRepository.findById(id);
    }

    public void addNewSlot(Long walletId, String name, int balance){
        Wallet wallet = walletRepository.getReferenceById(walletId);

        Slot slot = new Slot();
        slot.setName(name);
        slot.setBalance(balance);
        slot.setWallet(wallet);

        slotRepository.save(slot);

        wallet.getSlots().add(slot);
        walletRepository.save(wallet);
    }
    public void deposit(Long id, int amount){
        Wallet wallet = walletRepository.getReferenceById(id);

        wallet.setUnUsedBalance(wallet.getUnUsedBalance() + amount);
        walletRepository.save(wallet);
    }
    public void decrease(Long id, int amount){
        Wallet wallet = walletRepository.getReferenceById(id);

        wallet.setUnUsedBalance(wallet.getUnUsedBalance() - amount);
        walletRepository.save(wallet);
    }
    public void deleteSlot(Long slotId){
        Slot slot = slotRepository.getReferenceById(slotId);
        Wallet wallet = slot.getWallet();
//        wallet.getSlots().remove(slot);
//        walletRepository.save(wallet);

        wallet.setUnUsedBalance(wallet.getUnUsedBalance()+slot.getBalance());
        wallet.setUsedBalance(wallet.getUsedBalance()-slot.getBalance());

        slotRepository.deleteById(slotId);
    }
    public List<TransactionHistory> getAllHistory(Long id){
        return transactionHistoryRepository.getAllByWalletId(id);
    }
}
