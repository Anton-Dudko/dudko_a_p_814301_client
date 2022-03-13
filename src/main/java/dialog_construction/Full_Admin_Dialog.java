package dialog_construction;

import sockets.Data_Prerunner;
import entity.UserIEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Full_Admin_Dialog extends Admin_Dialog {
    @Override
    public void prepareFrameDialog() {
        super.prepareFrameDialog();
        frameDialog.buttons.get(7).setLocation(150, 430);
        frameDialog.addButton(500, 30,150, 380, "Пользователи");
        frameDialog.addButton(500, 30,150, 480, "Закрыть выполнение системы");
        frameDialog.setSize(800, 570);
    }

    @Override
    public void setEventsForButtons() {
        super.setEventsForButtons();
        setEventUser();
        setEventExitWitchServer();
    }

    protected void setEventUser(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Just_Product_Type categoryDialog = new Just_Product_Type(frameDialog, UserIEntity.class, "\"Пользователи\"");
                categoryDialog.create();
            }
        };
        frameDialog.buttons.get(8).addActionListener(actionListener);
    }

    protected void setEventExitWitchServer(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String exitingServerMessage = Data_Prerunner.exit("witch");
                if(!"".equals(exitingServerMessage))
                    JOptionPane.showMessageDialog(frameDialog, exitingServerMessage, "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
                frameDialog.dispose();
            }
        };
        frameDialog.buttons.get(9).addActionListener(actionListener);
    }
}
