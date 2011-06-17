package ruffkat.hombucha.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.net.URL;

@Entity
@DiscriminatorValue("online")
@Indexed(index = "indices/online")
public class Online
        extends Source {

    @Basic
    @Field(index = Index.TOKENIZED, store = Store.YES)
    private URL url;

    public Online() {
        this(null, null);
    }

    public Online(String name, URL url) {
        super(name);
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Online)) return false;
        if (!super.equals(o)) return false;

        Online online = (Online) o;

        if (url != null ? !url.equals(online.url) : online.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
