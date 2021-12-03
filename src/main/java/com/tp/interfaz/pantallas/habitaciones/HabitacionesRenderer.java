package com.tp.interfaz.pantallas.habitaciones;

import java.awt.Color;

import javax.swing.JTable;
import java.awt.Component;
import java.awt.Point;

import javax.swing.table.DefaultTableCellRenderer;

public class HabitacionesRenderer extends DefaultTableCellRenderer{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3441189786021680448L;
	Color color_RESERVADA = new Color(255,255,128);;
    Color color_OCUPADA = new Color(255,128,128);;
    Color color_SELECCIONADA = new Color(0,128,255);;
    Color color_MANTENIMIENTO = new Color(255,153,0);

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        column++;
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        HabitacionesTableModel model = (HabitacionesTableModel) table.getModel();
        
        if(model.getEstado(row, column) == null){
            setBackground(table.getBackground());
            return this;
        }

        
        Point init = ((PintableTable) table).getCeldaInicial();
        Point fin = ((PintableTable) table).getCeldaFinal();

        if(init.getX()!=-1 && fin.getX()!=-1 && row <= fin.getY() && row>= init.getY() && column-1 == fin.getX()){
            setBackground(color_SELECCIONADA);
        }
        else if (((PintableTable) table).isSeleccionando() && column-1 == init.getX() && row == init.getY()){
            setBackground(color_SELECCIONADA);
        }
        else{
            switch(model.getEstado(row, column)){
                case "RESERVADA":{
                    setBackground(color_RESERVADA);
                    break;
                }
                case "OCUPADA":{
                    setBackground(color_OCUPADA);
                    break;
                }
                case "MANTENIMIENTO":{
                    setBackground(color_MANTENIMIENTO);
                    break;
                }
                default:{
                    setBackground(table.getBackground());
                }
            }
        }
        
        return this;
    }
}
