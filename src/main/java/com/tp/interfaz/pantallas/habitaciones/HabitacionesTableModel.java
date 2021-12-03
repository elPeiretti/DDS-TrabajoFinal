package com.tp.interfaz.pantallas.habitaciones;

import javax.swing.table.DefaultTableModel;

public class HabitacionesTableModel extends DefaultTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4471712428274003782L;

	public HabitacionesTableModel(){
        super();
    }

    public String getEstado(int row, int col){
        return (String) this.getValueAt(row, col);
    }
}
