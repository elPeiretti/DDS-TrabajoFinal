package com.tp.interfaces.misc;

import java.util.List;
import javax.swing.*;
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

    public static void setEnterAction(List<JButton> buttonList){
        for (JButton b : buttonList){
            EnterActionAssigner.setEnterAction(b);
        }
    }

}
