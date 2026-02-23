package com.project.feedback.repository;

import com.project.feedback.entity.CampaignResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CampaignResponseRepository extends JpaRepository<CampaignResponse, Long> {
    List<CampaignResponse> findByCampaignId(Long campaignId);
}