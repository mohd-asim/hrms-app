package com.yourcompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CandidateController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Add necessary attributes to the model for the dashboard view
        return "dashboard";
    }
}