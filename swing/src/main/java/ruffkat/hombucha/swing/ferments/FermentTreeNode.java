package ruffkat.hombucha.swing.ferments;

import ruffkat.hombucha.model.Ferment;

public class FermentTreeNode {
    private final Ferment ferment;

    public FermentTreeNode(Ferment ferment) {
        this.ferment = ferment;
    }

    public Ferment getFerment() {
        return ferment;
    }

    @Override
    public String toString() {
        return ferment.getName();
    }
}
