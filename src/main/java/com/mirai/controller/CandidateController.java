package com.mirai.controller;

import com.mirai.model.Candidate;
import com.mirai.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Add necessary attributes to the model
        return "dashboard";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        // Retrieve candidate profile from the service
        Candidate candidate = candidateService.getCandidateProfile();
        model.addAttribute("candidate", candidate);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Candidate candidate) {
        // Update candidate profile
        candidateService.updateCandidateProfile(candidate);
        return "redirect:/candidate/profile";
    }

    @PostMapping("/uploadResume")
    public String uploadResume(@ModelAttribute("file") MultipartFile file) throws IOException {
        // Parse and store resume
        candidateService.storeResume(file);
        return "redirect:/candidate/profile";
    }

    // Other methods for job search, view job listing, apply for job, track application status, and view recommendations
}
