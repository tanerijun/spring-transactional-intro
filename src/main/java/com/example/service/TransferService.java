package com.example.service;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long senderId, long receiverId, double amount) {
        Account sender = accountRepository.findAccountById(senderId);
        Account receiver = accountRepository.findAccountById(receiverId);

        double senderNewMoney = sender.getMoney() - amount;
        double receiverNewMoney = receiver.getMoney() + amount;

        accountRepository.changeMoneyAmount(senderId, senderNewMoney);
        accountRepository.changeMoneyAmount(receiverId, receiverNewMoney);

        throw new RuntimeException("Problem executing transaction.");
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
