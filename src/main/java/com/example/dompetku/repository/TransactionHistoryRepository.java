package com.example.dompetku.repository;

import com.example.dompetku.model.Owner;
import com.example.dompetku.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>  {
    @Query("SELECT h FROM TransactionHistory h INNER JOIN h.slot s JOIN s.wallet w WHERE w.id = :walletId")
    List<TransactionHistory> getAllByWalletId(@Param("walletId") Long walletId);
}
