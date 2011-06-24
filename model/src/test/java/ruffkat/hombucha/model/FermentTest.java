package ruffkat.hombucha.model;

import org.junit.Before;
import org.junit.Test;
import ruffkat.hombucha.measure.Measurements;
import ruffkat.hombucha.measure.Range;

import javax.measure.quantity.Volume;

import static ruffkat.hombucha.measure.MeasureAssert.assertRangeEquals;

public class FermentTest {
    private Ferment ferment;

    @Before
    public void setUp()
            throws Exception {
        ferment = new Ferment();
    }

    @Test
    public void testDailyDraw()
            throws Exception {
        ferment.setVolume(Measurements.volume("4.7 l"));
        assertRangeEquals(new Range<Volume>(Measurements.volume("0.47 l"), Measurements.volume("0.94 l")),
                ferment.dailyDraw());
    }
}
