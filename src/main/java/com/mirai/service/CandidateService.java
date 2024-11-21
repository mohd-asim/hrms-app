package com.mirai.service;

import com.mirai.model.Candidate;
import com.mirai.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    private final Path resumeStorageLocation = Paths.get("resumes");

    public CandidateService() {
        try {
            Files.createDirectories(resumeStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create resume storage directory", e);
        }
    }

    public Candidate getCandidateProfile() {
        // Fetch candidate profile from repository
        Optional<Candidate> candidate = candidateRepository.findById("candidateId"); // Replace with actual logic
        return candidate.orElse(new Candidate());
    }

    public void updateCandidateProfile(Candidate candidate) {
        // Update candidate profile in repository
        candidateRepository.save(candidate);
    }

    public void storeResume(MultipartFile file) throws IOException {
        // Store resume file
        Path targetLocation = resumeStorageLocation.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetLocation);

        // Update candidate profile with resume path
        Candidate candidate = getCandidateProfile();
        candidate.setResumePath(targetLocation.toString());
        updateCandidateProfile(candidate);
    }

    // Other service methods for job search, view job listing, apply for job, track application status, and view recommendations
}
