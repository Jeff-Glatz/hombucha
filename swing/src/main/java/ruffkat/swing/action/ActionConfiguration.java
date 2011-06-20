package ruffkat.swing.action;

import javax.swing.Action;

/**
 * Defines an interface for classes are capable of configuring
 * an {@link Action}
 *
 * @see Action
 */
public interface ActionConfiguration {

    /**
     * Instructs this {@link ActionConfiguration} to configure
     * the supplied {@link Action} instance, overriding previously
     * configured properties
     *
     * @param command The command identifying the supplied {@link Action}
     * @param action  The {@link Action} instance to configure
     * @param <T>     The concrete action being configured
     * @return The supplied {@link Action} after configuration
     */
    <T extends Action> T configure(Object command, T action);
}
