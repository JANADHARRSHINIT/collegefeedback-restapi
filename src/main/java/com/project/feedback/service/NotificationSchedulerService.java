package com.project.feedback.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.feedback.entity.Notification;

@Service
public class NotificationSchedulerService {
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private UserService userService;
    
    // Run every hour to send scheduled notifications
    @Scheduled(fixedRate = 3600000)
    public void sendScheduledNotifications() {
        // Send general reminders
        sendGeneralReminders();
    }
    
    // Send daily digest at 9 AM
    @Scheduled(cron = "0 0 9 * * *")
    public void sendDailyDigest() {
        sendDailyNotifications();
    }
    
    private void sendGeneralReminders() {
        try {
            // Send feedback reminders to all active users
            userService.getAllUsers().forEach(user -> {
                if (user.isActive()) {
                    notificationService.sendFeedbackReminder(
                        user.getId(), 
                        "General Course Feedback", 
                        LocalDateTime.now().plusDays(7)
                    );
                }
            });
        } catch (Exception e) {
            System.err.println("Error sending general reminders: " + e.getMessage());
        }
    }
    
    private void sendDailyNotifications() {
        try {
            // Send daily digest to all users
            userService.getAllUsers().forEach(user -> {
                if (user.isActive()) {
                    notificationService.createNotification(
                        user.getId(),
                        "Daily Digest",
                        "Check your pending feedback submissions",
                        Notification.NotificationType.SYSTEM_ALERT,
                        Notification.NotificationPriority.LOW
                    );
                }
            });
        } catch (Exception e) {
            System.err.println("Error sending daily digest: " + e.getMessage());
        }
    }
    
    public void sendEmergencyNotificationToAll(String title, String message) {
        userService.getAllUsers().forEach(user -> {
            notificationService.sendEmergencyAlert(user.getId(), title, message);
        });
    }
}