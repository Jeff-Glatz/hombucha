package ruffkat.hombucha.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Item implements Serializable, Sourced {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Transient
    private Source source;
    private Calendar received;

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

    public Calendar getReceived() {
        return received;
    }

    public void setReceived(Calendar received) {
        this.received = received;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item that = (Item) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (received != null ? !received.equals(that.received) : that.received != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;

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
