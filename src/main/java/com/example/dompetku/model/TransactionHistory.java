package com.example.dompetku.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactionhistory")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = false)
    private int amount;

    @Column(nullable = false, unique = false)
    private Date date;

    @Column(nullable = false, unique = false)
    private String description;

    @Column(nullable = false, unique = false)
    private String type;

    @JsonIgnoreProperties("histories")
    @ManyToOne
    @JoinColumn(name="slot_id", nullable = false)
    private Slot slot;

    public TransactionHistory(int amount, Date date, String description, String type, Slot slot) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
        this.slot = slot;
    }
}
