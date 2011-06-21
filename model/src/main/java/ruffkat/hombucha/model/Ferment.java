package ruffkat.hombucha.model;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import ruffkat.hombucha.measure.MeasureBridge;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.measure.Volumetric;
import ruffkat.hombucha.util.PropertyUtils;

import javax.measure.Measure;
import javax.measure.converter.MultiplyConverter;
import javax.measure.quantity.Volume;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Indexed
public class Ferment
        implements Persistent, Viewable, Volumetric {

    @Id
    @GeneratedValue
    @DocumentId
    private Long oid;

    @Basic
    @Field
    private String name;

    @ManyToOne
    @IndexedEmbedded
    private Recipe recipe;

    @OneToOne
    @IndexedEmbedded
    private Mother mother;

    @OneToOne
    @IndexedEmbedded(depth = 1)
    private Vessel vessel;

    @Basic
    @Field
    @FieldBridge(impl = MeasureBridge.class)
    @Type(type = "measure")
    private Measure<Volume> volume;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] image;

    @Basic
    @Enumerated(EnumType.STRING)
    @Field
    private Processing processing;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Field
    private Date start;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Field
    private Date stop;


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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        if (PropertyUtils.changed(this.vessel, vessel)) {
            if (this.vessel != null) {
                this.vessel.release(this);
            }
            if (vessel != null) {
                vessel.utilize(this);
            }
            this.vessel = vessel;
        }
    }

    public Measure<Volume> getVolume() {
        return volume;
    }

    public void setVolume(Measure<Volume> volume) {
        this.volume = volume;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public Measure<Volume> dailyDraw() {
        return Measurements.convert(volume, new MultiplyConverter(new Double("0.200")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ferment)) return false;

        Ferment ferment = (Ferment) o;

        if (vessel != null ? !vessel.equals(ferment.vessel) : ferment.vessel != null) return false;
        if (mother != null ? !mother.equals(ferment.mother) : ferment.mother != null) return false;
        if (name != null ? !name.equals(ferment.name) : ferment.name != null) return false;
        if (processing != ferment.processing) return false;
        if (recipe != null ? !recipe.equals(ferment.recipe) : ferment.recipe != null) return false;
        if (start != null ? !start.equals(ferment.start) : ferment.start != null) return false;
        if (stop != null ? !stop.equals(ferment.stop) : ferment.stop != null) return false;
        if (volume != null ? !volume.equals(ferment.volume) : ferment.volume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (mother != null ? mother.hashCode() : 0);
        result = 31 * result + (vessel != null ? vessel.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (processing != null ? processing.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (stop != null ? stop.hashCode() : 0);
        return result;
    }
}
