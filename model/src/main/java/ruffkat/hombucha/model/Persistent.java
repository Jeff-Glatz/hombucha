package ruffkat.hombucha.model;

import java.io.Serializable;

public interface Persistent
        extends Serializable {
    Long getOid();
    boolean persisted();
}
