package ruffkat.swing.task;

/**
 * A simple interface for classes that act as a repository
 * of tasks.
 */
public interface TaskRepository {

    /**
     * Registers the specified {@link Task} with this
     * {@link TaskRepository}
     *
     * @param task The {@link Task} to register
     * @param <V>  The result value of the task
     * @return The {@link Task} after registration
     */
    <V> Task<V> register(Task<V> task);
}
