package com.dateam.retailindustry.controller;

import com.dateam.retailindustry.dto.UserReqDTO;
import com.dateam.retailindustry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/userpage")
public class WebUserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "user")
    public ModelAndView userGet() {
        ModelAndView modelAndView = new ModelAndView("user");

        modelAndView.addObject("alluser", userService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "listuser")
    public ModelAndView viewAllUser() {
        ModelAndView modelAndView = new ModelAndView("userlist");

        userService.findAll();
        modelAndView.addObject("alluser", userService.findAll());

        return modelAndView;
    }

    @GetMapping(value = "adduser")
    public ModelAndView adduser(){
        ModelAndView modelAndView = new ModelAndView("usernew");

        return modelAndView;
    }

    @PostMapping(value = "adduser")
    public ModelAndView addNewUser(@Valid UserReqDTO userReqDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("usernew");

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("usernew");

            for (FieldError error : result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equalsIgnoreCase("userName")) {
                    mav.addObject("errorName", error.getDefaultMessage());
                }

                if (error.getField().equals("userBalance")) {
                    mav.addObject(("errorBalance"), error.getDefaultMessage());
                }
            }

            mav.addObject("alluser", userReqDTO);
            return mav;
        }

        userService.create(userReqDTO);
        modelAndView.addObject("alluser", userService.findAll());
        modelAndView.setViewName("redirect:/userpage/listuser");

        return modelAndView;
    }

    @GetMapping(value = "updateuser/{id}")
    public ModelAndView updateNewUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("useredit");

        userService.findById(id);
        modelAndView.addObject("alluser", userService.findById(id));

        return modelAndView;
    }

    @PostMapping(value = "updateuser/{id}")
    public ModelAndView updateUserById(@PathVariable("id") Long id, UserReqDTO userReqDTO){
        ModelAndView modelAndView = new ModelAndView();
        userService.update(id, userReqDTO);
        modelAndView.addObject("alluser", userService.findAll());
        modelAndView.setViewName("redirect:/userpage/listuser");

        return modelAndView;
    }

    @GetMapping(value = "deleteUser/{id}")
    public RedirectView deleteUserId(@PathVariable("id") Long id) {
        userService.delete(id);

        return new RedirectView("/retailindustry/userpage/listuser");
    }
}
