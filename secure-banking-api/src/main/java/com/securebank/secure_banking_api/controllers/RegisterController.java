package com.securebank.secure_banking_api.controllers;

import com.securebank.secure_banking_api.service.SecureBankingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.securebank.secure_banking_api.entity.Customer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    private SecureBankingService secureBankingService;

    // inject the banking service in via constructor so we can use it to add new customers

    public RegisterController(SecureBankingService secureBankingService) {
        this.secureBankingService = secureBankingService;
    }

    @GetMapping("/register")
    public String register(Model model) {

        // the html form is bound by a customer object which it expects before the form is shown this is what
        // the model.addAttribute does here -> this also gives access to more thymeleaf binding features

        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {

        // ill have to fix this later because the success and failure messages aren't appearing

        try {
            secureBankingService.addCustomer(customer);
            redirectAttributes.addFlashAttribute("success", "Registration successful! You can" +
                    " now login.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error registering user: " +
                    e.getMessage());
            return "redirect:/register";
        }
    }
}
