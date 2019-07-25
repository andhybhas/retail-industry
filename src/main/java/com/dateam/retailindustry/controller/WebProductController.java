package com.dateam.retailindustry.controller;

import com.dateam.retailindustry.dto.ProductReqDTO;
import com.dateam.retailindustry.service.ProductService;
import com.dateam.retailindustry.service.TransactionService;
import com.dateam.retailindustry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/productpage")
public class WebProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @GetMapping(value = "product")
    public ModelAndView productGet() {
        ModelAndView modelAndView = new ModelAndView("product");

        modelAndView.addObject("allproduct", productService.findAll());

        return modelAndView;
    }

    @GetMapping(value = "listproduct")
    public ModelAndView viewAllProduct() {
        ModelAndView modelAndView = new ModelAndView("productlist");

        productService.findAll();
        modelAndView.addObject("allproduct", productService.findAll());

        return modelAndView;
    }

    @GetMapping(value = "addproduct")
    public ModelAndView addproduct() {
        ModelAndView modelAndView = new ModelAndView("productnew");

        return modelAndView;
    }

    @PostMapping(value = "addproduct")
    public ModelAndView addNewProduct(@Valid ProductReqDTO productReqDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("productnew");

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("productnew");

            for (FieldError error : result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equalsIgnoreCase("productName")) {
                    mav.addObject("errorName", error.getDefaultMessage());
                }

                if (error.getField().equals("productPrice")) {
                    mav.addObject("errorPrice", error.getDefaultMessage());
                }

                if (error.getField().equals("productStock")) {
                    mav.addObject("errorStock", error.getDefaultMessage());
                }
            }

            mav.addObject("allproduct", productReqDTO);
            return mav;
        }

        productService.create(productReqDTO);
        modelAndView.addObject("allproduct", productService.findAll());
        modelAndView.setViewName("redirect:/productpage/listproduct");

        return modelAndView;
    }

    @GetMapping(value = "updateproduct/{id}")
    public ModelAndView updateNewProduct(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("productedit");

        productService.findById(id);
        modelAndView.addObject("allproduct", productService.findById(id));

        return modelAndView;
    }

    @PostMapping(value = "updateproduct/{id}")
    public ModelAndView updateProductById(@PathVariable("id") Long id, ProductReqDTO productReqDTO){
        ModelAndView modelAndView = new ModelAndView();
        productService.update(id, productReqDTO);
        modelAndView.addObject("allproduct", productService.findAll());
        modelAndView.setViewName("redirect:/productpage/listproduct");

        return modelAndView;
    }

    @GetMapping(value = "deleteProduct/{id}")
    public RedirectView deleteProductItem(@PathVariable("id") Long id) {
        productService.delete(id);

        return new RedirectView("/retailindustry/productpage/listproduct");
    }
}
