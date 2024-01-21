package com.example.cmdexecutorplugin;

import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.execution.runners.GenericProgramRunner;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.PluginId;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CMDRunner extends GenericProgramRunner<RunnerSettings> {

    private static final Logger LOG = Logger.getInstance(CMDExecutor.class);
    private Boolean pluginEnabled;


    @Override
    public @NotNull @NonNls String getRunnerId() {
        return "CMDExecutor";
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile runProfile) {
        return executorId.equals(CMDExecutor.id) &&
                (runProfile instanceof ApplicationConfiguration )
    }

    private boolean isTerminalPluginEnabled(){

        if (pluginEnabled != null) return pluginEnabled;
        IdeaPluginDescriptor plugin =
                PluginManager.getPlugin(PluginId.getId("org.jetbrains.plugin.terminal"));

        this.pluginEnabled = plugin != null && plugin.isEnabled();

        return pluginEnabled;
    }
}
