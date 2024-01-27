package com.mock.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class DefaultController {

    @GetMapping(value = {"/", "/index"})
    public String home(Model model) {
        model.addAttribute("text", "hello");
        // html view
        return "index";
    }

    @GetMapping("/healthCheck")
    public String healthCheck() {
        // api
        return "{'message': 'API Data'}";
    }

}
