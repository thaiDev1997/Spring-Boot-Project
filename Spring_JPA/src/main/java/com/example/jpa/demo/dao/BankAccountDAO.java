package com.example.jpa.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.example.jpa.demo.entity.BankAccount;
import com.example.jpa.demo.exception.BankTransactionException;
import com.example.jpa.demo.dto.BankAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class BankAccountDAO {

    @Autowired
    private EntityManager entityManager;


    public BankAccountDAO() {
    }

    public BankAccount findById(Long id) {
        return this.entityManager.find(BankAccount.class, id);
    }

    public List<BankAccountDTO> listBankAccountInfo() {
        String sql = "Select new " + BankAccountDTO.class.getName() //
                + "(e.id,e.fullName,e.balance) " //
                + " from " + BankAccount.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, BankAccountDTO.class);
        return query.getResultList();
    }

    // MANDATORY: Bắt buộc phải có Transaction đã được tạo trước đó.
    @Transactional(propagation = Propagation.MANDATORY )
    public void addAmount(Long id, double amount) throws BankTransactionException {
        BankAccount account = this.findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }

    // Không bắt ngoại lệ BankTransactionException trong phương thức này.
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException {

        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
}
