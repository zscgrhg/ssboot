package com.example.demo.action;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {

    @RequestMapping({"/admin/index"})
    public String pub() {
        return "admin/index";
    }

    @RequestMapping({"/admin/logout"})
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }
}
