package com.rmdev.rmexpensetracker.service;

import com.rmdev.rmexpensetracker.entity.Transaction;
import com.rmdev.rmexpensetracker.exception.RmBadRequestException;
import com.rmdev.rmexpensetracker.exception.RmResourceNotFoundException;

import java.util.List;

public interface TransactionService {

    List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws RmResourceNotFoundException;

    Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws RmBadRequestException;

    void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws RmBadRequestException;

    void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws RmResourceNotFoundException;

}
