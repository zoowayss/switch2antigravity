package com.zoowayss.antigravity;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class OpenFileInAntigravityAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (file == null || file.isDirectory()) {
            return;
        }

        AntigravityService service = ApplicationManager.getApplication()
                .getService(AntigravityService.class);
        
        // Get the current line number if editor is available
        int lineNumber = -1;
        if (editor != null) {
            int offset = editor.getCaretModel().getOffset();
            lineNumber = editor.getDocument().getLineNumber(offset) + 1; // Convert to 1-based
        }
        
        service.openInAntigravity(file.getPath(), lineNumber);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        // Only enable the action if a file (not directory) is selected
        e.getPresentation().setEnabledAndVisible(file != null && !file.isDirectory());
    }
}
