package com.zoowayss.antigravity;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public final class AntigravityService {

    private static final String DEFAULT_ANTIGRAVITY_PATH = System.getProperty("user.home")
            + "/.antigravity/antigravity/bin/antigravity";
    private String cachedAntigravityPath = null;

    /**
     * Dynamically detect the Antigravity installation path.
     * Checks in the following order:
     * 1. ANTIGRAVITY_PATH environment variable
     * 2. Common installation locations in user's home directory
     * 3. System PATH
     * 4. Default hardcoded path
     *
     * @return The path to the Antigravity executable
     */
    private String getAntigravityPath() {
        // Return cached path if already found
        if (cachedAntigravityPath != null) {
            return cachedAntigravityPath;
        }

        // 1. Check environment variable
        String envPath = System.getenv("ANTIGRAVITY_PATH");
        if (envPath != null && isValidAntigravityPath(envPath)) {
            cachedAntigravityPath = envPath;
            return envPath;
        }

        // 2. Check common installation locations
        String userHome = System.getProperty("user.home");
        String[] commonPaths = {
                userHome + "/.antigravity/antigravity/bin/antigravity",
                userHome + "/.local/bin/antigravity",
                userHome + "/bin/antigravity",
                "/usr/local/bin/antigravity",
                "/usr/bin/antigravity",
                "/opt/antigravity/bin/antigravity"
        };

        for (String path : commonPaths) {
            if (isValidAntigravityPath(path)) {
                cachedAntigravityPath = path;
                return path;
            }
        }

        // 3. Try to find in PATH
        String pathFromEnv = findInSystemPath();
        if (pathFromEnv != null) {
            cachedAntigravityPath = pathFromEnv;
            return pathFromEnv;
        }

        // 4. Fall back to default path
        return DEFAULT_ANTIGRAVITY_PATH;
    }

    /**
     * Check if the given path is a valid Antigravity executable
     */
    private boolean isValidAntigravityPath(String path) {
        if (path == null || path.isEmpty()) {
            return false;
        }
        File file = new File(path);
        return file.exists() && file.isFile() && file.canExecute();
    }

    /**
     * Search for 'antigravity' in the system PATH
     */
    private String findInSystemPath() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String command = os.contains("win") ? "where antigravity" : "which antigravity";

            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String path = reader.readLine();
                if (path != null && !path.isEmpty() && isValidAntigravityPath(path)) {
                    return path.trim();
                }
            }
        } catch (IOException e) {
            // Silently fail, will use fallback
        }
        return null;
    }

    public void openInAntigravity(String path) {
        openInAntigravity(path, -1);
    }

    public void openInAntigravity(String path, int lineNumber) {
        String antigravityPath = getAntigravityPath();

        // Verify the path exists before attempting to execute
        if (!isValidAntigravityPath(antigravityPath)) {
            showNotification("Antigravity Error",
                    "Antigravity executable not found. Please set ANTIGRAVITY_PATH environment variable or install Antigravity.",
                    NotificationType.ERROR);
            return;
        }

        try {
            // Build the command with line number if available
            ProcessBuilder processBuilder;
            String displayPath = path;
            
            if (lineNumber > 0) {
                // Use -g argument for file:line format
                String fileWithLine = path + ":" + lineNumber;
                processBuilder = new ProcessBuilder(antigravityPath, "-g", fileWithLine);
                displayPath = fileWithLine;
            } else {
                processBuilder = new ProcessBuilder(antigravityPath, path);
            }
            
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

            showNotification("Antigravity",
                    "Opening in Antigravity: " + displayPath + "\nUsing: " + antigravityPath,
                    NotificationType.INFORMATION);

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
