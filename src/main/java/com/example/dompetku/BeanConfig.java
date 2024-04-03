package com.example.dompetku;

import com.example.dompetku.model.Owner;
import com.example.dompetku.model.Slot;
import com.example.dompetku.model.TransactionHistory;
import com.example.dompetku.model.Wallet;
import com.example.dompetku.repository.OwnerRepository;
import com.example.dompetku.repository.SlotRepository;
import com.example.dompetku.repository.TransactionHistoryRepository;
import com.example.dompetku.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Configuration
public class BeanConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            OwnerRepository ownerRepository,
            WalletRepository walletRepository,
            SlotRepository slotRepository,
            TransactionHistoryRepository transactionHistoryRepository
    ){
        return args->{
            Owner owner = new Owner("Vincent", "tangan");
            ownerRepository.save(owner);
            Wallet wallet1 = new Wallet(100000, 100000, owner);
            walletRepository.save(wallet1);

            owner.setWallet(wallet1);
            ownerRepository.save(owner);

            Slot slot1 = new Slot("Makan", 150000, wallet1);
            Slot slot2 = new Slot("Kuliah", 50000, wallet1);
            slotRepository.saveAll(List.of(slot1, slot2));

            TransactionHistory transactionHistory1a = new TransactionHistory(
                    10000,
                    new GregorianCalendar(2024, Calendar.JANUARY, 10).getTime(),
                    "Beli Cilok",
                    "DECREASE",
                    slot1
            );
            TransactionHistory transactionHistory1b = new TransactionHistory(
                    200000,
                    new GregorianCalendar(2024, Calendar.MARCH, 20).getTime(),
                    "Beli Sushi Tei",
                    "DECREASE",
                    slot1
            );
            TransactionHistory transactionHistory2a = new TransactionHistory(
                    9000000,
                    new GregorianCalendar(2024, Calendar.FEBRUARY, 30).getTime(),
                    "BP3",
                    "DECREASE",
                    slot2
            );
            TransactionHistory transactionHistory2b = new TransactionHistory(
                    540000,
                    new GregorianCalendar(2024, Calendar.APRIL, 25).getTime(),
                    "SKS 1",
                    "DECREASE",
                    slot2
            );
            transactionHistoryRepository.saveAll(List.of(transactionHistory1a, transactionHistory1b, transactionHistory2a, transactionHistory2b));

//            Student kevin = new Student(
//                    "Kevin Wijaya",
//                    "kevin.w271010@gmail.com",
//                    LocalDate.of(2003, Month.AUGUST, 27)
//            );
//            Student naruto = new Student(
//                    "Uzumaki Naruto",
//                    "naruto@gmail.com",
//                    LocalDate.of(1987, Month.OCTOBER, 10)
//            );
//            repository.saveAll(
//                    List.of(kevin, naruto)
//            );
        };
    }
}
