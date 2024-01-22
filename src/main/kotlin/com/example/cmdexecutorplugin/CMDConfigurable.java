package com.example.cmdexecutorplugin;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CMDConfigurable implements Configurable {
    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return null;
    }

    @Override
    public @Nullable @NonNls String getHelpTopic() {
        return Configurable.super.getHelpTopic();
    }

    @Override
    public @Nullable JComponent createComponent() {
        return null;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }

    @Override
    public void reset() {
        Configurable.super.reset();
    }

    @Override
    public void disposeUIResources() {
        Configurable.super.disposeUIResources();
    }
}
