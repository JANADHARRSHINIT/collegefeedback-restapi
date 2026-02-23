package com.project.feedback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.feedback.entity.Notification;
import com.project.feedback.entity.NotificationPreference;
import com.project.feedback.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;
    
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'FACULTY', 'ADMIN')")
    public Page<Notification> getUserNotifications(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return notificationService.getUserNotifications(userId, pageable);
    }
    
    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnreadNotifications(@PathVariable Long userId) {
        return notificationService.getUnreadNotifications(userId);
    }
    
    @GetMapping("/user/{userId}/count")
    public Map<String, Long> getUnreadCount(@PathVariable Long userId) {
        return Map.of("unreadCount", notificationService.getUnreadCount(userId));
    }
    
    @PutMapping("/{id}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/user/{userId}/read-all")
    public ResponseEntity<?> markAllAsRead(@PathVariable Long userId) {
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/send")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> sendNotification(@RequestBody Map<String, Object> request) {
        Long userId = Long.valueOf(request.get("userId").toString());
        String title = request.get("title").toString();
        String message = request.get("message").toString();
        String type = request.get("type").toString();
        String priority = request.getOrDefault("priority", "NORMAL").toString();
        
        Notification notification = notificationService.createNotification(
            userId, title, message, 
            Notification.NotificationType.valueOf(type),
            Notification.NotificationPriority.valueOf(priority)
        );
        
        return ResponseEntity.ok(notification);
    }
    
    @GetMapping("/preferences/{userId}")
    public NotificationPreference getPreferences(@PathVariable Long userId) {
        return notificationService.getUserPreferences(userId);
    }
    
    @PutMapping("/preferences/{userId}")
    public NotificationPreference updatePreferences(
            @PathVariable Long userId, 
            @RequestBody NotificationPreference preferences) {
        return notificationService.updatePreferences(userId, preferences);
    }
}