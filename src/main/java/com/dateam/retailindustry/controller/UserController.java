package com.dateam.retailindustry.controller;

import com.dateam.retailindustry.dto.UserReqDTO;
import com.dateam.retailindustry.service.UserService;
import com.dateam.retailindustry.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<Response> findAll() {

        Response response = new Response();
        response.setMessage("Berhasil Menampilkan Data");
        response.setData(userService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable ("id")Long id) {

        Response response = new Response();
        response.setMessage("User " + id +" Found!");
        response.setData(userService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Valid UserReqDTO userReqDTO) {

        Response response = new Response();
        response.setMessage("Successfully created a user");
        response.setData(userService.create(userReqDTO));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable ("id")Long id, @RequestBody @Validated UserReqDTO userReqDTO) {

        Response response = new Response();
        response.setMessage("The user was successfully updated");
        response.setData(userService.update(id, userReqDTO));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable ("id") Long id) {

        Response response = new Response();
        response.setMessage("The user was successfully deleted");
        response.setData(userService.findById(id));

        userService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }
}
