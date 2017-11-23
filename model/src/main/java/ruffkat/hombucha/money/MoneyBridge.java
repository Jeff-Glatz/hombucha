package ruffkat.hombucha.money;

import org.hibernate.search.bridge.TwoWayStringBridge;

public class MoneyBridge
        implements TwoWayStringBridge {
    private final MoneyFormat format = new DefaultMoneyFormat();

    @Override
    public String objectToString(Object object) {
        return object != null ? format.format((Money) object) : null;
    }

    @Override
    public Object stringToObject(String value) {
        return value != null ? format.parse(value) : null;
    }
}
