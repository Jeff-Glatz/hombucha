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
        return null;
    }

    @Override
    public boolean isLeaf(Object o) {
        return true;
    }

    @Override
    public Object getChild(Object o, int i) {
        return brewing.get(i);
    }

    @Override
    public int getChildCount(Object o) {
        return brewing.size();
    }

    @Override
    public int getIndexOfChild(Object o, Object child) {
        return brewing.indexOf(child);
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {
    }

    protected void fireTreeStructureChanged(FermentTreeNode node) {
        TreeModelListener[] treeModelListeners = listeners.getListeners(TreeModelListener.class);
        if (treeModelListeners.length > 0) {
            TreeModelEvent event = new TreeModelEvent(this, new TreePath(node));
            for (TreeModelListener listener : treeModelListeners) {
                listener.treeStructureChanged(event);
            }
        }
    }
}
