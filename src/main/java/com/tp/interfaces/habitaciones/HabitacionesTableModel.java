package com.tp.interfaces.habitaciones;

import javax.swing.table.DefaultTableModel;

public class HabitacionesTableModel extends DefaultTableModel{
    public HabitacionesTableModel(){
        super();
    }

    public String getEstado(int row, int col){
        return (String) this.getValueAt(row, col);
    }
}
