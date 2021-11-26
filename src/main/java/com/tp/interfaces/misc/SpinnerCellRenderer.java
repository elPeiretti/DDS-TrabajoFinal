package com.tp.interfaces.misc;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.SpinnerNumberModel;

public class SpinnerCellRenderer extends SpinnerPanel implements TableCellRenderer {
    public SpinnerCellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(isSelected ? table.getSelectionBackground() : table
                .getBackground());
        if (value != null) {
            spinner.setValue(value);
        }
        return this;
    }
}

