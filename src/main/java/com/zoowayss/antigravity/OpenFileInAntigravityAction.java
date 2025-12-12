package com.zoowayss.antigravity;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class OpenFileInAntigravityAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);

        if (file != null && !file.isDirectory()) {
            AntigravityService service = ApplicationManager.getApplication()
                    .getService(AntigravityService.class);
            service.openInAntigravity(file.getPath());
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        // Only enable the action if a file (not directory) is selected
        e.getPresentation().setEnabledAndVisible(file != null && !file.isDirectory());
    }
}
