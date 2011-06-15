package ruffkat.hombucha.jpa;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import javax.measure.Measure;
import javax.measure.unit.Unit;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class MeasureType implements UserType {

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
        return new int[]{Types.NUMERIC, Types.VARCHAR};
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index)
            throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.NUMERIC);
            st.setNull(index + 1, Types.VARCHAR);
        } else {
            Measure<?> measure = (Measure<?>) value;
            st.setObject(index, measure.getValue(), Types.NUMERIC);
            st.setObject(index + 1, measure.getUnit().toString(), Types.VARCHAR);
        }
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
            throws HibernateException,
            SQLException {
        if (!resultSet.wasNull()) {
            Double value = resultSet.getDouble(names[0]);
            if (value != null) {
                Unit<?> unit = Unit.valueOf(resultSet.getString(names[1]));
                return Measure.valueOf(value, unit);
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
