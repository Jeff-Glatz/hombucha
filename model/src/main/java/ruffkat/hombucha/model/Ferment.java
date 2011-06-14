package ruffkat.hombucha.model;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Ferment implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Transient
//    @ManyToOne(optional = true)
    private Recipe recipe;

    @Transient
//    @OneToOne(optional = true)
    private Mushroom mushroom;

    @Transient
//    @OneToOne(optional = true)
    private Reactor reactor;

    @Transient
    private Measure<Volume> volume;

    @Enumerated(EnumType.STRING)
    private Method method;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;


    public Long getId() {
        return id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Mushroom getMushroom() {
        return mushroom;
    }

    public void setMushroom(Mushroom mushroom) {
        this.mushroom = mushroom;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void setReactor(Reactor reactor) {
        this.reactor = reactor;
    }

    public Measure<Volume> getVolume() {
        return volume;
    }

    public void setVolume(Measure<Volume> volume) {
        this.volume = volume;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date start) {
        this.startTime = start;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date end) {
        this.endTime = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ferment ferment = (Ferment) o;

        if (endTime != null ? !endTime.equals(ferment.endTime) : ferment.endTime != null) return false;
        if (method != ferment.method) return false;
        if (mushroom != null ? !mushroom.equals(ferment.mushroom) : ferment.mushroom != null) return false;
        if (reactor != null ? !reactor.equals(ferment.reactor) : ferment.reactor != null) return false;
        if (recipe != null ? !recipe.equals(ferment.recipe) : ferment.recipe != null) return false;
        if (startTime != null ? !startTime.equals(ferment.startTime) : ferment.startTime != null) return false;
        if (volume != null ? !volume.equals(ferment.volume) : ferment.volume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recipe != null ? recipe.hashCode() : 0;
        result = 31 * result + (mushroom != null ? mushroom.hashCode() : 0);
        result = 31 * result + (reactor != null ? reactor.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
