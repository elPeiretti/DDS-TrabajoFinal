package com.tp.interfaz.pantallas.misc;

import java.util.List;
import javax.swing.*;

import java.awt.event.*;

public class EnterActionAssigner {
    public static void setEnterAction(JButton b){
        b.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter.pressed");
        b.getActionMap().put("Enter.pressed", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -6747046521073467580L;

			@Override
            public void actionPerformed(ActionEvent e) {
                b.doClick();
            }
        });
    }
    public static void setEnterAction(@SuppressWarnings("rawtypes") JComboBox jc){
        jc.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter.pressed");
        jc.getActionMap().put("Enter.pressed", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 4581128632963491028L;

			@Override
            public void actionPerformed(ActionEvent e) {
                if(jc.isPopupVisible()) jc.hidePopup();
                else jc.showPopup();
            }
        });
        
    }
    public static void setEnterAction(List<JButton> buttonList){
        for (JButton b : buttonList){
            EnterActionAssigner.setEnterAction(b);
        }
    }
    public static void setEnterActionComboBox(@SuppressWarnings("rawtypes") List<JComboBox> comboList){
        for (@SuppressWarnings("rawtypes") JComboBox b : comboList){
            EnterActionAssigner.setEnterAction(b);
        }
    }
    
    

}
