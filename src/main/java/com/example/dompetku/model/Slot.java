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
@Table(name = "slot")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = false)
    private int balance;

    @JsonIgnoreProperties("slot")
    @OneToMany(mappedBy = "slot")
    private List<TransactionHistory> histories;

    @JsonIgnoreProperties("slots")
    @ManyToOne
    @JoinColumn(name="wallet_id", nullable = false)
    private Wallet wallet;

    public Slot(String name, int balance, Wallet wallet) {
        this.name = name;
        this.balance = balance;
        this.histories = histories;
        this.wallet = wallet;
    }
}
