package ruffkat.hombucha.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import java.io.Serializable;

@Entity
@Inheritance
public class Friend implements Serializable, Source {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String name;

    @Basic
    public String email;

    public Friend() {
        this(null);
    }

    public Friend(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (email != null ? !email.equals(friend.email) : friend.email != null) return false;
        if (id != null ? !id.equals(friend.id) : friend.id != null) return false;
        if (name != null ? !name.equals(friend.name) : friend.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
