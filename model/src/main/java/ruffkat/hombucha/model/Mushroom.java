package ruffkat.hombucha.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Mushroom
        implements Serializable, Sourced {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String name;

    @Transient
    private Source source;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date received;

    @ManyToOne
    private Mushroom mother;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    public Mushroom getMother() {
        return mother;
    }

    public void setMother(Mushroom mother) {
        this.mother = mother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mushroom mushroom = (Mushroom) o;

        if (name != null ? !name.equals(mushroom.name) : mushroom.name != null) return false;
        if (received != null ? !received.equals(mushroom.received) : mushroom.received != null) return false;
        if (source != null ? !source.equals(mushroom.source) : mushroom.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (received != null ? received.hashCode() : 0);
        return result;
    }
}
