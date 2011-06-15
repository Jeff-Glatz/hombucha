package ruffkat.hombucha.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public abstract class Persistent
        implements Serializable {

    @Id
    @GeneratedValue
    private Long oid;


    public Long getOid() {
        return oid;
    }

    public boolean persisted() {
        return oid != null;
    }
}
