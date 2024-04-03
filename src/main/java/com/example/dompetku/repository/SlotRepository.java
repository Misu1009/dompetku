package com.example.dompetku.repository;

import com.example.dompetku.model.Owner;
import com.example.dompetku.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long>  {
}
