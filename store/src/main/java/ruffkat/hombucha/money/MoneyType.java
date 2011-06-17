package ruffkat.hombucha.money;

import org.hibernate.HibernateException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class MoneyType implements
        UserType, ParameterizedType {
    private MoneyFormat format;

    @Override
    public void setParameterValues(Properties parameters) {
        // TODO: support configuration via parameters
        format = DefaultMoneyFormat.get();
    }

    public Class returnedClass() {
        return Money.class;
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
            Money money = (Money) value;
            st.setObject(index, format.format(money), Types.VARCHAR);
        }
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
            throws HibernateException,
            SQLException {
        if (!resultSet.wasNull()) {
            String money = resultSet.getString(names[0]);
            if (money != null) {
                return format.parse(money);
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
