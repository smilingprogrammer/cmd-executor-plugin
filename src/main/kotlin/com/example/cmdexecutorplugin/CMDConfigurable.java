package com.example.cmdexecutorplugin;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CMDConfigurable implements Configurable {

    private final Project project;
    private PatchConfigurations myState;
    private JTextArea toAddVmOptions;
    private JTextArea toRemoveVmOptions;
    private JTextArea toAddProgramsOptions;
    private JTextArea toRemoveProgramOptions;
    private JCheckBox runInTerminal;
    private SpinnerNumberModel portSpinnerModel;
    private JCheckBox findPort;

    public CMDConfigurable(Project project){

        this.project = project;

    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "ExecuteInCMD plugin";
    }

    @Override
    public @Nullable @NonNls String getHelpTopic() {
        return null;
    }

    @Override
    public @Nullable JComponent createComponent() {
        myState = ServiceManager.getService(project, PatchConfigurations.class);

        toAddVmOptions = new JTextArea(myState.toAddVmOptions);
        toRemoveVmOptions = new JTextArea(myState.toRemoveVmOptions);
        toAddProgramsOptions = new JTextArea(myState.toAddProgramOptions);
        toRemoveProgramOptions = new JTextArea(myState.toRemoveProgramOptions);

        Box mainBox = Box.createVerticalBox();

        mainBox.add(LabeledComponent.create(toAddVmOptions, "To add VM Options"));
        mainBox.add(LabeledComponent.create(toRemoveVmOptions, "To remove VM options"));
        mainBox.add(LabeledComponent.create(toAddProgramsOptions, "To add program options"));
        mainBox.add(LabeledComponent.create(toRemoveProgramOptions, "To remove program options"));

        runInTerminal = new JCheckBox("Execute Inside IntelliJ IDEA Terminal", myState.isRunInsideTerminal);

        Box bottomBox = Box.createHorizontalBox();
        portSpinnerModel = new SpinnerNumberModel(60000, 1024, 65535, 1);
        JSpinner freeSpinner = new JSpinner(portSpinnerModel);
        findPort = new JCheckBox("Try to find free port");
        findPort.setToolTipText("At start plugin will try to find free port. " +
                "This port will be available as $freePort macros in program/VM optiuons");

        bottomBox.add(findPort);
        bottomBox.add(LabeledComponent.create(freeSpinner, "Start value of check"));

        JPanel result = new JPanel(new BorderLayout());
        result.add(runInTerminal, BorderLayout.PAGE_START);
        result.add(mainBox, BorderLayout.CENTER);
        result.add(bottomBox, BorderLayout.PAGE_END);

        return result;
    }

    @Override
    public boolean isModified() {
        boolean simpleModified = !toAddVmOptions.getText().equals(myState.toAddVmOptions)
                || !toRemoveVmOptions.getText().equals(myState.toRemoveVmOptions)
                || !toAddProgramsOptions.getText().equals(myState.toAddProgramOptions)
                || !toRemoveProgramOptions.getText().equals(myState.toRemoveProgramOptions)
                || runInTerminal.isSelected() != myState.isRunInsideTerminal;

        if (simpleModified)
            return true;

        Integer currentValue = findPort.isSelected() ? portSpinnerModel.getNumber().intValue() : null;

        return !Objects.equals(currentValue, myState.startPort);
    }

    @Override
    public void apply() throws ConfigurationException {

        PatchConfigurations state = new PatchConfigurations();
        state.toAddVmOptions = toAddVmOptions.getText().trim();
        state.toRemoveVmOptions = toRemoveVmOptions.getText().trim();
        state.toAddProgramOptions = toAddProgramsOptions.getText().trim();
        state.toRemoveProgramOptions = toRemoveProgramOptions.getText().trim();
        state.isRunInsideTerminal = runInTerminal.isSelected();
        state.startPort = findPort.isSelected() ? portSpinnerModel.getNumber().intValue() : null;
        ServiceManager.getService(project, PatchConfigurations.class).loadState(state);
    }

    @Override
    public void reset() {
        toAddVmOptions.setText(myState.toAddVmOptions);
        toRemoveVmOptions.setText(myState.toRemoveVmOptions);
        toAddProgramsOptions.setText(myState.toAddProgramOptions);
        toRemoveProgramOptions.setText(myState.toRemoveProgramOptions);
        runInTerminal.setSelected(myState.isRunInsideTerminal);

        if (myState.startPort == null) {

            findPort.setSelected(false);
        } else {

            findPort.setSelected(true);
            portSpinnerModel.setValue(myState.startPort);
        }
    }

    @Override
    public void disposeUIResources() {
    }
}
