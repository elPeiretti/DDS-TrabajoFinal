package com.tp.interfaces.misc;

import java.util.List;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.event.*;

public class EnterActionAssigner {
    public static void setEnterAction(JButton b){
        b.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter.pressed");
        b.getActionMap().put("Enter.pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.doClick();
            }
        });
    }
    public static void setEnterAction(JComboBox jc){
        jc.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter.pressed");
        jc.getActionMap().put("Enter.pressed", new AbstractAction() {
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
    public static void setEnterActionComboBox(List<JComboBox> comboList){
        for (JComboBox b : comboList){
            EnterActionAssigner.setEnterAction(b);
        }
    }
    
    

}
