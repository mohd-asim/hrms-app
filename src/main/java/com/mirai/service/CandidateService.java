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

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    private final Path rootLocation = Paths.get("upload-dir");

    public void updateCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public void storeResume(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        // Implement logic to parse the resume and extract information
    }
}
