package com.tp.interfaces.habitaciones;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.tp.interfaces.misc.Mensaje;
import com.tp.interfaces.misc.columngroup.GroupableTableHeader;

public class PintableTable extends JTable{

    private boolean seleccionando;
    private Point celdaInicial;
    private Point celdaFinal; 

    public PintableTable(DefaultTableModel jtable_habitaciones_contenido) {
        super(jtable_habitaciones_contenido);
        seleccionando=false;
        celdaInicial = new Point();
        celdaFinal = new Point();
        celdaInicial.setLocation(-1, -1);
        celdaFinal.setLocation(-1, -1);

        this.addMouseListener(new MouseListener(){

            @Override
            public void mouseReleased(MouseEvent e) {
                int row = getSelectedRow();
                int column = getSelectedColumn();

                if(getValueAt(row, column) == "OCUPADA"){
                    return;
                }

                if(!seleccionando){
                    if(row != 0){
                        Mensaje.mensajeInformacion("La fecha de inicio de la ocupación debe ser hoy.");
                        return;
                    }
                    seleccionando = true;
                    celdaInicial.setLocation(column, row);
                    celdaFinal.setLocation(-1, -1);
                }
                else{
                    if(column != celdaInicial.x){
                        if(row != 0){
                            Mensaje.mensajeInformacion("No se pueden ocupar múltiples habitaciones a la vez.");
                        }
                        else{
                            celdaInicial.setLocation(column, row);
                        }
                        jtable_habitaciones_contenido.fireTableDataChanged();
                        return;
                    }
                    int row_aux = row;
                    int inicial_y = celdaInicial.y;

                    if(row_aux < inicial_y){
                        row_aux=inicial_y;
                        inicial_y=row;
                    }

                    boolean ok = true;
                    for (int i=inicial_y ; i<=row_aux ; i++){
                        if(getValueAt(i,column).equals("OCUPADA")){
                            ok=false;
                            break;
                        }
                    }
                    if(ok){
                        celdaFinal.setLocation(column, row);
                        if(celdaFinal.y<celdaInicial.y){
                            celdaFinal.y=celdaInicial.y;
                            celdaInicial.y=row;
                        }
                        seleccionando = false;
                    }
                    else{
                        celdaInicial.setLocation(column, row);
                        jtable_habitaciones_contenido.fireTableDataChanged();
                    }
                } 
                jtable_habitaciones_contenido.fireTableDataChanged(); 
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
    }

    public Point getCeldaInicial(){
        return celdaInicial;
    }
    public Point getCeldaFinal(){
        return celdaFinal;
    }

    @Override
    protected JTableHeader createDefaultTableHeader() {
        return new GroupableTableHeader(columnModel);
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

    public boolean isSeleccionando() {
        return seleccionando;
    }

   /* @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
        Component c = super.prepareRenderer(renderer, row, column);

        if(getSelectedRow() == row && getSelectedColumn() == column){
            if(c.getBackground().equals(Color.RED))
                return c;
            
            if(!seleccionando){
                seleccionando = true;
                celdaInicial.setLocation(column, row);
                c.setBackground(Color.BLUE);
            }
            else{
                if(column != celdaInicial.x)
                    return c;
                celdaFinal.setLocation(column, row);
                if(celdaFinal.y<celdaInicial.y){
                    celdaFinal.y=celdaInicial.y;
                    celdaInicial.y=column;
                }
                seleccionando = false;
            }
            
        }
        return c;
    }*/
}
