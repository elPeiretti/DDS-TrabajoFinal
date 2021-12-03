package com.tp.interfaz.pantallas.misc.spinner;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;

import java.awt.event.*;
import java.util.EventObject;

public class SpinnerCellEditor extends SpinnerPanel implements TableCellEditor {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1645954546223081L;

	public SpinnerCellEditor() {
        super();
        spinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                fireEditingStopped();

            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.setBackground(table.getSelectionBackground());
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
        
        return this;
    }

    // Copid from DefaultCellEditor.EditorDelegate
    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        if (anEvent instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) anEvent;
            return e.getID() != MouseEvent.MOUSE_DRAGGED;
        }
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    };

    transient protected ChangeEvent changeEvent = null;

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
    }

    public CellEditorListener[] getCellEditorListeners() {
        return listenerList.getListeners(CellEditorListener.class);
    }

    protected void fireEditingStopped() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null)
                    changeEvent = new ChangeEvent(this);
                ((CellEditorListener) listeners[i + 1])
                        .editingStopped(changeEvent);
            }
        }
    }

    protected void fireEditingCanceled() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null)
                    changeEvent = new ChangeEvent(this);
                ((CellEditorListener) listeners[i + 1])
                        .editingCanceled(changeEvent);
            }
        }
    }
}
