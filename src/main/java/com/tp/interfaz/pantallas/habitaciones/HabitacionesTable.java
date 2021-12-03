package com.tp.interfaz.pantallas.habitaciones;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;

public class HabitacionesTable implements ChangeListener, PropertyChangeListener{

    private JTable jtable_habitaciones;
	private JTable jtable_fechas;
	private JScrollPane jspane_tabla;

    public HabitacionesTable (JScrollPane scrollPane){
        
		this.jspane_tabla = scrollPane;

		jtable_habitaciones = ((JTable)scrollPane.getViewport().getView());
		//jtable_habitaciones.setAutoCreateColumnsFromModel( false );
		
		jtable_habitaciones.addPropertyChangeListener( this );
		jtable_habitaciones.setCellSelectionEnabled(true);
		HabitacionesRenderer cellRenderer = new HabitacionesRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		jtable_habitaciones.setDefaultRenderer(Object.class, cellRenderer);

		//  Use the existing table to create a new table sharing
		//  the DataModel and ListSelectionModel

		jtable_fechas = new JTable();
		jtable_fechas.setAutoCreateColumnsFromModel( false );
		jtable_fechas.setModel(jtable_habitaciones.getModel());
		jtable_fechas.setSelectionModel( jtable_habitaciones.getSelectionModel() );
		jtable_fechas.setFocusable( false );

		//  Remove the jtable_fechas columns from the jtable_habitaciones table
		//  and add them to the jtable_fechas table

		TableColumn column = jtable_habitaciones.getColumnModel().getColumn(0);
		jtable_fechas.getColumnModel().addColumn(column);
		jtable_habitaciones.removeColumn(column);

		//  Add the jtable_fechas table to the scroll pane

        jtable_fechas.setPreferredScrollableViewportSize(jtable_fechas.getPreferredSize());
		scrollPane.setRowHeaderView( jtable_fechas );
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, jtable_fechas.getTableHeader());

		// Synchronize scrolling of the row header with the jtable_habitaciones table
		scrollPane.getRowHeader().addChangeListener( this );
	}

	public JTable getFechasTable(){
		return jtable_fechas;
	}

	@Override
	public void stateChanged(ChangeEvent e){
		//  Sync the scroll pane scrollbar with the row header
		JViewport viewport = (JViewport) e.getSource();
	    jspane_tabla.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e){
		//  Keep the jtable_fechas table in sync with the jtable_habitaciones table

		if ("selectionModel".equals(e.getPropertyName())){
			jtable_fechas.setSelectionModel( jtable_habitaciones.getSelectionModel() );
		}

		if ("model".equals(e.getPropertyName())){
			jtable_fechas.setModel( jtable_habitaciones.getModel() );
		}
	}
}
