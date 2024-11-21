package com.mirai.controller;

import com.mirai.model.Candidate;
import com.mirai.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        // Implement logic to display the candidate dashboard
        return "dashboard";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        // Implement logic to display the candidate profile
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(Candidate candidate) {
        // Implement logic to update the candidate profile
        candidateService.updateCandidate(candidate);
        return "redirect:/profile";
    }

    @PostMapping("/resume/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file) throws IOException {
        // Implement logic to parse and store the resume
        candidateService.storeResume(file);
        return "redirect:/dashboard";
    }
}
