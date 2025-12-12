package com.zoowayss.antigravity;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public final class AntigravityService {

    private static final String ANTIGRAVITY_PATH = "/Users/zoowayss/.antigravity/antigravity/bin/antigravity";

    public void openInAntigravity(String path) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(ANTIGRAVITY_PATH, path);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            // Read output in a separate thread to avoid blocking
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Antigravity: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            showNotification("Antigravity", "Opening in Antigravity: " + path, NotificationType.INFORMATION);

        } catch (IOException e) {
            showNotification("Antigravity Error",
                    "Failed to open in Antigravity: " + e.getMessage(),
                    NotificationType.ERROR);
            e.printStackTrace();
        }
    }

    private void showNotification(String title, String content, NotificationType type) {
        Notification notification = new Notification(
                "Antigravity.Notification.Group",
                title,
                content,
                type);
        Notifications.Bus.notify(notification);
    }
}
