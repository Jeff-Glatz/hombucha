package ruffkat.swing.action;

import org.jdesktop.swingx.action.ActionContainerFactory;
import org.jdesktop.swingx.action.ActionManager;

import javax.swing.Action;

public class Actions implements ActionRepository {
    private final ActionConfiguration configuration;
    private final ActionContainerFactory containerFactory;

    public Actions(ActionConfiguration configuration, ActionContainerFactory containerFactory) {
        this.configuration = configuration;
        this.containerFactory = containerFactory;
    }

    @Override
    public ActionConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public ActionContainerFactory getContainerFactory() {
        return containerFactory;
    }

    public ActionManager getManager() {
        return containerFactory.getActionManager();
    }

    @Override
    public <T extends Action> T register(Object command, T action) {
        ActionManager manager = getManager();
        manager.addAction(command, configuration.configure(command, action));
        return action;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Action> T getAction(Object command) {
        ActionManager manager = getManager();
        return (T) manager.getAction(command);
    }

    @Override
    public boolean enabled(Object command) {
        Action action = getAction(command);
        return action.isEnabled();
    }

    @Override
    public void enable(Object command) {
        Action action = getAction(command);
        if (!action.isEnabled()) {
            action.setEnabled(true);
        }
    }

    @Override
    public void disable(Object command) {
        Action action = getAction(command);
        if (action.isEnabled()) {
            action.setEnabled(false);
        }
    }

    @Override
    public void toggle(Object command) {
        Action action = getAction(command);
        action.setEnabled(!action.isEnabled());
    }
}
