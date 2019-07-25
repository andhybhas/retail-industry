package com.dateam.retailindustry.service;

import com.dateam.retailindustry.dto.ProductRespDTO;
import com.dateam.retailindustry.dto.TransactionReqDTO;
import com.dateam.retailindustry.dto.TransactionRespDTO;
import com.dateam.retailindustry.dto.UserRespDTO;
import com.dateam.retailindustry.entity.Product;
import com.dateam.retailindustry.entity.Transactions;
import com.dateam.retailindustry.entity.User;
import com.dateam.retailindustry.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    public List<Transactions> findAll() {
        return transactionRepository.findAll();
    }

    public Transactions findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public TransactionRespDTO create (TransactionReqDTO transactionReqDTO) {

        Product product = productService.findById(transactionReqDTO.getProductId());

        int getPrice = product.getProductPrice();
        int getQtyProduct = transactionReqDTO.getTransactionQty();
        int totalTransaction = getPrice * getQtyProduct;

//        Update Stock Produk
        long stockProduct = product.getProductStock();
        if (stockProduct < 0 ) {
            throw new IllegalArgumentException("Out of stock, transactions cannot be made.");
        }

//        Update Saldo User
        User users = userService.findById(transactionReqDTO.getUserId());
        int userFirstBalance = users.getUserBalance();
        int userNewBalance = userFirstBalance - totalTransaction;
        if (userNewBalance < 0) {
            throw new IllegalArgumentException("Insufficient balance, transaction cannot be made.");
        }

        User user = userService.findById(transactionReqDTO.getUserId());
        String transactionUser = user.getUserName();

        Product products = productService.findById(transactionReqDTO.getProductId());
        String transactionProduct = products.getProductName();

        Transactions transactions = new Transactions();
        transactions.setUserId(transactionReqDTO.getUserId());
        transactions.setUserName(transactionUser);
        transactions.setProductId(transactionReqDTO.getProductId());
        transactions.setProductName(transactionProduct);
        transactions.setTransactionQty(transactionReqDTO.getTransactionQty());
        transactions.setTransactionDate(transactionReqDTO.getTransactionDate());

        product.setProductStock(stockProduct-getQtyProduct);
        transactions.setTransactionTotal(totalTransaction);
        user.setUserBalance(userNewBalance);
        transactions.setUserBalance(userNewBalance);

        transactionRepository.save(transactions);

        TransactionRespDTO transactionRespDTO = new TransactionRespDTO();
        transactionRespDTO.setTransactionId(transactions.getTransactionId());
        transactionRespDTO.setUserId(transactions.getUserId());
        transactionRespDTO.setUserName(transactions.getUserName());
        transactionRespDTO.setProductId(transactions.getProductId());
        transactionRespDTO.setProductName(transactions.getProductName());
        transactionRespDTO.setTransactionQty(getQtyProduct);
        transactionRespDTO.setTransactionDate(transactions.getTransactionDate());
        transactionRespDTO.setProductStock(stockProduct-getQtyProduct);

        transactionRespDTO.setTotalTransaction(transactions.getTransactionTotal());
        transactionRespDTO.setUserBalance(userNewBalance);

        return transactionRespDTO;

    }

}