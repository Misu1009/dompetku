package com.example.dompetku.controller;


import com.example.dompetku.model.Owner;
import com.example.dompetku.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "api/v1/dompetku/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @GetMapping // sukses
    public Owner getOwnerByUsername(@RequestParam String username, @RequestParam String password){
        Optional<Owner> owner = ownerService.getOwnerByUsername(username);
        if(owner.isPresent() && owner.get().getPassword().equals(password)){
            return owner.get();
        }
        return new Owner();
    }

    @PostMapping(path="/saveOwner")
    public void saveOwner(@RequestBody Owner owner){
        ownerService.saveOwner(owner);
    }

    @PutMapping(path = "/edit/{ownerId}")
    public void edit(
            @PathVariable("ownerId") Long ownerId,
            @RequestParam String username,
            @RequestParam String password
    ){
        ownerService.edit(ownerId, username, password);
    }

    @PostMapping(path = "/addNewWallet/{ownerId}")
    public void addNewWallet(
            @PathVariable("ownerId") Long ownerId
    ){
        ownerService.addNewWallet(ownerId);
    }
    @PostMapping(path = "/deleteWallet/{walletId}")
    public void deleteWallet(
            @PathVariable("walletId") Long walletId
    ){
        ownerService.deleteWallet(walletId);
    }
    @PostMapping(path = "/delete/{ownerId}")
    public void deleteOwner(
            @PathVariable("ownerId") Long ownerId
    ){
        ownerService.deleteOwner(ownerId);
    }
}
