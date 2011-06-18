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
@DiscriminatorValue("local")
@Indexed
public class Local
        extends Source {

    @Basic
    @Field
    private URL url;

    @Basic
    @Field
    private String phone;

    @Basic
    @Field
    private String email;

    @Basic
    @Field
    private String directions;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Local)) return false;
        if (!super.equals(o)) return false;

        Local local = (Local) o;

        if (directions != null ? !directions.equals(local.directions) : local.directions != null) return false;
        if (email != null ? !email.equals(local.email) : local.email != null) return false;
        if (phone != null ? !phone.equals(local.phone) : local.phone != null) return false;
        if (url != null ? !url.equals(local.url) : local.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (directions != null ? directions.hashCode() : 0);
        return result;
    }
}
