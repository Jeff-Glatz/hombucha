package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;

import javax.measure.Measure;
import javax.measure.quantity.Volume;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Ferment
        extends Persistent {

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH})
    private Recipe recipe;

    @OneToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH})
    private Mushroom mushroom;

    @OneToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH})
    private Container container;

    @Type(type = "measure")
    private Measure<Volume> volume;

    @Basic
    @Enumerated(EnumType.STRING)
    private Processing processing;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date stop;


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

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Measure<Volume> getVolume() {
        return volume;
    }

    public void setVolume(Measure<Volume> volume) {
        this.volume = volume;
    }

    public Processing getProcessing() {
        return processing;
    }

    public void setProcessing(Processing processing) {
        this.processing = processing;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date end) {
        this.stop = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ferment ferment = (Ferment) o;

        if (stop != null ? !stop.equals(ferment.stop) : ferment.stop != null) return false;
        if (processing != ferment.processing) return false;
        if (mushroom != null ? !mushroom.equals(ferment.mushroom) : ferment.mushroom != null) return false;
        if (container != null ? !container.equals(ferment.container) : ferment.container != null) return false;
        if (recipe != null ? !recipe.equals(ferment.recipe) : ferment.recipe != null) return false;
        if (start != null ? !start.equals(ferment.start) : ferment.start != null) return false;
        if (volume != null ? !volume.equals(ferment.volume) : ferment.volume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recipe != null ? recipe.hashCode() : 0;
        result = 31 * result + (mushroom != null ? mushroom.hashCode() : 0);
        result = 31 * result + (container != null ? container.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (processing != null ? processing.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (stop != null ? stop.hashCode() : 0);
        return result;
    }
}
