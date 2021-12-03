package com.tp.interfaz.pantallas.misc.spinner;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

public class SpinnerTable extends JTable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8237770086997305403L;
	private List<Integer> max_jspinner = new ArrayList<Integer>();

    public List<Integer> getJspinnersMaxList(){
        return max_jspinner;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
        switch (column) {
            case 4:
                return Boolean.class;
            default:
                return Object.class;
        }
    }
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);

    }

    @Override
    public boolean isCellEditable(int row, int column) {

        if (column == 3)
            return true;

        return super.isCellEditable(row, column);
    }

}
