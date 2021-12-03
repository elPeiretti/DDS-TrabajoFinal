package com.tp.interfaz.pantallas.habitaciones;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.tp.interfaz.pantallas.misc.Mensaje;
import com.tp.interfaz.pantallas.misc.columngroup.GroupableTableHeader;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PintableTable extends JTable{

	private static final long serialVersionUID = -6833561922562096298L;
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
                    Mensaje.mensajeInformacion("No se puede ocupar una habitación que ya se encuentra ocupada.");
                    seleccionando=false;
                    return;
                }
                if(getValueAt(row, column) == "MANTENIMIENTO"){
                    Mensaje.mensajeInformacion("No se puede ocupar una habitación que se encuentra en mantenimiento.");
                    seleccionando=false;
                    return;
                }

                if(!seleccionando){
                    if(!LocalDate.parse((String)jtable_habitaciones_contenido.getValueAt(row, 0),DateTimeFormatter.ofPattern("dd/MM/yyyy")).equals(LocalDate.now())){
                        Mensaje.mensajeInformacion("La fecha de inicio de la ocupación debe ser hoy.");
                        return;
                    }
                    seleccionando = true;
                    celdaInicial.setLocation(column, row);
                    celdaFinal.setLocation(-1, -1);
                }
                else{
                    if(column != celdaInicial.x){
                        if(!LocalDate.parse((String)jtable_habitaciones_contenido.getValueAt(row, 0),DateTimeFormatter.ofPattern("dd/MM/yyyy")).equals(LocalDate.now())){
                            Mensaje.mensajeInformacion("No se pueden ocupar múltiples habitaciones a la vez.");
                            seleccionando=false;
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
                        if(row<celdaInicial.y){ // selecciono una fecha anterior a la actual como celdaFinal
                            Mensaje.mensajeInformacion("No se puede ocupar una habitación en el pasado.");
                            celdaInicial.setLocation(-1,-1);                            
                        }
                        else{
                            celdaFinal.setLocation(column, row);
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

    public void limpiarCeldasSeleccionadas(){
        celdaInicial.setLocation(-1, -1);
        celdaFinal.setLocation(-1, -1);
        seleccionando=false;
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

}
