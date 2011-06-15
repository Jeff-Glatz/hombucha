package ruffkat.hombucha.measure;

import javax.measure.quantity.Quantity;
import javax.measure.unit.ProductUnit;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * This interface represents the molar concentration of a substance,
 * the system unit for this quantity is "mol/m³" (moles per cubic meter).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Molar_concentration">
 *      Wikipedia: Molar Concentration</a>
 */
public interface Molarity extends Quantity {

    /**
     * Holds the SI unit (Système International d'Unités) for this quantity.
     */
    public final static Unit<Molarity> UNIT =
            new ProductUnit<Molarity>(SI.MOLE.divide(SI.CUBIC_METRE));
}
