package com.tp.interfaces.misc;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class ResultPane extends JPanel {
	
	public JTable jtable_resultados;
	public JLabel lbl_paginas;
	public JButton btn_prev;
	public JButton btn_next;

	public ResultPane() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		jtable_resultados = new JTable();
		jtable_resultados.setBorder(new LineBorder(new Color(0, 0, 0)));
		jtable_resultados.setBounds(0, 0, 620, 150);
		add(jtable_resultados);
		
		lbl_paginas = new JLabel("0/0");
		lbl_paginas.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_paginas.setBounds(272, 158, 76, 14);
		add(lbl_paginas);
		
		btn_prev = new JButton("<");
		btn_prev.setBounds(212, 155, 50, 20);
		add(btn_prev);
		
		btn_next = new JButton(">");
		btn_next.setBounds(358, 154, 50, 20);
		add(btn_next);
		
	}

}
