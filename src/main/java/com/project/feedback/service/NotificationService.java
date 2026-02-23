package com.project.feedback.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.feedback.entity.Notification;
import com.project.feedback.entity.NotificationPreference;
import com.project.feedback.repository.NotificationRepository;
import com.project.feedback.repository.NotificationPreferenceRepository;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private NotificationPreferenceRepository preferenceRepository;
    
    public Notification createNotification(Long userId, String title, String message, 
                                         Notification.NotificationType type, 
                                         Notification.NotificationPriority priority) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setPriority(priority);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setScheduledAt(LocalDateTime.now());
        
        return notificationRepository.save(notification);
    }
    
    public void sendFeedbackReminder(Long userId, String courseName, LocalDateTime deadline) {
        String title = "Feedback Reminder";
        String message = String.format("Please submit your feedback for %s. Deadline: %s", 
                                     courseName, deadline.toString());
        createNotification(userId, title, message, 
                         Notification.NotificationType.FEEDBACK_REMINDER, 
                         Notification.NotificationPriority.MEDIUM);
    }
    
    public void sendCampaignAlert(Long userId, String campaignName, String details) {
        String title = "New Feedback Campaign";
        String message = String.format("New campaign '%s' is available. %s", campaignName, details);
        createNotification(userId, title, message, 
                         Notification.NotificationType.CAMPAIGN_ALERT, 
                         Notification.NotificationPriority.MEDIUM);
    }
    
    public void sendDeadlineWarning(Long userId, String courseName, LocalDateTime deadline) {
        String title = "Deadline Approaching";
        String message = String.format("Feedback deadline for %s is approaching: %s", 
                                     courseName, deadline.toString());
        createNotification(userId, title, message, 
                         Notification.NotificationType.DEADLINE_WARNING, 
                         Notification.NotificationPriority.HIGH);
    }
    
    public void sendEmergencyAlert(Long userId, String title, String message) {
        createNotification(userId, title, message, 
                         Notification.NotificationType.EMERGENCY, 
                         Notification.NotificationPriority.CRITICAL);
    }
    
    public Page<Notification> getUserNotifications(Long userId, Pageable pageable) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
    }
    
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndReadFalseOrderByCreatedAtDesc(userId);
    }
    
    public long getUnreadCount(Long userId) {
        return notificationRepository.countByUserIdAndReadFalse(userId);
    }
    
    public void markAsRead(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (notification.isPresent()) {
            Notification n = notification.get();
            n.setRead(true);
            n.setReadAt(LocalDateTime.now());
            notificationRepository.save(n);
        }
    }
    
    public void markAllAsRead(Long userId) {
        List<Notification> unreadNotifications = getUnreadNotifications(userId);
        for (Notification notification : unreadNotifications) {
            notification.setRead(true);
            notification.setReadAt(LocalDateTime.now());
        }
        notificationRepository.saveAll(unreadNotifications);
    }
    
    public NotificationPreference getUserPreferences(Long userId) {
        return preferenceRepository.findByUserId(userId)
                .orElseGet(() -> createDefaultPreferences(userId));
    }
    
    public NotificationPreference updatePreferences(Long userId, NotificationPreference preferences) {
        preferences.setUserId(userId);
        return preferenceRepository.save(preferences);
    }
    
    private NotificationPreference createDefaultPreferences(Long userId) {
        NotificationPreference preferences = new NotificationPreference();
        preferences.setUserId(userId);
        return preferenceRepository.save(preferences);
    }
}