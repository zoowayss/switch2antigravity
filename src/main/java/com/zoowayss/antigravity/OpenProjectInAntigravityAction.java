package com.zoowayss.antigravity;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class OpenProjectInAntigravityAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();

        if (project != null) {
            String basePath = project.getBasePath();
            if (basePath != null) {
                AntigravityService service = ApplicationManager.getApplication()
                        .getService(AntigravityService.class);
                service.openInAntigravity(basePath);
            }
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        // Only enable the action if a project is open
        e.getPresentation().setEnabledAndVisible(project != null);
    }
}
