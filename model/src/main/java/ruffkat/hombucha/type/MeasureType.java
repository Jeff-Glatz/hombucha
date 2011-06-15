package ruffkat.hombucha.type;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import javax.measure.Measure;
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
        return new int[]{Types.VARCHAR};
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index)
            throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.VARCHAR);
        } else {
            st.setObject(index, value.toString(), Types.VARCHAR);
        }
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
            throws HibernateException,
            SQLException {
        if (!resultSet.wasNull()) {
            String measure = resultSet.getString(names[0]);
            if (measure != null) {
                return Measure.valueOf(measure);
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
