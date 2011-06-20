package ruffkat.swing.action;

import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 * Describes the configurable properties of an {@link Action}
 *
 * @see Action
 * @see ActionDescriptors
 */
public class ActionDescriptor {
    private String command;
    private String name;
    private Integer mnemonic;
    private String accelerator;
    private String smallIcon;
    private String toolTip;
    private Boolean enabled;

    public ActionDescriptor() {
        this(null, null, null, null, null, null);
    }

    public ActionDescriptor(String command, String name) {
        this(command, name, null, null, null, null);
    }

    public ActionDescriptor(String command, String name, Integer mnemonic) {
        this(command, name, mnemonic, null, null, null);
    }

    public ActionDescriptor(String command, String name, Integer mnemonic, String accelerator) {
        this(command, name, mnemonic, accelerator, null, null);
    }

    public ActionDescriptor(String command, String name, Integer mnemonic, String accelerator, String smallIcon) {
        this(command, name, mnemonic, accelerator, smallIcon, null);
    }

    public ActionDescriptor(String command, String name, Integer mnemonic, String accelerator, String smallIcon, String toolTip) {
        this.command = command;
        this.name = name;
        this.mnemonic = mnemonic;
        this.accelerator = accelerator;
        this.smallIcon = smallIcon;
        this.toolTip = toolTip;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(Integer mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getAccelerator() {
        return accelerator;
    }

    public void setAccelerator(String accelerator) {
        this.accelerator = accelerator;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Instructs this {@link ActionDescriptor} to configure
     * the supplied {@link Action} instance, overriding previously
     * configured properties
     *
     * @param action The {@link Action} instance to configure
     * @param <T>    The concrete action being configured
     * @return The supplied {@link Action} after configuration
     */
    public <T extends Action> T configure(T action) {
        if (command != null) {
            action.putValue(Action.ACTION_COMMAND_KEY, command);
        }
        if (name != null) {
            action.putValue(Action.NAME, name);
        }
        if (mnemonic != null) {
            action.putValue(Action.MNEMONIC_KEY, mnemonic);
        }
        if (accelerator != null) {
            action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        }
        if (smallIcon != null) {
            action.putValue(Action.SMALL_ICON, UIManager.getIcon(smallIcon));
        }
        if (toolTip != null) {
            action.putValue(Action.SHORT_DESCRIPTION, toolTip);
        }
        if (enabled != null) {
            action.setEnabled(enabled);
        }
        return action;
    }

    public static ActionDescriptor disabled(ActionDescriptor descriptor) {
        descriptor.setEnabled(false);
        return descriptor;
    }
}
