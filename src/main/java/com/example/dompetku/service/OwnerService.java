package com.example.dompetku.service;


import com.example.dompetku.model.Owner;
import com.example.dompetku.model.Wallet;
import com.example.dompetku.repository.OwnerRepository;
import com.example.dompetku.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, WalletRepository walletRepository){
        this.ownerRepository = ownerRepository;
        this.walletRepository = walletRepository;
    }

    public Optional<Owner> getOwnerByUsername(String username){
         return ownerRepository.findByUsername(username);
    }

    public void saveOwner(Owner owner){
        Optional<Owner> ownerOptional = ownerRepository.findByUsername(owner.getUsername());
        if(ownerOptional.isPresent()){
            throw new IllegalStateException("username taken");
        }
        ownerRepository.save(owner);
    }

    @Transactional
    public void edit(Long id, String username, String password){
        Owner owner = ownerRepository.getReferenceById(id);
        if(!username.isEmpty()){
            owner.setUsername(username);
        }
        if(!password.isEmpty()){
            owner.setPassword(password);
        }
        ownerRepository.save(owner);
    }

    public void addNewWallet(Long id){
        Owner owner = ownerRepository.getReferenceById(id);

        Wallet wallet = new Wallet();
        wallet.setOwner(owner);
        wallet.setUnUsedBalance(0);
        wallet.setUsedBalance(0);

        walletRepository.save(wallet);
        owner.setWallet(wallet);
        ownerRepository.save(owner);

    }

    public void deleteWallet(Long walletId){
        Wallet wallet = walletRepository.getReferenceById(walletId);
        Owner owner = wallet.getOwner();
        owner.setWallet(null);

        ownerRepository.save(owner);
        wallet.setOwner(null);
        walletRepository.deleteById(walletId);
    }
    public void deleteOwner(Long ownerId){
        Owner owner = ownerRepository.getReferenceById(ownerId);
        Wallet wallet = owner.getWallet();
        wallet.setOwner(null);
        walletRepository.save(wallet);

        ownerRepository.deleteById(ownerId);
    }
}
