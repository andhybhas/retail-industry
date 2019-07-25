package com.dateam.retailindustry.controller;

import com.dateam.retailindustry.dto.ProductReqDTO;
import com.dateam.retailindustry.service.ProductService;
import com.dateam.retailindustry.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/productname/{productName}")
    ResponseEntity<Response> getByProductName(@PathVariable("productName")String productName) {

        Response response = new Response();
        response.setMessage("sukses");
        response.setData(productService.findByProductName(productName));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/productid/{id}")
    ResponseEntity<Response> getById (@PathVariable("id")Long id) {

        Response response = new Response();
        response.setMessage("Successfully display product data");
        response.setData(productService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }


    @GetMapping
    ResponseEntity<Response> findAll() {

        Response response = new Response();
        response.setMessage("Successfully display product data");
        response.setData(productService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Valid ProductReqDTO productReqDTO) {

        Response response = new Response();
        response.setMessage("Add product successfully");
        response.setData(productService.create(productReqDTO));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable ("id")Long id, @RequestBody @Validated ProductReqDTO productReqDTO) {

        Response response = new Response();
        response.setMessage("Successfully updated product");
        response.setData(productService.update(id, productReqDTO));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable ("id")Long id){

        Response response = new Response();
        response.setMessage("Product successfully deleted");
        response.setData(productService.findById(id));

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}