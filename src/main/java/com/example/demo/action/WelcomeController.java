package com.example.demo.action;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    @RequestMapping({"/", "/index"})
    public String wel() {
        return "index";
    }

    @RequestMapping({"/public", "/public/index"})
    public String pub() {
        return "public/index";
    }
}
