package com.tp.interfaces.misc.spinner;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;

import java.awt.Dimension;

public class SpinnerPanel extends JPanel{

    protected JSpinner spinner = new JSpinner() {
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
