package ruffkat.hombucha.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.net.URL;

@Entity
@DiscriminatorValue("website")
@Indexed
public class Website
        extends Source {

    @Basic
    @Field
    private URL url;

    public Website() {
        this(null, null);
    }

    public Website(String name, URL url) {
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
        if (!(o instanceof Website)) return false;
        if (!super.equals(o)) return false;

        Website website = (Website) o;

        if (url != null ? !url.equals(website.url) : website.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
