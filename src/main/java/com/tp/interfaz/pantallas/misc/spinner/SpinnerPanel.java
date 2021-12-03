package com.tp.interfaz.pantallas.misc.spinner;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;

import java.awt.Dimension;

public class SpinnerPanel extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5033264129881734036L;
	protected JSpinner spinner = new JSpinner() {
        /**
		 * 
		 */
		private static final long serialVersionUID = 2959743319020480973L;

		@Override
        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            return new Dimension(40, d.height);
        }
    };

    public SpinnerPanel() {
        super();
        setOpaque(true);
        ((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        add(spinner);
    }
}
