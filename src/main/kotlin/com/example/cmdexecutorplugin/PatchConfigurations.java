package com.example.cmdexecutorplugin;

import com.intellij.openapi.components.PersistentStateComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PatchConfigurations implements PersistentStateComponent<PatchConfigurations> {

    public String toAddVmOptions = "";
    public String toRemoveVmOptions = "";
    public String toAddProgramOptions = "";
    public String toRemoveProgramOptions = "";
    public boolean isRunInsideTerminal = false;
    public Integer startPort;

    @Override
    public @Nullable PatchConfigurations getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PatchConfigurations patchConfigurations) {

        toAddVmOptions = patchConfigurations.toAddVmOptions;
        toRemoveVmOptions = patchConfigurations.toRemoveVmOptions;
        toAddProgramOptions = patchConfigurations.toAddProgramOptions;
        toRemoveProgramOptions = patchConfigurations.toRemoveProgramOptions;
        isRunInsideTerminal = patchConfigurations.isRunInsideTerminal;
        startPort = patchConfigurations.startPort;
    }
}
