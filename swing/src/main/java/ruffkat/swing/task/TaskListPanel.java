package ruffkat.swing.task;

import org.jdesktop.swingx.JXBusyLabel;
import ruffkat.swing.action.ActionConfiguration;
import ruffkat.swing.ui.UserInterface;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ToolTipManager;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * The {@link TaskListPanel} will be of most interest for integration
 * into an application as it provides a single point of access to all
 * of the task operations.
 * <p/>
 * For predictable results, {@link #register(Task) register} the {@link Task}
 * <b>before</b> executing it
 *
 * @see Task
 * @see TaskRepository
 */
public class TaskListPanel extends JPanel implements TaskRepository, ListDataListener, ItemListener {
    private final TaskListModel workers;
    private final JXBusyLabel busyLabel;
    private final JPanel comboBoxPanel;
    private final JProgressBar progress;
    private final JPanel progressPanel;
    private final StateChangeHandler listener;

    public TaskListPanel(UserInterface ui, TaskListModel workers, ActionConfiguration actionConfiguration) {
        this.workers = workers;
        this.workers.addListDataListener(this);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder());
        setOpaque(false);

        busyLabel = new JXBusyLabel(new Dimension(16, 16));
        busyLabel.setOpaque(false);
        busyLabel.getBusyPainter().setHighlightColor(ui.color("TaskListPanel.BusyLabel.highlightColor"));

        TaskEditor editor = new TaskEditor(ui, actionConfiguration);
        editor.setOpaque(false);
        editor.setBackground(getBackground());

        JComboBox comboBox = new JComboBox(workers);
        comboBox.setOpaque(false);
        comboBox.setBorder(BorderFactory.createEmptyBorder());
        comboBox.setEditable(true);
        comboBox.setFocusable(false);
        comboBox.setToolTipText(ui.string("TaskListPanel.toolTipText"));
        comboBox.setRenderer(new TaskRenderer(ui, actionConfiguration));
        comboBox.setEditor(editor);
        comboBox.addItemListener(this);
        ToolTipManager.sharedInstance().registerComponent(comboBox);

        comboBoxPanel = new JPanel();
        comboBoxPanel.setOpaque(false);
        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.X_AXIS));
        comboBoxPanel.add(Box.createHorizontalStrut(5));
        comboBoxPanel.add(comboBox);

        progress = new JProgressBar();
        progress.setOpaque(false);
        progress.setStringPainted(false);

        progressPanel = new JPanel();
        progressPanel.setOpaque(false);
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.X_AXIS));
        progressPanel.add(Box.createHorizontalStrut(5));
        progressPanel.add(progress);

        listener = new StateChangeHandler() {
            protected void onTaskProgress(Task<?> task) {
                updateProgress(task);
            }
        };

        add(busyLabel);
        add(progressPanel);
        add(comboBoxPanel);
    }

    public <V> Task<V> register(Task<V> task) {
        return workers.register(task);
    }

    public void itemStateChanged(ItemEvent e) {
        Task<?> task = (Task<?>) e.getItem();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            updateProgress(task);
            task.addPropertyChangeListener(listener);
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            task.removePropertyChangeListener(listener);
            updateProgress(task);
        }
    }

    public void intervalAdded(ListDataEvent e) {
        if (!busyLabel.isBusy()) {
            busyLabel.setBusy(true);
        }
        if (!comboBoxPanel.isVisible()) {
            comboBoxPanel.setVisible(true);
        }
    }

    public void intervalRemoved(ListDataEvent e) {
        if (workers.getSize() == 0) {
            comboBoxPanel.setVisible(false);
            progressPanel.setVisible(false);
            busyLabel.setBusy(false);
        }
    }

    public void contentsChanged(ListDataEvent e) {
        if (workers.getSize() == 0) {
            comboBoxPanel.setVisible(false);
            progressPanel.setVisible(false);
            busyLabel.setBusy(false);
        }
    }

    private void updateProgress(Task<?> task) {
        progress.setValue(task.getProgress());
        if (task.getProgress() > 0) {
            if (!progressPanel.isVisible()) {
                progressPanel.setVisible(true);
            }
        } else {
            if (progressPanel.isVisible()) {
                progressPanel.setVisible(false);
            }
        }
    }
}
