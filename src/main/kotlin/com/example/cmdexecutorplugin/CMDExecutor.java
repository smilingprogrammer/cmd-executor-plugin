package com.example.cmdexecutorplugin;


import com.intellij.execution.Executor;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.NlsActions;
import com.intellij.openapi.wm.ToolWindowId;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CMDExecutor extends Executor {

    public static final Icon cmdIcon = IconLoader.getIcon("");
    public static final String id = "CMDExecutor";

    @Override
    public @NotNull String getToolWindowId() {
        return ToolWindowId.DEBUG;
    }

    @Override
    public @NotNull Icon getToolWindowIcon() {
        return cmdIcon;
    }

    @Override
    public @NotNull Icon getIcon() {
        return cmdIcon;
    }

    @Override
    public Icon getDisabledIcon() {
        return cmdIcon;
    }

    @Override
    public @NlsActions.ActionDescription String getDescription() {
        return "Run program in cmd.exe instead of internal console";
    }

    @Override
    public @NotNull @NlsActions.ActionText String getActionName() {
        return "Run in cmd";
    }

    @Override
    public @NotNull @NonNls String getId() {
        return id;
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getStartActionText() {
        return "Run in cmd";
    }

    @Override
    public @NonNls String getContextActionId() {
        return "RunInCmd";
    }

    @Override
    public @NonNls String getHelpId() {
        return null;
    }
}
