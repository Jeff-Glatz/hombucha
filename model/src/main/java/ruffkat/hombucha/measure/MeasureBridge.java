package ruffkat.hombucha.measure;

import org.hibernate.search.bridge.TwoWayStringBridge;

import javax.measure.MeasureFormat;
import java.text.ParsePosition;

public class MeasureBridge
        implements TwoWayStringBridge {
    private final MeasureFormat format = MeasureFormat.getStandard();

    @Override
    public Object stringToObject(String value) {
        return value != null ? format.parse(value, new ParsePosition(0)) : null;
    }

    @Override
    public String objectToString(Object object) {
        return object != null ? format.format(object) : null;
    }
}
