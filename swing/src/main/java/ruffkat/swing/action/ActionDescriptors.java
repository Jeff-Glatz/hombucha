package ruffkat.swing.action;

import javax.swing.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionDescriptors implements ActionConfiguration {
    private final Map<Object, ActionDescriptor> descriptors = new HashMap<Object, ActionDescriptor>();

    public ActionDescriptors(List<ActionDescriptor> descriptors) {
        for (ActionDescriptor descriptor : descriptors) {
            add(descriptor);
        }
    }

    public void add(ActionDescriptor descriptor) {
        descriptors.put(descriptor.getCommand(), descriptor);
    }

    public <T extends Action> T configure(Object command, T action) {
        ActionDescriptor descriptor = descriptors.get(command);
        return descriptor != null ? descriptor.configure(action) : action;
    }
}
