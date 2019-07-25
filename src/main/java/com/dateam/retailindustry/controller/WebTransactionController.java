package com.dateam.retailindustry.controller;

import com.dateam.retailindustry.dto.TransactionReqDTO;
import com.dateam.retailindustry.service.ProductService;
import com.dateam.retailindustry.service.TransactionService;
import com.dateam.retailindustry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/transactionpage")
public class WebTransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping(value = "transaction")
    public ModelAndView transactionGet() {
        ModelAndView modelAndView = new ModelAndView("transaction");

        modelAndView.addObject("alltransaction", transactionService.findAll());

        return modelAndView;
    }

    @GetMapping(value = "buyProduct")
    public ModelAndView buyProductItem() {
        ModelAndView modelAndView = new ModelAndView("productbuy");

        productService.findAll();
        userService.findAll();
        modelAndView.addObject("allproduct", productService.findAll());
        modelAndView.addObject("alluser", userService.findAll());

        return modelAndView;
    }

    @PostMapping(value = "buyProduct")
    public ModelAndView buyProduct(@Valid TransactionReqDTO transactionReqDTO) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            transactionService.create(transactionReqDTO);
            modelAndView.addObject("alltransaction", transactionService.findAll());
            modelAndView.setViewName("redirect:/productpage/listproduct");

        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("buyproduct");
        }

        return modelAndView;
    }
}