package com.example.dompetku.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = false)
    private int unUsedBalance;

    @Column(nullable = false, unique = false)
    private int usedBalance;

    @JsonIgnoreProperties("wallet")
    @OneToMany(mappedBy = "wallet")
    private List<Slot> slots;

    @JsonIgnoreProperties("wallet")
    @OneToOne(mappedBy = "wallet")
    private Owner owner;

    public Wallet(int unUsedBalance, int usedBalance, Owner owner) {
        this.unUsedBalance = unUsedBalance;
        this.usedBalance = usedBalance;
        this.owner = owner;
    }
}
