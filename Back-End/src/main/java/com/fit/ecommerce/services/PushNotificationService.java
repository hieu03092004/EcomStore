package com.fit.ecommerce.services;

public interface PushNotificationService {
    void sendPushNotification(String expoPushToken, String title, String body, Object data);
}

