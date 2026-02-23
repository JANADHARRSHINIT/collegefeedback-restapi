package com.project.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.feedback.entity.Audit;
import com.project.feedback.repository.AuditRepository;
import java.util.List;

@RestController
@RequestMapping("/api/audits")
@CrossOrigin(origins = "*")
public class AuditController {
    
    @Autowired
    private AuditRepository auditRepository;
    
    @GetMapping
    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }
    
    @GetMapping("/entity/{entityType}")
    public List<Audit> getAuditsByEntity(@PathVariable String entityType) {
        return auditRepository.findByEntityTypeOrderByTimestampDesc(entityType);
    }
}