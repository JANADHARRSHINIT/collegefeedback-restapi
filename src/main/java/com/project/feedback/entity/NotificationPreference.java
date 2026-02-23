package com.project.feedback.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notification_preferences")
@Data
public class NotificationPreference {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "email_enabled")
    private boolean emailEnabled = true;
    
    @Column(name = "sms_enabled")
    private boolean smsEnabled = false;
    
    @Column(name = "push_enabled")
    private boolean pushEnabled = true;
    
    @Column(name = "in_app_enabled")
    private boolean inAppEnabled = true;
    
    @Column(name = "feedback_reminders")
    private boolean feedbackReminders = true;
    
    @Column(name = "campaign_alerts")
    private boolean campaignAlerts = true;
    
    @Column(name = "deadline_warnings")
    private boolean deadlineWarnings = true;
    
    @Column(name = "performance_summaries")
    private boolean performanceSummaries = true;
    
    @Column(name = "system_alerts")
    private boolean systemAlerts = true;
    
    @Column(name = "emergency_notifications")
    private boolean emergencyNotifications = true;
}