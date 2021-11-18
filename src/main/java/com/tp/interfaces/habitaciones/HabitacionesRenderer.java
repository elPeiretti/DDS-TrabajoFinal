package com.tp.interfaces.habitaciones;

import java.awt.Color;

import javax.swing.JTable;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;

public class HabitacionesRenderer extends DefaultTableCellRenderer{

    Color color_RESERVADA = Color.YELLOW;
    Color color_OCUPADA = Color.RED;
    Color color_SELECCIONADA = Color.BLUE;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        column++;
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        HabitacionesTableModel model = (HabitacionesTableModel) table.getModel();
        
        if(model.getEstado(row, column) == null){
            setBackground(table.getBackground());
            return this;
        }

        switch(model.getEstado(row, column)){
            case "RESERVADA":{
                setBackground(color_RESERVADA);
                break;
            }
            case "OCUPADA":{
                setBackground(color_OCUPADA);
                break;
            }
            default:{
                setBackground(table.getBackground());
            }
        }
        return this;
    }
}
