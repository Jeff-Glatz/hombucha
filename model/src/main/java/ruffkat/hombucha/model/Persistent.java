package ruffkat.hombucha.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
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
