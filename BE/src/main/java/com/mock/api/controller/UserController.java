package com.mock.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String search) {
        //TODO implement, source sample
        return "demo";
    }
}
