package ruffkat.hombucha.model;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@MappedSuperclass
public abstract class Sourced
        extends Persistent {

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date received;

    @Transient
    private Source source;


    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sourced)) return false;

        Sourced sourced = (Sourced) o;

        if (received != null ? !received.equals(sourced.received) : sourced.received != null) return false;
        if (source != null ? !source.equals(sourced.source) : sourced.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = received != null ? received.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }
}
