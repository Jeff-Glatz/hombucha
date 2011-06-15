package ruffkat.hombucha.measure;

import org.junit.Test;

import javax.measure.unit.Unit;

import static junit.framework.Assert.assertEquals;

public class UnitsTest {

    @Test
    public void testMolarity()
            throws Exception {
        Unit<Molarity> molarity = Units.molarity("mol/m3");

        assertEquals("[N]/[L]3", molarity.getDimension().toString());
    }
}
