package ruffkat.swing.action;

import org.jdesktop.swingx.action.ActionContainerFactory;

import javax.swing.Action;

/**
 * A repository of {@link Action} instances in use by an
 * application.
 * <p/>
 * Exposes methods for accessing an {@link ActionContainerFactory}
 * that can be used to build GUI components from {@link Action}
 * instances, accessing an {@link ActionConfiguration} that can be
 * used to configure an {@link Action} from an external source.
 *
 * @see Action
 * @see ActionContainerFactory
 * @see ActionConfiguration
 */
public interface ActionRepository {

    /**
     * Returns an {@link ActionContainerFactory} that can be used
     * to build GUI components from {@link Action} instances
     *
     * @return An {@link ActionContainerFactory} that can be used
     *         to build GUI components from {@link Action} instances
     */
    ActionContainerFactory getContainerFactory();

    /**
     * Returns an {@link ActionConfiguration} that can be used to
     * configure an {@link Action} from an external source.
     *
     * @return An {@link ActionConfiguration} that can be used to
     *         configure an {@link Action} from an external source.
     */
    ActionConfiguration getConfiguration();

    /**
     * Registers an {@link Action} for the supplied command
     * and uses the {@link #getConfiguration()} to configure
     * it.
     *
     * @param command The command to trigger the specified {@link Action}
     * @param action  The {@link Action} to perform when the command is invoked
     * @param <T>     The concrete {@link Action} to register
     * @return The supplied {@link Action} after configuration
     * @see #getConfiguration()
     */
    <T extends Action> T register(Object command, T action);

    /**
     * Returns the {@link Action} registered for the specified
     * command
     *
     * @param command The command use to register the requested {@link Action}
     * @param <T>     The concrete {@link Action} registered
     * @return The requested {@link Action}
     */
    <T extends Action> T getAction(Object command);

    /**
     * Returns {@code true} if the {@link Action} registered with the
     * specified command is enabled; {@code false} otherwise
     *
     * @param command The command use to register the {@link Action} of interest
     * @return {@code true} if the {@link Action} registered with the
     *         specified command is enabled; {@code false} otherwise
     */
    boolean enabled(Object command);

    /**
     * Enables the {@link Action} registered with the specified command
     *
     * @param command The command use to register the {@link Action}
     *                to be enabled
     */
    void enable(Object command);

    /**
     * Disables the {@link Action} registered with the specified command
     *
     * @param command The command use to register the {@link Action}
     *                to be disabled
     */
    void disable(Object command);

    /**
     * Toggles the current {@code enabled} state of the {@link Action}
     * registered with the specified command
     *
     * @param command The command use to register the {@link Action}
     *                whose {@code enabled} state is to be toggled
     */
    void toggle(Object command);
}
