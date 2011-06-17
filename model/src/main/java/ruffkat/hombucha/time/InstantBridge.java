package ruffkat.hombucha.time;

import org.apache.lucene.document.DateTools;
import org.hibernate.search.SearchException;
import org.hibernate.search.bridge.TwoWayStringBridge;

import javax.time.Instant;
import java.text.ParseException;

public class InstantBridge implements TwoWayStringBridge {

    @Override
    public Object stringToObject(String value) {
        try {
            return value != null ?
                    DateTools.stringToDate(value) :
                    null;
        } catch (ParseException e) {
            throw new SearchException("Unable to parse into date: " + value, e);
        }
    }

    @Override
    public String objectToString(Object object) {
        Instant instant = (Instant) object;
        return object != null ?
                DateTools.timeToString(instant.toEpochMillisLong(),
                        DateTools.Resolution.MILLISECOND) :
                null;
    }
}
