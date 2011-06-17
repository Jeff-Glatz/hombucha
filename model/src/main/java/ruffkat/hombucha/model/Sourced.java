package ruffkat.hombucha.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class Sourced
        implements Persistent, Viewable {

    @Id
    @GeneratedValue
    @DocumentId
    private Long oid;

    @Basic
    @Field(index = Index.TOKENIZED, store = Store.YES)
    private String name;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    private Date received;

    @ManyToOne
    private Source source;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] image;


    public Long getOid() {
        return oid;
    }

    public boolean persisted() {
        return oid != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sourced)) return false;

        Sourced sourced = (Sourced) o;

        if (name != null ? !name.equals(sourced.name) : sourced.name != null) return false;
        if (received != null ? !received.equals(sourced.received) : sourced.received != null) return false;
        if (source != null ? !source.equals(sourced.source) : sourced.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (received != null ? received.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sourced{" +
                "oid=" + oid +
                ", name='" + name + '\'' +
                '}';
    }
}
