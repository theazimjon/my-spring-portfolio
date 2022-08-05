package com.qwasar.myspringportfolio.controllers;

import com.qwasar.myspringportfolio.models.Account;
import com.qwasar.myspringportfolio.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "sign_up";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account) {
        accountService.save(account);
        return "redirect:/";
    }

}
