package ruffkat.hombucha.store;

import ruffkat.hombucha.model.Ferment;

import java.util.Set;

public interface Ferments
        extends Repository<Ferment> {
    Ferment create();
    Set<Ferment> brewing();
}
