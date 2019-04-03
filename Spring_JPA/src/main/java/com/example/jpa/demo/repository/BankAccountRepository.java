package com.example.jpa.demo.repository;

import com.example.jpa.demo.entity.BankAccount;
import com.example.jpa.demo.dto.BankAccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("SELECT ba FROM BankAccount ba WHERE ba.fullName = (:fullName)")
    List<BankAccount> findBankAccountByFullName(@Param("fullName") String fullName);

    @Query("SELECT new com.example.jpa.demo.dto.BankAccountDTO(ba.id, ba.fullName, ba.balance) FROM BankAccount ba WHERE ba.fullName = (:fullName)")
    List<BankAccountDTO> findBankAccountDTOByFullName(@Param("fullName") String fullName);
}
