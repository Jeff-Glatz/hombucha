package ruffkat.swing.task;

import java.util.concurrent.TimeUnit;

/**
 * A NO-OP {@link BackgroundTask} that will block for the
 * configured amount of time while executing in the
 * background. It may optionally be configured to display
 * the progress of the task.
 */
public class BlockingTask extends BackgroundTask<Void, Void> {
    private final long time;
    private final TimeUnit unit;
    private final boolean showProgress;

    public BlockingTask(String description, long time, TimeUnit unit) {
        this(description, time, unit, false);
    }

    public BlockingTask(String description, long time, TimeUnit unit, boolean showProgress) {
        super(description);
        this.time = time;
        this.unit = unit;
        this.showProgress = showProgress;
    }

    protected Void doInBackground()
            throws Exception {
        long milliseconds = unit.toMillis(time);
        if (showProgress) {
            for (int progress = 1; progress <= 100; progress++) {
                Thread.sleep(milliseconds);
                setProgress(progress);
            }
        } else {
            Thread.sleep(milliseconds);
        }
        return null;
    }
}
