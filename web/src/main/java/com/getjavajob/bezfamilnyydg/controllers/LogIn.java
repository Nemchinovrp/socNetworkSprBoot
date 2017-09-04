package com.getjavajob.bezfamilnyydg.controllers;

import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.getjavajob.bezfamilnyydg.common.Constants.LOG_IN_PAGE;
import static com.getjavajob.bezfamilnyydg.common.Constants.PERSONAL_PAGE;

@Controller
public class LogIn {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return LOG_IN_PAGE;
    }
}
