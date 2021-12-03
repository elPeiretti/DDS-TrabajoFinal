package com.tp.interfaz.pantallas.misc.spinner;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;

public class SpinnerCellRenderer extends SpinnerPanel implements TableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4029070467949009807L;

	public SpinnerCellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(isSelected ? table.getSelectionBackground() : table
                .getBackground());
        
        if (value != null) {
            if((int)value>0){
                int max = ((SpinnerTable) table).getJspinnersMaxList().get(row);
                if((int)value < max)
                    spinner.setValue(value);
                else{
                    spinner.setValue(max);
                }
            }
            else{
                spinner.setValue(0);
            }
        }
        return this;
    }
}

