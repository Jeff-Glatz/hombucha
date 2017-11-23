package ruffkat.hombucha.measure;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import javax.measure.Measure;
import javax.measure.MeasureFormat;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParsePosition;
import java.util.Properties;

/**
 * Persists instances of {@code JSR-275} {@link Measure}
 */
public class MeasureType
        implements UserType, ParameterizedType {
    private MeasureFormat format;

    @Override
    public void setParameterValues(Properties parameters) {
        // TODO: support configuration via parameters
        format = MeasureFormat.getStandard();
    }

    public Class returnedClass() {
        return Measure.class;
    }

    public boolean equals(Object x, Object y)
            throws HibernateException {
        return x != null ? x.equals(y) : y == x;
    }

    public int hashCode(Object x)
            throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }

    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor implementor)
            throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.VARCHAR);
        } else {
            st.setObject(index, format.format(value), Types.VARCHAR);
        }
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor implementor, Object owner)
            throws HibernateException, SQLException {
        if (!resultSet.wasNull()) {
            String measure = resultSet.getString(names[0]);
            if (measure != null) {
                return format.parse(measure, new ParsePosition(0));
            }
        }
        return null;
    }

    public Object deepCopy(Object value)
            throws HibernateException {
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object value)
            throws HibernateException {
        return (Serializable) value;
    }

    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }
}
