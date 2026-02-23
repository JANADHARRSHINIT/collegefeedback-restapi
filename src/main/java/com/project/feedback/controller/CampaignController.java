package com.project.feedback.controller;

import com.project.feedback.entity.Campaign;
import com.project.feedback.entity.CampaignResponse;
import com.project.feedback.repository.CampaignRepository;
import com.project.feedback.repository.CampaignResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/campaigns")
@CrossOrigin(origins = "*")
public class CampaignController {
    
    @Autowired
    private CampaignRepository campaignRepository;
    
    @Autowired
    private CampaignResponseRepository responseRepository;
    
    @PostMapping
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignRepository.save(campaign);
    }
    
    @GetMapping
    public List<Campaign> getActiveCampaigns() {
        return campaignRepository.findByActiveTrue();
    }
    
    @PutMapping("/{id}")
    public Campaign updateCampaign(@PathVariable Long id, @RequestBody Campaign campaign) {
        campaign.setId(id);
        return campaignRepository.save(campaign);
    }
    
    @GetMapping("/{id}/responses")
    public List<CampaignResponse> getCampaignResponses(@PathVariable Long id) {
        return responseRepository.findByCampaignId(id);
    }
}