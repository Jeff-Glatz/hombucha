package ruffkat.hombucha.model;

import javax.measure.quantity.Quantity;
import javax.measure.unit.ProductUnit;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public interface Molarity extends Quantity {

    /**
     * Holds the SI unit (Système International d'Unités) for this quantity.
     */
    public final static Unit<Molarity> UNIT = new ProductUnit<Molarity>(SI.MOLE.divide(SI.MILLI(SI.CUBIC_METRE)));
}
