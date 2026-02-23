package com.project.feedback.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {
    
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalCampaigns", 0);
        data.put("totalFeedbacks", 0);
        return data;
    }
    
    @GetMapping("/reports")
    public Map<String, Object> getReports() {
        Map<String, Object> reports = new HashMap<>();
        reports.put("campaignCount", 0);
        return reports;
    }
    
    @GetMapping("/trends")
    public Map<String, Object> getTrends() {
        Map<String, Object> trends = new HashMap<>();
        trends.put("totalResponses", 0);
        return trends;
    }
    
    @GetMapping("/satisfaction")
    public Map<String, Object> getSatisfaction() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("averageRating", 4.2);
        return metrics;
    }
}