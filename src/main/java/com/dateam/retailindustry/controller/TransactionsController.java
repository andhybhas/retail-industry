package com.dateam.retailindustry.controller;

import com.dateam.retailindustry.dto.TransactionReqDTO;
import com.dateam.retailindustry.service.TransactionService;
import com.dateam.retailindustry.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "transaction")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    ResponseEntity<Response> findAll() {

        Response response = new Response();
        response.setMessage("Successfully displayed transaction");
        response.setData(transactionService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable ("id") Long id) {

        Response response = new Response();
        response.setMessage("Successfully displays selected transactions");
        response.setData(transactionService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> addTransaction(@RequestBody @Valid TransactionReqDTO transactionReqDTO) {

        Response response = new Response();
        response.setMessage("Transaction was successful");
        response.setData(transactionService.create(transactionReqDTO));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}


