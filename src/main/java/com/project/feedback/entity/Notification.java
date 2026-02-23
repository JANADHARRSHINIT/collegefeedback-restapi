package com.project.feedback.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notifications")
@Data
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType type;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private NotificationPriority priority;
    
    @Column(name = "is_read")
    private boolean read = false;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "read_at")
    private LocalDateTime readAt;
    
    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;
    
    @Column(name = "sent_at")
    private LocalDateTime sentAt;
    
    @Column(name = "delivery_status")
    private String deliveryStatus;
    
    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;
    
    public enum NotificationType {
        FEEDBACK_REMINDER, CAMPAIGN_ALERT, DEADLINE_WARNING, 
        FEEDBACK_RECEIVED, PERFORMANCE_SUMMARY, SYSTEM_ALERT, EMERGENCY
    }
    
    public enum NotificationPriority {
    CRITICAL,
    HIGH,
    MEDIUM,
    LOW
}
}