package ruffkat.hombucha.swing.ferments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruffkat.hombucha.model.Ferment;
import ruffkat.hombucha.store.Ferments;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

@Component
public class FermentTreeModel implements TreeModel {
    private EventListenerList listeners = new EventListenerList();
    private List<FermentTreeNode> brewing = new ArrayList<FermentTreeNode>();

    public static final Object ROOT = new Object();

    @Autowired
    private Ferments ferments;

    public FermentTreeModel() {
        this(null);
    }

    public FermentTreeModel(Ferments ferments) {
        this.ferments = ferments;
    }

    @Override
    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(TreeModelListener.class, listener);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(TreeModelListener.class, listener);
    }

    public void initialize() {
        brewing.clear();
        for (Ferment ferment : ferments.brewing()) {
            brewing.add(new FermentTreeNode(ferment));
        }
    }

    @Override
    public Object getRoot() {
        return ROOT;
    }

    @Override
    public boolean isLeaf(Object o) {
        return o != ROOT;
    }

    @Override
    public Object getChild(Object o, int i) {
        if (o == ROOT) {
            return brewing.get(i);
        }
        return null;
    }

    @Override
    public int getChildCount(Object o) {
        if (o == ROOT) {
            return brewing.size();
        }
        return 0;
    }

    @Override
    public int getIndexOfChild(Object o, Object child) {
        if (o == ROOT) {
            return brewing.indexOf(child);
        }
        return -1;
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {
    }

    public void fireTreeStructureChanged() {
        TreeModelListener[] treeModelListeners = listeners.getListeners(TreeModelListener.class);
        if (treeModelListeners.length > 0) {
            TreeModelEvent event = new TreeModelEvent(this, new TreePath(getRoot()));
            for (TreeModelListener listener : treeModelListeners) {
                listener.treeStructureChanged(event);
            }
        }
    }
}
