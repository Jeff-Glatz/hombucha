package ruffkat.swing.ui;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberRenderer extends DefaultTableCellRenderer.UIResource {
    private final NumberFormat formatter;

    public NumberRenderer(String decimalFormat) {
        this(new DecimalFormat(decimalFormat));
    }

    public NumberRenderer(NumberFormat formatter) {
        super();
        this.formatter = formatter;
        setHorizontalAlignment(JLabel.RIGHT);
    }


    public void setValue(Object value) {
        setText((value == null) ? "" : formatter.format(value));
    }
}
