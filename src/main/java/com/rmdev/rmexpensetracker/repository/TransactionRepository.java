package com.rmdev.rmexpensetracker.repository;

import com.rmdev.rmexpensetracker.entity.Transaction;
import com.rmdev.rmexpensetracker.exception.RmBadRequestException;
import com.rmdev.rmexpensetracker.exception.RmResourceNotFoundException;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll(Integer userId, Integer categoryId);

    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws RmResourceNotFoundException;

    Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws RmBadRequestException;

    void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws RmBadRequestException;

    void removeById(Integer userId, Integer categoryId, Integer transactionId) throws RmResourceNotFoundException;
}
