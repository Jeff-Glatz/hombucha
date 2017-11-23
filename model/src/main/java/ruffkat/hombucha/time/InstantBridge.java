package ruffkat.hombucha.time;

import org.apache.lucene.document.DateTools;
import org.hibernate.search.exception.SearchException;
import org.hibernate.search.bridge.TwoWayStringBridge;

import java.time.Instant;
import java.text.ParseException;

import static org.apache.lucene.document.DateTools.Resolution.MILLISECOND;
import static org.apache.lucene.document.DateTools.timeToString;

public class InstantBridge
        implements TwoWayStringBridge {

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
                timeToString(instant.toEpochMilli(), MILLISECOND) :
                null;
    }
}
