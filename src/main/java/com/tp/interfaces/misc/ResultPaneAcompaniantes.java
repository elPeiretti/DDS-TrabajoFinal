package com.tp.interfaces.misc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.RowSorter.SortKey;
import javax.swing.border.LineBorder;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ResultPaneAcompaniantes<E> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -434640732594870399L;
	private JTable jtable_resultados;
	private JLabel lbl_paginas;
	private JButton btn_prev;
	private JButton btn_next;
	private List<E> objetos_en_tabla;
	private DefaultTableModel jtable_contenido;
	private JScrollPane jsp_tabla;
	private Integer pagina_actual = 1;
	private Long cant_paginas = 1L;
	private Integer cant_filas = 8;
	private TableRowSorter<TableModel> sorter;
	
	public ResultPaneAcompaniantes() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		jtable_resultados = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7495260620475200509L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
                switch (column) {
                    case 4:
                        return Boolean.class;
                    default:
                        return Object.class;
                }
            }
		};
		
		jsp_tabla = new JScrollPane(jtable_resultados);
		jsp_tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
		jsp_tabla.setBounds(0, 0, 620, 150);
		add(jsp_tabla);
				
		lbl_paginas = new JLabel("1/1");
		lbl_paginas.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_paginas.setBounds(272, 158, 76, 14);
		add(lbl_paginas);
		
		btn_prev = new JButton("<");
		btn_prev.setBounds(212, 155, 50, 20);
		add(btn_prev);
		
		btn_next = new JButton(">");
		btn_next.setBounds(358, 154, 50, 20);
		add(btn_next);
		
		jtable_contenido = new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -4959317085830906692L;

			public boolean isCellEditable(int row, int column){
				return false;
				//return true;
			}			
		};
		jtable_resultados.setModel(jtable_contenido);
		sorter = new TableRowSorter<TableModel>(jtable_contenido);
		jtable_resultados.setRowSorter(sorter);
		objetos_en_tabla = new ArrayList<E>();
	}
	
	public ResultPaneAcompaniantes(Runnable tableFillerMethod){
		this();
		
		btn_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(pagina_actual >= cant_paginas) return;
				
				pagina_actual++;
				tableFillerMethod.run();
				
			}
		});
		
		btn_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(pagina_actual.equals(1)) return;
				
				pagina_actual--;
				tableFillerMethod.run();
			}
		});
	}
	
	public void agregarColumnas(List<String> nombres,List<Integer> noOrdenables) {
		nombres.forEach(n -> jtable_contenido.addColumn(n));
		if(noOrdenables == null) return;
		noOrdenables.forEach(n -> sorter.setSortable(n,false));
		sorter.setSortKeys(List.of(new SortKey(0,SortOrder.ASCENDING)));
	}
	
	public void agregarRowListener(RowSorterListener listener) {
		sorter.addRowSorterListener(listener);
	}
	
	public Integer getPaginaActual() {
		return pagina_actual;
	}

	public void setPaginaActual(Integer pagina_actual) {
		this.pagina_actual = pagina_actual;
		lbl_paginas.setText(pagina_actual.toString()+"/"+cant_paginas.toString());
	}

	public Long getCantPaginas() {
		return cant_paginas;
	}

	public void setCantPaginas(Long cant_paginas) {
		this.cant_paginas = cant_paginas == 0? 1 : cant_paginas;
		lbl_paginas.setText(pagina_actual.toString()+"/"+this.cant_paginas.toString());
	}

	public DefaultTableModel getContenido() {
		return jtable_contenido;
	}

	public void setContenido(DefaultTableModel jtable_contenido) {
		this.jtable_contenido = jtable_contenido;
	}
	
	public JTable getTable() {
		return jtable_resultados;
	}

	public JLabel getPageNumbers() {
		return lbl_paginas;
	}

	public JButton getPrevBtn() {
		return btn_prev;
	}

	public JButton getNextBtn() {
		return btn_next;
	}

	public List<E> getRowObjects() {
		return objetos_en_tabla;
	}

	public Integer getCantidadFilas(){
		return cant_filas;
	}
	
}
